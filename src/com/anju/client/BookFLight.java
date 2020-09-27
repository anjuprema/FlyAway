package com.anju.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.anju.admin.Airline;
import com.anju.admin.Flight;

/**
 * Servlet implementation class BookFLight
 */
@WebServlet("/BookFLight")
public class BookFLight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Configuration conf = new Configuration().configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Booking.class).addAnnotatedClass(Flight.class).addAnnotatedClass(Airline.class).addAnnotatedClass(Destination.class);
		StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
		SessionFactory factory = conf.buildSessionFactory(registry.build());
		Session sessionf = factory.openSession();		
		Transaction trans = sessionf.beginTransaction();
		int ticketCount = Integer.parseInt(request.getParameter("ticket_count"));
		double ticketFare = Double.parseDouble(request.getParameter("price_per_seat"));
		
		Double totalFare = ticketCount * ticketFare;
		
		//Flight flight = new Flight();
		//flight.setId_flight(Integer.parseInt(request.getParameter("id_flight")));
		
		Booking bookFlight = new Booking();
		bookFlight.setFlightId((Integer.parseInt(request.getParameter("id_flight"))));
		bookFlight.setBookedBy(request.getParameter("booked_by"));
		bookFlight.setContactNumber(request.getParameter("contact_number"));
		bookFlight.setContactEmail(request.getParameter("contact_email"));
		bookFlight.setContactAddress(request.getParameter("contact_address"));
		bookFlight.setTicketCount(ticketCount);
		bookFlight.setTotalFare(totalFare);
		
		int bookingId = (int) sessionf.save(bookFlight);
		trans.commit();
        sessionf.close();
		request.setAttribute("id_booking", bookingId);
		RequestDispatcher rd = request.getRequestDispatcher("MakePayment.jsp?id_booking="+bookingId+"&total_fare="+totalFare);
		rd.include(request, response);
		
				

		
	}

}
