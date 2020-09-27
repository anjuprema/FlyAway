<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Admin - Login</title>
</head>
<body>
	<%@ include file="header.html"%>
	<div class="container">
		<form action="LoginAdmin" method="post">
			<div class='header'>
				<h2 style='margin: 5px'>Login</h2>
			</div>
			<table width='50%'>
				<tr>
					<td>User Name:</td>
					<td><input type="text" name="userName"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="Submit" value="Login"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>