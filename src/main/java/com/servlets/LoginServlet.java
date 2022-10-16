package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.entities.User;
import com.helper.FactoryProvider;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	try{
    		PrintWriter out = response.getWriter();
    		
    		// Coding area
    		String email = request.getParameter("email");
    		String password = request.getParameter("password");
    		
    		//Validations
    		
    		//Authenticating  user
    		UserDao userDao = new UserDao(FactoryProvider.getFactory());
    		User user = userDao.getUserByEmailAndPassword(email, password);
    		//System.out.println(user);
    		HttpSession httpSession =  request.getSession();
    		
    		if(user == null) {
    			httpSession.setAttribute("message", "Invalid Details !! Try with another one");
    			response.sendRedirect("login.jsp");
    			return;
    		}
    		else {
    			out.println("<h1>Welcome "+user.getUserName()+" </h1>");
    			
    			//login
    			httpSession.setAttribute("current-user", user);
    			
    			if(user.getUserType().equals("admin")) {
    				// Admin : admin.jsp
    				response.sendRedirect("admin.jsp");
    			}
    			else if(user.getUserType().equals("normal")) {
    				// Normal : normal.jsp
    				response.sendRedirect("normal.jsp");
    			}
    			else {
    				out.println("We have not identified user type");
    			}
    			
    			
    		}
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();    		
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
