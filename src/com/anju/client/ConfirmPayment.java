package com.anju.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

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

/**
 * Servlet implementation class ConfirmPayment
 */
@WebServlet("/ConfirmPayment")
public class ConfirmPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_booking = Integer.parseInt(request.getParameter("id_booking"));
		if(id_booking > 0) {
			PrintWriter pw = response.getWriter();
			Configuration conf = new Configuration().configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(Booking.class);
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			SessionFactory factory = conf.buildSessionFactory(registry.build());
			Session sessionf = factory.openSession();		
			Transaction trans = sessionf.beginTransaction();
			
			//create random pnr number
			SecureRandom random = new SecureRandom();
			int num = random.nextInt(100000);
			String pnr = "PNR-"+String.format("%05d", num);
			
			String hqlUpdate = "update Booking set payment_done=:payment, pnr_number=:pnr where id_booking=:id";
			sessionf.createQuery( hqlUpdate ).setParameter("payment", true).setParameter("pnr", pnr).setParameter("id", id_booking).executeUpdate();

			trans.commit();
			sessionf.close();
			
			RequestDispatcher rd = request.getRequestDispatcher("ShowBookingDetail.jsp?pnr_number="+pnr);
			rd.include(request, response);
		}
	}

}
