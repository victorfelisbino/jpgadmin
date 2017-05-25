<%@page import="com.products.UserDao"%>
<jsp:useBean id="u" class="com.products.UserBean"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
UserDao.delete(u);
response.sendRedirect("viewusers.jsp");
%>