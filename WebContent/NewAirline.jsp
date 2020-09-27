<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<title>Create New Airline</title>
</head>
<body>
	<%@ include file="adminHeader.html"%>
	<div class="container">
		<form action="NewAirline" method="post">
			<div class='header'>
				<h2 style='margin: 5px'>Create New Airline</h2>
			</div>
			<table width='50%' style="padding:20px 0 30px 0;">
				<tr>
					<td><span style="color:red;">*</span>Enter Airline:</td>
					<td><input type="text" name="airline_name" id="airline_name"></td>
				</tr>
				<tr>
					<td><input type="submit" name="submit" value="Save"></td>
					<td></td>
				</tr>			
			</table>
		</form>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>