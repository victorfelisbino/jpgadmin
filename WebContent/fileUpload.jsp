<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="com.products.*,java.util.*"
   %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				//response.sendRedirect("index.jsp");
			}
			List<String> vendorslist=ProductUtils.getVendors();
			List<String> csvFields = UploadCSV.getcsvFields();
			List<String> productFields = UploadCSV.getproductsFields();
			HashMap<String,String> fieldMap = new HashMap<String,String>();
		%>
				<style>
.div1, .div2 {
      /* float: left; */
    width: 300px;
    height: 35px;
    /* margin: 10px; */
    /* padding: 10px; */
    border: 1px solid black;
}
</style>

      </head>
	
      <body>
      	<jsp:include page="navigation.jsp"/>
		<div id="main">
			<form action="UploadCSV" method="post" enctype="multipart/form-data">
				<br />
				Select File to Upload:<input type="file" name="file">
				<br>
				
				<input type="submit" value="Upload">
			</form>
			<hr>
			<!-- to do -- add functionality to modify field mapping from UI
			<form action="UploadCSV">
				 <p>Fields mapping</p>
				<input type="radio" name="fieldMapping" value="New"> New
				<input type="radio" name="fieldMapping" value="Existing"> Existing<br>
				<div class="existingMap">
					<select>
						<option value=""></option>
						<c:forEach items="<%= vendorslist%>" var="vendor">
							<option value="${vendor}">${vendor}</option>
						</c:forEach>
					</select>
				</div>	
				<table>
					<tr>
						<td>Mapping Name</td>
						<td><input type="text" name="vendorName"/></td>
					</tr>
				</table>
				<div style="width: 100%; overflow: hidden;">
					<div style="width: 500px; float: left;"> 
						<table>
							<c:forEach items="<%= productFields%>" var="pfield">
								<tr>
									<td>${pfield}</td>
									<td><input type="text" name="${pfield}"/></td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div style="margin-left: 500px;">
						<c:forEach items="${csvFields}" var="field">
							  <div  draggable="true" ondragstart="drag(event)" ondrop="drop(event)" id="${field}">
							  	${field}
							  </div>
						</c:forEach>
					</div>
				</div>
				<input type="submit" value="Upload">
			</form>
			-->
        </div>
<script>
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}
function addHidden(theForm, key, value) {
    // Create a hidden input element, and append it to the form:
    var input = document.createElement('input');
    input.type = 'hidden';
    input.name = key;'name-as-seen-at-the-server';
    input.value = value;
    theForm.appendChild(input);
}

// Form reference:
var theForm = document.getElementById("fieldMapping");
function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.target.appendChild(document.getElementById(data));
}
</script>
      </body>
	
   </html>