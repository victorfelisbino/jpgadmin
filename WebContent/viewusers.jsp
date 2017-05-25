<!DOCTYPE html>

<html>
	<head>
		<%@page import="com.products.*,java.util.*"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>View Users</title>
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
			<h1>Users List</h1>
			<%
			List<UserBean> list=UserDao.getAllRecords();
			request.setAttribute("list",list);
			%>
			
			<table border="1" width="90%">
			<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Edit</th><th>Delete</th></tr>
			<c:forEach items="${list}" var="u">
				<tr><td>${u.getId()}</td><td>${u.getFirstName()}</td><td>${u.getLastName()}</td><td>${u.getEmail()}</td><td><a href="editform.jsp?id=${u.getId()}">Edit</a></td><td><a href="deleteuser.jsp?id=${u.getId()}">Delete</a></td></tr>
			</c:forEach>
			</table>
			<br/><a href="adduserform.jsp">Add New User</a>
		</div>
	</body>
</html>