<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="com.products.UserBean"
   %>
 
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
   "http://www.w3.org/TR/html4/loose.dtd">

   <html>

      <head>
         <meta http-equiv="Content-Type" 
            content="text/html; charset=windows-1256">
         <title>   User Logged Successfully   </title>
         <% 
        	UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));
			if(session == null || currentUser == null){
				response.sendRedirect("index.jsp");
			}
         	
		%>
      </head>
	
      <body>
      	<jsp:include page="navigation.jsp"/>
		<div id="main">
			
            Welcome <%= currentUser.getFirstName() + " " + currentUser.getLastName() %>
        </div>

      </body>
	
   </html>