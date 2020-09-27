<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page import="org.hibernate.Session"%>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Transaction"%>
<%@ page import="org.hibernate.boot.registry.StandardServiceRegistryBuilder"%>
<%@ page import="org.hibernate.cfg.Configuration"%>
<%@ page import="com.anju.admin.Flight"%>
<%@ page import="com.anju.admin.Airline"%>
<%@ page import="com.anju.client.Destination"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Fly Away</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/admin_list.css" />
</head>
<body>
	<%@ include file="header.html"%>
	<div class="container">
		
			<%
			
			PrintWriter pw = response.getWriter();
			Configuration conf = new Configuration().configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(Flight.class).addAnnotatedClass(Airline.class).addAnnotatedClass(Destination.class);
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			SessionFactory factory = conf.buildSessionFactory(registry.build());
			Session sessionf = factory.openSession();		
			Transaction trans = sessionf.beginTransaction();
			
			Query query = sessionf.createQuery("from Flight where DATE(departure_time) = DATE(now())");
			List <Flight> flight_list = query.getResultList();
			pw.write("<div class='header'><h2 style='margin:5px'>Today's Flight</h2></div><table width='100%'>");
			pw.write("<tr><th>Flight Number</th><th>Airline</th><th>Source/Destination</th><th>Departure Time</th><th>Total Seats</th><th>Fare</th></tr>");
			
			if(!flight_list.isEmpty())
			for(Flight f : flight_list ){
				pw.write("<tr>");
				pw.write("<td>"+ f.getFlight_number() +"</td>");
				pw.write("<td>"+ f.getAirline().getAirline_name() +"</td>");
				pw.write("<td>"+ f.getSource().getDestination() +" - "+ f.getDestination().getDestination() + "</td>");
				pw.write("<td>"+ f.getDeparture_time() +"</td>");
				pw.write("<td>"+ f.getNumber_seats() +"</td>");
				pw.write("<td>"+ f.getPrice_per_seat() +"</td>");
				pw.write("<td><a href='BookFlight.jsp?id_flight="+ f.getId_flight() +"'><img src='images/book.png' width='70' height='30' alt='Book'></a></td>");
				pw.write("</tr>");
			}
			pw.write("</table>");
			
			trans.commit();
	        sessionf.close();
			
			%>
		
	</div>
	<%@ include file="footer.html"%>
</body>
</html>