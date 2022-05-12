package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.models.User;


public class ERSServlet extends HttpServlet {

	private ObjectMapper mapper = new ObjectMapper();
	private UserController userController = new UserController();
	private ReimbursementController reimbController = new ReimbursementController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("=======hello from ERS servlet");
		
		resp.setContentType("application/json");

		resp.setStatus(404);

		final String URL = req.getRequestURI().replace("/ERS/app/", "");

		String[] UrlSections = URL.split("/");
		
		System.out.println("Split section: " + UrlSections[0]);
		
		switch (UrlSections[0]) {
		case "login":
			if (req.getMethod().equals("POST")) {
				userController.login(req, resp);
			}
			break;
		case "register":
			if (req.getMethod().equals("GET")) {

			} else if (req.getMethod().equals("POST")) {
				BufferedReader reader = req.getReader();

				StringBuilder stBuilder = new StringBuilder();
				String line = reader.readLine();
				while(line!=null) {
					stBuilder.append(line);
					line = reader.readLine();
				}
				String body = new String(stBuilder);

				User user = mapper.readValue(body, User.class);
				System.out.println(user.toString());
				userController.createUser(user, resp);
			} else {
				resp.setStatus(401);
			}
			break;
		case "reimbursements":
			HttpSession session = req.getSession(false);
			if (session != null) {
				if (req.getMethod().equals("GET")) {
					reimbController.getReimbursements(session, resp);
				}
//					else if(req.getMethod().equals("POST")) {
//					BufferedReader reader = req.getReader();
//					
//					StringBuilder stBuilder = new StringBuilder();
//					
//					String line = reader.readLine();
//					
//					while(line!=null) {
//						stBuilder.append(line);
//						line = reader.readLine();
//					}
//					
//					String body = new String(stBuilder);
//					System.out.println(body);
//					
//					Todo todo = mapper.readValue(body, Todo.class);
//					todo.setCreator((User)session.getAttribute("user"));
//					todoController.addTodo(todo, resp);
//				}else if(req.getMethod().equals("PUT")) {
//					BufferedReader reader = req.getReader();
//					
//					StringBuilder stBuilder = new StringBuilder();
//					
//					String line = reader.readLine();
//					
//					while(line!=null) {
//						stBuilder.append(line);
//						line = reader.readLine();
//					}
//					
//					String body = new String(stBuilder);
//					System.out.println(body);
//					
//					Todo todo = mapper.readValue(body, Todo.class);
//					todo.setCreator((User)session.getAttribute("user"));
//					todoController.updateTodo(todo, resp);
//				}
			} else {
				resp.setStatus(401);
			}
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//		throws ServletException, IOException{
//		
//		BufferedReader reader = req.getReader();
//		StringBuilder stringBuilder = new StringBuilder();
//		String line = reader.readLine(); // Gets first line from buffered reader
//		while (line != null) {
//			stringBuilder.append(line);
//			line = reader.readLine(); // Gets the next line, returns null at end of body.
//		}
//		String body = new String(stringBuilder);
//		
//		IncomingAuthenticationData user = new ObjectMapper().readValue(body, IncomingAuthenticationData.class);
//		
//		AuthService authService = new AuthService();
//		
//		User potentiallyLoggedInUser = authService.login(user.getUn(), user.getPwd()); 
//		
//		if (potentiallyLoggedInUser != null){
//			System.out.println("Login successful");
//			resp.setStatus(201);
//		} else {
//			System.out.println("Login not successful");
//			resp.setStatus(406);
//		}
//	}
}
	
