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
			HashMap<String,String> mappingMap = new HashMap<String,String>();
		%>
		<style>
#div1, #div2 {
    float: left;
    width: 100px;
    height: 35px;
    margin: 10px;
    padding: 10px;
    border: 1px solid black;
}
</style>
<script>
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.target.appendChild(document.getElementById(data));
}
</script>
	</head>
	<body>
		<jsp:include page="navigation.jsp"/>
		<div id="main">	
<div id="div1" ondrop="drop(event)" ondragover="allowDrop(event)">
  <div  draggable="true" ondragstart="drag(event)" id="drag1" width="88" height="31">
  	test
  </div>
</div>

<div id="div2" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
		</div>
	</body>
</html>