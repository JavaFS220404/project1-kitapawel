package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.UserService;

public class MenuServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String URI = request.getRequestURI();
		System.out.println(URI);
		
		PrintWriter print = response.getWriter();
		print.print("Hello from the ERS app");
		
		UserService userService = new UserService();
		userService.getByUsername(URI);
		
	}
	
}
