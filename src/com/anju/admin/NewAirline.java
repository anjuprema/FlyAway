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

import com.anju.admin.Airline;

/**
 * Servlet implementation class NewAirline
 */
@WebServlet("/NewAirline")
public class NewAirline extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String airline = request.getParameter("airline_name").trim();
		PrintWriter pw = response.getWriter();
		if(!airline.equals("")) {			
			Configuration conf = new Configuration().configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(Airline.class);
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			SessionFactory factory = conf.buildSessionFactory(registry.build());
			Session sessionf = factory.openSession();		
			Transaction trans = sessionf.beginTransaction();
			
			Query query = sessionf.createQuery("from Airline where airline_name='"+airline+"'");
			List <Airline> airline_list = query.list();
			
			if(!airline_list.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("NewAirline.jsp");
				rd.include(request, response);
				pw.write("<span style='color:red;'>Airline already exists..<span>");
			}else {
				Airline a = new Airline();
				a.setAirline_name(airline);
				sessionf.save(a);
				trans.commit();
		        sessionf.close();
		        
		        RequestDispatcher rd = request.getRequestDispatcher("NewAirline.jsp");
				rd.include(request, response);
				pw.write("<span style='color:red;'>Saved Successfully..<span>");
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("NewAirline.jsp");
			rd.include(request, response);
			pw.write("<span style='color:red;'>Invalid airline name..<span>");
		}
	}

}
