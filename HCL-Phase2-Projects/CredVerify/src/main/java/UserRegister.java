

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hcl.HibernateUtil;
import com.hcl.model.User;

/**
 * Servlet implementation class UserRegister
 */
public class UserRegister extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
        boolean validFlag = true;
        String firstname = request.getParameter("firstname"), lastname = request.getParameter("lastname"), 
        		username = request.getParameter("username"), password = request.getParameter("password");
        out.println("<html><body>"); 
        
        if(firstname.isEmpty() || lastname.isEmpty() || username.isEmpty() || password.isEmpty())
        {
        	out.print("<h3 style=\"background-color:red\">Error! Empty input.</h3>");
          	if(firstname.isEmpty())
	        {
	    		out.print("<h4>Please input a first name.</h4>");
	    	}
	        
	        if(lastname.isEmpty())
	        {
	    		out.print("<h4>Please input a last name.</h4>");
	    	}
	        
	        if(username.isEmpty())
	        {
	    		out.print("<h4>Please input a username.</h4>");
	    	}
	        
	        if(password.isEmpty())
	        {
	        	out.print("<h4>Please input a password.</h4>");
	        }
	        validFlag = false;
	        out.println("<a href='index.jsp'>Return to homepage</a><br>");
        }
        
     // If input is valid, add to the table
        if(validFlag)
        {
        	SessionFactory factory = HibernateUtil.getSessionFactory();
        	Session session = factory.openSession();
        	session.beginTransaction();
        	
        	// Set the price to pass to Product
            User user = new User();
        	user.setFirstName(firstname);
        	user.setLastName(lastname);
        	user.setUsername(username);
        	user.setPassword(password);
        	
        	session.save(user);
        	
        	session.getTransaction().commit();
        	
	        out.println("<b>Adding user</b> " + "\"" + request.getParameter("username") + "\" to database" + "<br>");
	        out.println("<a href='index.jsp'>Return to homepage</a><br>");
	        out.println("</body></html>");
	        
	        session.close();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		UserDao loginDao = new UserDao();
		
		boolean isValid = true;
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        out.println("<html><body>");
        if(username.isEmpty() || password.isEmpty())
        {
        	out.print("<h3 style=\"background-color:red\">Error! Empty input.</h3>");
        	
        	if(username.isEmpty())
        		out.print("<h4>Please input a username.</h4>");
        	
        	if(password.isEmpty())
	        {
	    		out.print("<h4>Please input a password.</h4>");
	    	}
        	isValid = false;
        	out.println("<a href='index.jsp'>Return to homepage</a><br>");
        }
        
        if(isValid)
	        if (loginDao.validate(username, password)) 
	        {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("loginsuccess.jsp");
	            dispatcher.forward(request, response);
	        } 
	        else 
	        {
	            out.println("Error! Username or password is incorrect.");
	        }
        
        out.println("</body></html>");
		// doGet(request, response);
	}

}
