<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<style>
 /* Style the header */
.header {
  background-color: #f44336;
  padding: 30px 40px;
  color: white;
  text-align: center;
}
</style>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css" />
<title>Admin - Change Password</title>
</head>
<body>
	<%@ include file="adminHeader.html"%>
	<%
		PrintWriter pw = response.getWriter();		
	%>
	<div class="container">
		<form action="ChangePassword" method="post">
			<div class='header'>
				<h2 style='margin: 5px'>Change Password</h2>
			</div>
			<table width='50%'>
				<tr>
					<td>User Name:</td>
					<td><b><i><%=session.getAttribute("user_name")%></i></b></td>
				</tr>
				<tr>
					<td>New Password:</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td>Confirm Password:</td>
					<td><input type="password" name="confirm_password"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="Submit" value="Save"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>