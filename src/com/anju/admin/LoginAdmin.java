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
import javax.servlet.http.HttpSession;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



/**
 * Servlet implementation class LoginAdmin
 */
@WebServlet("/LoginAdmin")
public class LoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdmin() {
        super();
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		PrintWriter pw = response.getWriter();
		/*check for blank value*/
		if(uname.equals("") || password.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("LoginAdmin.jsp");
			rd.include(request, response);
			pw.write("<div style='color:red;'>Invalid User Name, Password</div>");
		}else {
			Configuration conf = new Configuration().configure("hibernate.cfg.xml");
			conf.addAnnotatedClass(User.class);
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			SessionFactory factory = conf.buildSessionFactory(registry.build());
			Session session = factory.openSession();		
			Transaction trans = session.beginTransaction();
			
			Query query = session.createQuery("from User where user_name=:uname and password=:pswd");
			query.setString("uname", uname);
			query.setString("pswd", password);
			
			//System.out.println(user.getUser_name());
			List <User> user_list = query.list();
			if(user_list.isEmpty()) {
				/*Incorrect Username & Password*/
				RequestDispatcher rd = request.getRequestDispatcher("LoginAdmin.jsp");
				rd.include(request, response);
				pw.write("<div style='color:red;'>Incorrect User Name, Password</div>");
			}else {
				User user = new User();
				user = (User) query.getSingleResult();
				
				/*Login Successful*/
				HttpSession Hsession = request.getSession();
				Hsession.setAttribute("hasAdminLogedIn",true);
				Hsession.setAttribute("id_user", user.getId_user());
				Hsession.setAttribute("user_name", user.getUser_name());
				response.sendRedirect("AdminDashboard.jsp");
			}
	        trans.commit();
	        session.close();
		}
	}

}
