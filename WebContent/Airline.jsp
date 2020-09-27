<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page import="org.hibernate.Session"%>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Transaction"%>
<%@ page import="org.hibernate.boot.registry.StandardServiceRegistryBuilder"%>
<%@ page import="org.hibernate.cfg.Configuration"%>
<%@ page import="com.anju.admin.Airline"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/admin_list.css" />
<title>Admin - Airline</title>
</head>
<body>
	<%@ include file="adminHeader.html"%>
	<div class="container">
		<%
			PrintWriter pw = response.getWriter();
			Configuration conf = new Configuration().configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(Airline.class);
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			SessionFactory factory = conf.buildSessionFactory(registry.build());
			Session sessionf = factory.openSession();		
			Transaction trans = sessionf.beginTransaction();
			
			Query query = sessionf.createQuery("from Airline order by airline_name");
			List <Airline> airline_list = query.getResultList();
			pw.write("<div class='header'><h2 style='margin:5px'>Airline</h2></div><table width='100%'>");
			for(Airline a : airline_list ){
				pw.write("<tr><td>"+ a.getAirline_name() +"</td></tr>");
			}
			pw.write("</table>");
			
			trans.commit();
	        sessionf.close();
		%>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>