package com.anju.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password").trim();
		String confirm_password = request.getParameter("confirm_password").trim();
		PrintWriter pw = response.getWriter();
		
		if(password.equals("") || confirm_password.contentEquals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
			rd.include(request, response);
			pw.write("<div style='color:red;'>Invalid Data..</div>");
		}else {
			if(!password.equals(confirm_password)) {
				RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
				rd.include(request, response);
				pw.write("<div style='color:red;'>Password & Confirm Password does not match..</div>");
			}else {
				Configuration conf = new Configuration().configure("hibernate.cfg.xml");
				conf.addAnnotatedClass(User.class);
				StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
				SessionFactory factory = conf.buildSessionFactory(registry.build());
				Session session = factory.openSession();		
				Transaction trans = session.beginTransaction();
				
				/*change pasword*/
				HttpSession hsession = request.getSession();
				User user = new User();
				user.setId_user((int) hsession.getAttribute("id_user"));
				user.setUser_name((String) hsession.getAttribute("user_name"));
				user.setPassword(password);
				session.update(user);
				trans.commit();
		        session.close();
				
				/*prompt user to login again*/
				hsession.invalidate();
				RequestDispatcher rd = request.getRequestDispatcher("LoginAdmin.jsp");
				rd.include(request, response);
				pw.write("<div style='color:red;'>Password has been updated. Please login again..</div>");
				
				
			}
		}
	}

}
