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
<%@ page import="javax.servlet.annotation.WebServlet"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<title>Book Flight</title>
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
			
			Query query = sessionf.createQuery("from Flight where id_flight="+request.getParameter("id_flight"));
			Flight flight = (Flight) query.getSingleResult();
			
			
			trans.commit();
	        sessionf.close();
		%>
		<form action="BookFLight" method="post">
			<div class='header'>
				<h2 style='margin: 5px'>Book Flight</h2>
			</div>
			<table width="100%" border="0">
				<tr>
					<td width="20%"><b><i>Flight Number:</i></b></td>
					<td width="30%"><b><i> <%=flight.getFlight_number() %> </i></b></td>
					<td width="20%"><b><i>Fare:</i></b></td>
					<td width="30%"><b><i> <%=flight.getPrice_per_seat() %> </i></b></td>
				</tr>
				<tr>
					<td><b><i>Departure Time:</i></b></td>
					<td><b><i> <%=flight.getDeparture_time() %></i></b> </td>
					<td><b><i>Airline:</i></b></td>
					<td><b><i> <%=flight.getAirline().getAirline_name() %> </i></b></td>
				</tr>
				<tr>
					<td colspan="4"><hr></td>
				</tr>
				<tr>
					<td><span style="color:red;">*</span>Name:</td>
					<td>
						<input type="text" name="booked_by" id="booked_by" required>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>					
					<td><span style="color:red;">*</span>Phone Number:</td>
					<td>
						<input type="number" name="contact_number" id="contact_number" required>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><span style="color:red;">*</span>Email:</td>
					<td>
						<input type="email" name="contact_email" id="contact_email" required>
					</td>
					<td></td>
					<td></td>					
				</tr>
				<tr>					
					<td><span style="color:red;">*</span>No.of Tickets:</td>
					<td>
						<input type="number" name="ticket_count" id="ticket_count" required>
					</td>
					<td></td>
					<td></td>					
				</tr>
				<tr>
					<td><span style="color:red;">*</span>Address:</td>
					<td>
						<textarea id="contact_address" name="contact_address" rows="4" required></textarea>
					</td>
					<td></td>
					<td></td>					
				</tr>
				<tr>									
					<td><input type="hidden" name="id_flight" value="<%=request.getParameter("id_flight")%>"></td>
					<td>
						<div><input type="submit" name="submit" value="Continue To Pay"></div>
					</td>
					<td><input type="hidden" name="price_per_seat" value="<%=flight.getPrice_per_seat()%>"></td>	
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>