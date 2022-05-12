package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.UserDoesNotExistException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

public class UserController {
	
	private AuthService authService = new AuthService();
	private UserService userService = new UserService();
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
		
		Optional<User> authenticatedUser = authService.login(user.getUsername(), user.getPassword());
		

		System.out.println("passed authservice");
		
		if(authenticatedUser.isPresent()) {
			HttpSession session = req.getSession();
			session.setAttribute("user", authenticatedUser.get());
			
			resp.setStatus(200);
			//Integer roleID = 1;
			//if (authenticatedUser.get().getRole().equals(Role.FINANCE_MANAGER)) {
			//	roleID = 2;
			//}
			//resp.getOutputStream().write(roleID);			
			System.out.println("sent status 200 from usercontroller");
		}else {
			resp.setStatus(401);
		}
		//resp.getOutputStream().flush();
	}
	
	public void createUser(User user, HttpServletResponse resp) {
		if(userService.createUser(user.getUsername(), user.getPassword(), user.getRole(), user.getFirstName(), user.getLastName(), user.geteMail(), user.getPhoneNumber(), user.getAddress())) {
			System.out.println("hello from UserController/createUser");
			resp.setStatus(201);
		}else {
			resp.setStatus(400);
		}
	}

}