<%@page import="com.products.UserDao"%>

<%@page import="com.products.UserDao,com.products.UserBean"%>

<%
String id=request.getParameter("id");
UserBean u=UserDao.getRecordById(Integer.parseInt(id));
%>

<h1>Edit Form</h1>
<form action="UserUtils" method="post">
<input type="hidden" name="id" value="<%=u.getId() %>"/>
<input type="hidden" name="action" value="edit"/>
<table>
<tr><td>First Name:</td><td><input type="text" name="name" value="<%= u.getFirstName()%>"/></td></tr>
<tr><td>Last Name:</td><td><input type="text" name="name" value="<%= u.getLastName()%>"/></td></tr>
<tr><td>Username:</td><td><input type="text" name="name" value="<%= u.getUsername()%>"/></td></tr>
<tr><td>Password:</td><td><input type="password" name="password" value="<%= u.getPassword()%>"/></td></tr>
<tr><td>Email:</td><td><input type="email" name="email" value="<%= u.getEmail()%>"/></td></tr>

<tr><td colspan="2"><input type="submit" value="Edit User"/></td></tr>
</table>
</form>