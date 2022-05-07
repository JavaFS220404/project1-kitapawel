package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.services.UserService;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String URI = request.getRequestURI();
		System.out.println(URI);
		
		PrintWriter print = response.getWriter();
		print.print("Hello from the ERS app");
		
		UserService userService = new UserService();
		userService.getByUsername(URI);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		String username = req.getParameter("userId");
		String password = req.getParameter("password");
		
		//You would usually make a call to the service layer now to 
		//check against the database. For the demo will just hard code
		//the username and pw in. 
		
		RequestDispatcher reqDispatcher = null;
		PrintWriter printWriter = resp.getWriter();
		
		if(username.equalsIgnoreCase("Tim")&password.equals("password")) {
			//create a session
			HttpSession session = req.getSession(); 
			session.setAttribute("username", username);
			
			//Send them to the home page
			
			reqDispatcher = req.getRequestDispatcher("home"); 
			reqDispatcher.forward(req, resp);
		}else {
			reqDispatcher = req.getRequestDispatcher("index.html");//returns static file
			reqDispatcher.include(req, resp);
			printWriter.print("<span style='color:red; text-align:center;'>"
					+ "Invalid username or password</span>");
		}
	}

}