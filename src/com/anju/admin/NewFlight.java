package com.anju.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.anju.client.Destination;

/**
 * Servlet implementation class NewFlight
 */
@WebServlet("/NewFlight")
public class NewFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flight_number = request.getParameter("flight_number").trim();
		int id_airline = Integer.parseInt(request.getParameter("id_airline"));
		int id_source = Integer.parseInt(request.getParameter("id_source"));
		int id_destination = Integer.parseInt(request.getParameter("id_destination"));
		Date departure_time = null;
		try {
			System.out.println(request.getParameter("departure_time"));
			departure_time = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("departure_time"));
			System.out.println(departure_time);
		} catch (ParseException e) {			
			e.printStackTrace();
		};
		
		int number_seats = Integer.parseInt(request.getParameter("number_seats"));
		Double price_per_seat = Double.parseDouble(request.getParameter("price_per_seat"));
		
		PrintWriter pw = response.getWriter();
		if(!flight_number.equals("") && number_seats>0 && price_per_seat>0) {			
			Configuration conf = new Configuration().configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(Flight.class).addAnnotatedClass(Airline.class).addAnnotatedClass(Destination.class);
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			SessionFactory factory = conf.buildSessionFactory(registry.build());
			Session sessionf = factory.openSession();		
			Transaction trans = sessionf.beginTransaction();
			
			Airline a = new Airline();
			a.setId_airline(id_airline);
			
			Destination d = new Destination();
			d.setId_destination(id_destination);
			
			Destination s = new Destination();
			s.setId_destination(id_source);
			
			Flight f = new Flight();
			f.setFlight_number(flight_number);
			f.setNumber_seats(number_seats);
			f.setPrice_per_seat(price_per_seat);
			f.setDeparture_time(departure_time);
			f.setAirline(a);
			f.setDestination(d);
			f.setSource(s);
			sessionf.save(f);
			
			trans.commit();
	        sessionf.close();
	        RequestDispatcher rd = request.getRequestDispatcher("NewFlight.jsp");
			rd.include(request, response);
			pw.write("<span style='color:red;'>Saved Successfully..<span>");
			
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("NewFlight.jsp");
			rd.include(request, response);
			pw.write("<span style='color:red;'>Invalid Flight..<span>");
		}
	}

}
