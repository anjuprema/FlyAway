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
<%@ page import="com.anju.client.Booking"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Ticket</title>
<link rel="stylesheet" href="css/style.css" />
<style>
 /* Style the header */
.header {
  background-color: #f44336;
  padding: 30px 40px;
  color: white;
  text-align: center;
}
tr td{
	padding:10px;
}
select {
    width: 100%; 
    height: 25px;      
}
input[type="text"], input[type="date"], input[type="number"], input[type="email"]{
    width: 100%;  
    height: 20px;     
}
textarea{
	width: 100%; 
}
</style>
</head>
<body>
<%@ include file="header.html"%>
<%
		PrintWriter pw = response.getWriter();
		Configuration conf = new Configuration().configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Booking.class);
		StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
		SessionFactory factory = conf.buildSessionFactory(registry.build());
		Session sessionf = factory.openSession();		
		Transaction trans = sessionf.beginTransaction();
		
		Query query = sessionf.createQuery("from Booking where pnr_number='"+request.getParameter("pnr_number")+"'");
		Booking booking = (Booking) query.getSingleResult();
		
		conf.addAnnotatedClass(Flight.class).addAnnotatedClass(Airline.class).addAnnotatedClass(Destination.class);
		registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
		factory = conf.buildSessionFactory(registry.build());
		sessionf = factory.openSession();		
		trans = sessionf.beginTransaction();
		
		query = sessionf.createQuery("from Flight where id_flight="+booking.getFlightId());
		Flight flight = (Flight) query.getSingleResult();
		
		
		trans.commit();
        sessionf.close();
	%>
	<div class="container">		
		<form action="ConfirmPayment" method="post">
			<div class='header'>
				<h2 style='margin: 5px'>E-Ticket</h2>
			</div>
			<table width="100%" border="0">
				<tr>
					<td><b><i>PNR:</i></b></td>
					<td><b style="color:red;"><i> <%=booking.getPnr_number() %></i></b> </td>
					<td width="20%"><b><i>Flight Number:</i></b></td>
					<td width="30%"><b><i><%=flight.getFlight_number() %> </i></b></td>					
				</tr>
				<tr>
					<td><b><i>Departure Time:</i></b></td>
					<td><b><i><%=flight.getDeparture_time() %> </i></b> </td>					
					<td><b><i>Airline:</i></b></td>
					<td><b><i><%=flight.getAirline().getAirline_name() %>  </i></b></td>
				</tr>
				<tr>
					<td><b><i>Destination:</i></b></td>
					<td><b><i><%=flight.getSource().getDestination() %> - <%=flight.getDestination().getDestination() %> </i></b> </td>
				</tr>
				<tr>
					<td colspan="4"><hr></td>
				</tr>
				<tr>
					<td><b><i>Booked By:</i></b></td>
					<td><b><i> <%=booking.getBookedBy() %></i></b> </td>
					<td><b><i>Contact Number:</i></b></td>
					<td><b><i> <%=booking.getContactNumber() %> </i></b></td>
				</tr>
				<tr>
					<td><b><i>Contact Email:</i></b></td>
					<td><b><i> <%=booking.getContactEmail() %></i></b> </td>
					<td><b><i>Contact Address:</i></b></td>
					<td><b><i> <%=booking.getContactAddress() %></i></b> </td>
				</tr>
				<tr>
					<td><b><i>Ticket Fare:</i></b></td>
					<td><b><i><%=flight.getPrice_per_seat() %> </i></b> </td>
					<td><b><i>Total Fare:</i></b></td>
					<td><b><i> <%=booking.getTotalFare()%> </i></b></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="button" value="close" onclick="window.location='index.jsp'" ></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>