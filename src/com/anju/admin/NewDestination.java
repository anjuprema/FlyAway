package com.anju.admin;

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

import com.anju.client.Destination;

/**
 * Servlet implementation class NewDestination
 */
@WebServlet("/NewDestination")
public class NewDestination extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = request.getParameter("destination").trim();
		PrintWriter pw = response.getWriter();
		if(!destination.equals("")) {			
			Configuration conf = new Configuration().configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(Destination.class);
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			SessionFactory factory = conf.buildSessionFactory(registry.build());
			Session sessionf = factory.openSession();		
			Transaction trans = sessionf.beginTransaction();
			
			Query query = sessionf.createQuery("from Destination where destination='"+destination+"'");
			List <Destination> destination_list = query.list();
			
			if(!destination_list.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("NewDestination.jsp");
				rd.include(request, response);
				pw.write("<span style='color:red;'>Destination already exists..<span>");
			}else {
				Destination d = new Destination();
				d.setDestination(destination);
				sessionf.save(d);
				trans.commit();
		        sessionf.close();
		        RequestDispatcher rd = request.getRequestDispatcher("NewDestination.jsp");
				rd.include(request, response);
				pw.write("<span style='color:red;'>Saved Successfully..<span>");
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("NewDestination.jsp");
			rd.include(request, response);
			pw.write("<span style='color:red;'>Invalid destination name..<span>");
		}
		
	}

}
