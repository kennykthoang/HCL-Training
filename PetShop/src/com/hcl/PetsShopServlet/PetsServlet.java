package com.hcl.PetsShopServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcl.PetsShopServlet.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class PetsServlet
 */
@WebServlet(name="PetsServlet",urlPatterns = {"/PetsServlet"})
public class PetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
        
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
        // TODO Auto-generated method stub
		int id = 0;
        try {
	        	PrintWriter out = response.getWriter();
	            out.println("<html><body>");
	            
	            String str = request.getParameter("petid");
	                 
	            InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
	            Properties props = new Properties();
	            props.load(in);
	                
	            DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
	        	Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	            
	        	int dbid = 0,petid = 0;
	        	boolean errorFlag = false;
	        	if(str.isEmpty())
	        	{
	        		out.print("<h3 style=\"background-color:red\">Error! Empty input.</h3>");
	        		out.print("<h3>Please input an ID#.</h3>");
	        		errorFlag = true;
	        	}
	        	else if(!str.matches("^[1-9]\\d*$"))
	        	{
	        		out.print("<h3 style=\"background-color:red\">Error! Invalid Input!.</h3>");
	        		out.print("<h3>Please input valid numbers only.</h3>");
	        		errorFlag = true;
	        	}
	        	else
	        	{
	        		ResultSet rst = stmt.executeQuery("select count(*) from products");
	        		
	        		petid = Integer.parseInt(str);
	        		while(rst.next())
	        		{
	        			dbid = rst.getInt(1);
	        		}
	        		
	        		if(petid > dbid)
	        		{
	        			errorFlag = true;
	        			out.print("<h3 style=\"background-color:red\">Error! Pet ID# \"" + petid + "\" was not found!</h3>");
	        			out.print("<h4> Pet ID# \"" + petid + "\" is greater than the total number of pets.");
	        			out.print("<h4> There are currently " + dbid + " unique pets in the shop. </h4>");
	        		}
	        	}
	        	
	        	if(errorFlag == false)
	        	{
	        		ResultSet rst = stmt.executeQuery("select * from products where id=" + str);
		            out.println("<table><tr><th>Name</th><th>Color</th><th>Price</th></tr>");
		            
		            while (rst.next()) {
		                out.println("<tr><td>" + rst.getString("name") + "</td>" + "<td>" +
		                		rst.getString("color") + "</td><td>" + rst.getBigDecimal("price") + "</td></tr>");
		            }
	            }
	            
	            out.println("</table>");
	        
	            stmt.close();        
	   
	        
	            out.println("</body></html>");
	            conn.closeConnection();
	        
	        } 
        	catch (ClassNotFoundException e){
	        	e.printStackTrace();
	        } 
        	catch (SQLException e) {
	        	e.printStackTrace();
	        }
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
