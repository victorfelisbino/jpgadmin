<%@ page language="java" 
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
    import="com.products.UserBean"
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Login Page</title>
		<link rel="stylesheet" href="css/style.css">
		<% 
			UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));
			if(currentUser != null){
				response.sendRedirect("userLogged.jsp"); 
			}
		%>
	</head>

	<body>
		<div class="wrapper">
			<div class="container">
				
				<form action="Login">
					<h1>Welcome to JPG</h1>
					  <% String message = (String) (session.getAttribute("message"));%>
					  <h4 style="color:red;"><%=message %></h4>
					<input type="text" name="un" placeholder="Username"/><br>		
					<input type="password" name="pw" placeholder="Password"/>
					<input type="submit" value="submit">			
				</form>
			</div>
			
			<ul class="bg-bubbles" style="background-color: #205dbf;">
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
		<script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	
	    <script src="js/index.js"></script>
	</body>
</html>