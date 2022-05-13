package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.UserService;


public class ERSServlet extends HttpServlet {

	private ObjectMapper mapper = new ObjectMapper();
	private UserController userController = new UserController();
	private UserService userService = new UserService();
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
				HttpSession session = req.getSession(false);
				userController.login(req, resp);
			}
			break;
		case "logout":
			if (req.getMethod().equals("GET")) {
				HttpSession session= req.getSession();
				session.invalidate();
				resp.setStatus(200);
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
				}	else if(req.getMethod().equals("POST")) {
					BufferedReader reader = req.getReader();
					
					StringBuilder stBuilder = new StringBuilder();
					
					String line = reader.readLine();
					while(line!=null) {
						stBuilder.append(line);
						line = reader.readLine();
					}
					String body = new String(stBuilder);
					System.out.println(body);
					
					Reimbursement reimb = mapper.readValue(body, Reimbursement.class);
					reimb.setAuthor((User)session.getAttribute("user"));
					
					System.out.println(reimb.toString());
					reimbController.createReimbursement(reimb, resp);
				}
					else if(req.getMethod().equals("PUT")) {
					BufferedReader reader = req.getReader();
					
					StringBuilder stBuilder = new StringBuilder();
					
					String line = reader.readLine();
					
					while(line!=null) {
						stBuilder.append(line);
						line = reader.readLine();
					}
					
					String body = new String(stBuilder);
					System.out.println(body);
					
					Map jsonJavaRootObject = new Gson().fromJson(body, Map.class);
					Integer rId = Integer.valueOf((String)jsonJavaRootObject.get("id"));
					String status = (String)jsonJavaRootObject.get("status");
					String resolver = (String)jsonJavaRootObject.get("resolver");

					
					Reimbursement reimbUpdateData = new Reimbursement();
					reimbUpdateData.setResolver(userService.getByUsername(resolver).get());
					reimbUpdateData.setId(rId);
					ReimbStatus st = ReimbStatus.PENDING;
					if(status.equals("DENIED")) {
						st = ReimbStatus.DENIED;
					} else if (status.equals("APPROVED")) {
						st = ReimbStatus.APPROVED;
					}						
					reimbUpdateData.setStatus(st);
					
					System.out.println(reimbUpdateData.toString());
					
					reimbController.updateReimbursement(reimbUpdateData, resp);

			} else {
				resp.setStatus(401);
			}
			break;
			}
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

}
	
