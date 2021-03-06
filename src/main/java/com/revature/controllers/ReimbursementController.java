package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbursementController {
	
	private ReimbursementService reimbService = new ReimbursementService();
	private UserService userService = new UserService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void getReimbursements(HttpSession session, HttpServletResponse resp) throws IOException {
		User user = (User) session.getAttribute("user");
		System.out.println(user.toString());
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		if (user.getRole().equals(Role.FINANCE_MANAGER)) {
			reimbursements = reimbService.getAll();
		} else {
			reimbursements = reimbService.getReimbursementsByUser(user);
		}

		if(reimbursements.size()==0) {
			resp.setStatus(204);
		}else {
			resp.setStatus(200);
			String json = objectMapper.writeValueAsString(reimbursements);
			PrintWriter print = resp.getWriter();
			print.print(json);
		}
	}
	
	public void createReimbursement(Reimbursement reimb, HttpServletResponse resp) {
		if(reimbService.createReimbursement(reimb.getAuthor(), reimb.getAmount(), reimb.getDescription(), reimb.getReimbType())) {
			resp.setStatus(201);
		}else {
			resp.setStatus(400);
		}
	}
	
	public void updateReimbursement(Reimbursement reimb, HttpServletResponse resp) {
		Optional<User> u = Optional.ofNullable(userService.getByUsername(reimb.getResolver().getUsername()).get());
		User concreteUser = new User();
		if (u.isPresent()) {
			concreteUser = u.get();
		} else {
			concreteUser = null;
		}
		
		if (concreteUser.getRole().equals(Role.FINANCE_MANAGER)) {
			if(reimbService.process(reimbService.getReimbursementByID(reimb.getId()), reimb.getStatus(), concreteUser)) {
				resp.setStatus(200);
			}else {
				resp.setStatus(400);
			}
		} else {
			resp.setStatus(401);
		}
		

	}

}

