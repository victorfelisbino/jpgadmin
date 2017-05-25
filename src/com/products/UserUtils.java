package com.products;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class UserUtils extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) 
				           throws ServletException, java.io.IOException {
	
		try{	    
	
			UserBean user = new UserBean();
			String action = request.getParameter("action");
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setUserName(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			if(action.equals("add")){
				Integer done = UserDao.save(user);
				HttpSession session = request.getSession(true);
				if(done != 0){
					session.setAttribute("message","Successfully added user!");
					response.sendRedirect("viewusers.jsp");
				}else{
					session.setAttribute("message","An error occurred while attempting to add new user!");
					response.sendRedirect("viewusers.jsp");
				}
			}else if(action.equals("edit")){
				Integer done = UserDao.update(user);
				HttpSession session = request.getSession(true);
				if(done != 0){
					session.setAttribute("message","Successfully added user!");
					response.sendRedirect("viewusers.jsp");
				}else{
					session.setAttribute("message","An error occurred while attempting to add new user!");
					response.sendRedirect("viewusers.jsp");
				}
			}
		}catch (Throwable theException){
			System.out.println(theException); 
		}
	}
}