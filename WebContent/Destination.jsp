<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/admin_list.css" />
<title>Admin - Destination</title>
</head>
<body>
	<%@ include file="adminHeader.html"%>
	<div class="container">
		<%
			PrintWriter pw = response.getWriter();
			InputStream is = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
			Properties property = new Properties();
			property.load(is);
			Class.forName(property.getProperty("forname"));
			Connection con = DriverManager.getConnection(property.getProperty("url"),property.getProperty("username"),property.getProperty("password"));
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM admin_destination order by destination");
			pw.write("<div class='header'><h2 style='margin:5px'>Destination</h2></div><table width='100%'>");
			while(rs.next()){
				pw.write("<tr><td>"+rs.getString(2)+"</td></tr>");
			}
			pw.write("</table>");
			con.close();
		%>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>