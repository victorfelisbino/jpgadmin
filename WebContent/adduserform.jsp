<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.products.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User Form</title>
<% 
			HttpSession ses = request.getSession();
			UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));
			if(ses == null || currentUser == null){
				response.sendRedirect("index.jsp");
			}
		%>
</head>
<body>
	<jsp:include page="navigation.jsp"/>
	<div id="main">
		<form action="UserUtils">
			<input type="hidden" name="action" value="add"/>
			<% String message = (String) (session.getAttribute("message"));%>
			<h4 style="color:red;"><%=message %></h4>
			<table>
				<tr><td>First Name:</td><td><input type="text" name="firstName"/></td></tr>
				<tr><td>Last Name:</td><td><input type="text" name="lastName"/></td></tr>
				<tr><td>UserName:</td><td><input type="text" name="username"/></td></tr>
				<tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
				<tr><td>Email:</td><td><input type="email" name="email"/></td></tr>
			<input type="submit" value="submit">			
		</form>
	</div>


</body>
</html>