package com.anju.client;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.anju.admin.User;

/**
 * Servlet implementation class ViewBookingInfo
 */
@WebServlet("/ViewBookingInfo")
public class ViewBookingInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pnr = request.getParameter("pnr_number").trim();
		if(!pnr.equals("")) {
			PrintWriter pw = response.getWriter();
			Configuration conf = new Configuration().configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(Booking.class);
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			SessionFactory factory = conf.buildSessionFactory(registry.build());
			Session sessionf = factory.openSession();		
			Transaction trans = sessionf.beginTransaction();
			
			Query query = sessionf.createQuery("from Booking where pnr_number='"+pnr+"'");
			List <Booking> booking_list = query.list();
			
			if(!booking_list.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("ShowBookingDetail.jsp?pnr_number="+pnr);
				rd.include(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("ViewBooking.jsp");
				rd.include(request, response);
				pw.write("<span style='color:red;'>Invalid PNR number..<span>");
			}
		}
	}

}
