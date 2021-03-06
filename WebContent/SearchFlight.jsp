<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page import="org.hibernate.Session"%>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Transaction"%>
<%@ page import="org.hibernate.boot.registry.StandardServiceRegistryBuilder"%>
<%@ page import="org.hibernate.cfg.Configuration"%>
<%@ page import="com.anju.admin.Airline"%>
<%@ page import="com.anju.client.Destination"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Flight</title>
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
input[type="text"], input[type="date"], input[type="number"] {
    width: 100%;  
    height: 20px;     
}
</style>
</head>
<body>
	<%@ include file="header.html"%>
	<%
		/*get airline*/
		Configuration conf = new Configuration().configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Airline.class);
		StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
		SessionFactory factory = conf.buildSessionFactory(registry.build());
		Session sessionf = factory.openSession();		
		Transaction trans = sessionf.beginTransaction();
		
		Query query = sessionf.createQuery("from Airline order by airline_name");
		List <Airline> airline_list = query.getResultList();
		
		/*get destination*/
		conf.addAnnotatedClass(Destination.class);
		registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
		factory = conf.buildSessionFactory(registry.build());
		sessionf = factory.openSession();		
		trans = sessionf.beginTransaction();
		
		query = sessionf.createQuery("from Destination order by destination");
		List <Destination> destination_list = query.getResultList();
		
		/*get source/destination*/
		
	trans.commit();
    sessionf.close();
	%>
	<div class="container">
		<form action="SearchResult.jsp" method="post">
			<div class='header'>
				<h2 style='margin: 5px'>Search Flight</h2>
			</div>
			<table width='50%'>
				<tr>
					<td>Travel Date:</td>
					<td><input type="date" name="departure_time" id="departure_time" data-date-format="DD MMMM YYYY"></td>
				</tr>
				<tr>
					<td>Source:</td>
					<td>
						<select id="id_source" name="id_source">
							<option value="0">- Select -</option>
						  <%
						  for(Destination d : destination_list ){ %>
								<option value="<%=d.getId_destination()%>"><%=d.getDestination()%></option>
							<% }
						  %>
						</select>
					</td>
				</tr>
				<tr>
					<td>Destination:</td>
					<td>
						<select id="id_destination" name="id_destination">
							<option value="0">- Select -</option>
						  <%
						  for(Destination d : destination_list ){ %>
								<option value="<%=d.getId_destination()%>"><%=d.getDestination()%></option>
							<% }
						  %>
						</select>
					</td>
				</tr>
				<tr>
					<td>Number of Seats:</td>
					<td>
						<input type="number" name="number_seats" id="number_seats">
					</td>
				</tr>
				<tr>
					<td>Airline:</td>
					<td>
						<select id="id_airline" name="id_airline">
							<option value="0">- Select -</option>
						  <%
						  for(Airline a : airline_list ){ %>
								<option value="<%=a.getId_airline()%>"><%=a.getAirline_name()%></option>
							<% }
						  %>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div><input type="submit" name="submit" value="Search"></div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>