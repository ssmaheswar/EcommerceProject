package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.User;
import com.helper.FactoryProvider;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	try{
    		PrintWriter out = response.getWriter();
    		
    		String userName = request.getParameter("user_name");
    		String userEmail = request.getParameter("user_email");
    		String userPassword = request.getParameter("user_password");
    		String userPhone = request.getParameter("user_phone");
    		String userAddress = request.getParameter("user_address");
    		
    		// Validations
    		if(userName.isEmpty() || userEmail.isEmpty()) {
    			HttpSession httpSession =  request.getSession();
        		httpSession.setAttribute("message", " Enter Details");
        		
        		response.sendRedirect("register.jsp");
        		return;
    		}
    		
    		// Creating user object to store data
    		
    		User user = new User(userName, userEmail, userPassword, userPhone, "default.jpg", userAddress, "normal");
    		
    		Session hibernateSession = FactoryProvider.getFactory().openSession();
    		Transaction tx =  hibernateSession.beginTransaction();
    		
    		int userId = (Integer) hibernateSession.save(user);
    		
    		tx.commit();
    		hibernateSession.close();
    		
    		HttpSession httpSession =  request.getSession();
    		httpSession.setAttribute("message", "Registration Successful !! user id is "+userId);
    		
    		response.sendRedirect("register.jsp");
    		return;
    		
    		
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		HttpSession httpSession =  request.getSession();
    		httpSession.setAttribute("message", "Registration UnSuccessful , This Email Already Exists !! ");
    		
    		response.sendRedirect("register.jsp");
    		return;
    		
    		
		}
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);	
	}

}
