package com.revature;

import com.revature.models.ReimbStatus;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class Driver {

    public static void main(String[] args) {
    	UserService us = new UserService();
    	System.out.println(us.getByUsername(null));
    	System.out.println(us.getByUsername("madn"));
    	System.out.println(us.getByUserID(4));
    	
    	//us.createUser();
    	
    	ReimbursementDAO rd = new ReimbursementDAO();
    	rd.getById(12314);
    	
    	System.out.println("================================================");
    	
    	ReimbursementService rs = new ReimbursementService();
    	rs.getReimbursementsByStatus(ReimbStatus.APPROVED);
    	rs.getReimbursementsByStatus(ReimbStatus.DENIED);
    	rs.getReimbursementsByStatus(ReimbStatus.PENDING);
    	//rs.createReimbursement();
    	

    	
    }
}