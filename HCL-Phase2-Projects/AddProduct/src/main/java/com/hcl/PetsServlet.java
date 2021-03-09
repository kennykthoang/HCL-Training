package com.hcl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hcl.Product;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
        try {
               SessionFactory factory = HibernateUtil.getSessionFactory();
               
               Session session = factory.openSession();
               // using HQL
               List<Product> list = session.createQuery("from Product", Product.class).list();
               
               session.close();
               
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<b>Pet Listing</b><br>");
                for(Product p: list) {
                        out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                        ", Price: " + String.valueOf(p.getPrice()) + ", Color: " + p.getColor().toString() + "<br>");
                }
                out.println("<a href='index.jsp'>Return to homepage</a><br>");
                out.println("</body></html>");
            
            
        } catch (Exception ex) {
                throw ex;
        }

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        PrintWriter out = response.getWriter();
        boolean validFlag = true;
        String name = request.getParameter("name"), color = request.getParameter("color"), priceStr = request.getParameter("price");
        out.println("<html><body>"); 
        
        // Check if missing any input
        if(name.isEmpty() || color.isEmpty() || priceStr.isEmpty())
        {
        	out.print("<h3 style=\"background-color:red\">Error! Empty input.</h3>");
          	if(name.isEmpty())
	        {
	    		out.print("<h4>Please input a name.</h4>");
	    		validFlag = false;
	    	}
	        
	        if(color.isEmpty())
	        {
	    		out.print("<h4>Please input the color of the pet.</h4>");
	    		validFlag = false;
	    	}
	        
	        if(priceStr.isEmpty())
	        {
	    		out.print("<h4>Please input the price.</h4>");
	    		validFlag = false;
	    	}
	        
	        out.println("<a href='index.jsp'>Return to homepage</a><br>");
        }
        
        if(!priceStr.matches("^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$") && validFlag)
        {
        	out.print("<h3 style=\"background-color:red\">Error! Invalid price.</h3>");
    		out.print("<h4>Input price \"" + priceStr + "\" is invalid.</h4>");
    		out.println("<a href='index.jsp'>Return to homepage</a><br>");
    		validFlag = false;
        }
        
        // If input is valid, add to the table
        if(validFlag)
        {
        	SessionFactory factory = HibernateUtil.getSessionFactory();
        	Session session = factory.openSession();
        	session.beginTransaction();
        	
        	// Set the price to pass to Product
            BigDecimal price = new BigDecimal(priceStr);
        	Product input = new Product();
        	input.setName(name);
        	input.setColor(color);
        	input.setPrice(price);
        	
        	session.save(input);
        	
        	session.getTransaction().commit();
        	
	        out.println("<b>Adding Pet</b> " + "\"" + request.getParameter("name") + "\"" + "<br>");
	        out.println("<a href='index.jsp'>Return to homepage</a><br>");
	        out.println("</body></html>");
	        
	        session.close();
        }

		// doGet(request, response);
	}
}