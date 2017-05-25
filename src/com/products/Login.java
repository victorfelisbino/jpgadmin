package com.products;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class Login extends HttpServlet {


public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {

try
{	    

     UserBean user = new UserBean();
     user.setUserName(request.getParameter("un"));
     user.setPassword(request.getParameter("pw"));

     HttpSession session = request.getSession(true);
	 if(user.getUsername() != null && user.getUsername() != ""){
		 user = UserDao.login(user);
	     if (user.isValid()){     
	          session.setAttribute("currentSessionUser",user); 
	          response.sendRedirect("userLogged.jsp"); //logged-in page      		
	     }else{
	    	  session.setAttribute("message","Invalid username/password");
	          response.sendRedirect("index.jsp"); //error page 
	     }
	 }else{
		 session.setAttribute("message","");
		 response.sendRedirect("index.jsp"); 
	 }
} 
		
		
catch (Throwable theException) 	    
{
     System.out.println(theException); 
}
       }
	}