package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

public class UserController {
	
	private AuthService authService = new AuthService();
	private ObjectMapper mapper = new ObjectMapper();

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			
		System.out.println("=======hello from user controller");
		
		BufferedReader reader = req.getReader();
		
		StringBuilder stBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line!=null) {
			stBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stBuilder);
		
		User user = mapper.readValue(body, User.class);
		System.out.println("reached authservice");
		User authenticatedUser = authService.login(user.getUsername(), user.getPassword()).get();
		System.out.println("passed authservice");
		
		if(authenticatedUser != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", authenticatedUser);
			resp.setStatus(200);
		}else {
			resp.setStatus(401);
		}
		
	}

}