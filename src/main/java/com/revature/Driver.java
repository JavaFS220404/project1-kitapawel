package com.revature;

import com.revature.models.ReimbStatus;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class Driver {

    public static void main(String[] args) {
    	UserService us = new UserService();
//    	System.out.println(us.getByUsername(null));
//    	System.out.println(us.getByUsername("madn"));
//    	System.out.println(us.getByUserID(4));
//    	
//    	us.createUser();
//    	
//    	ReimbursementDAO rd = new ReimbursementDAO();
//    	rd.getById(12314);
//    	
    	System.out.println("================================================");
    	
    	ReimbursementService rs = new ReimbursementService();
//    	System.out.println(rs.getReimbursementByID(2));
//    	System.out.println(rs.getReimbursementsByStatus(ReimbStatus.APPROVED));
//    	System.out.println(rs.getReimbursementsByStatus(ReimbStatus.DENIED));
//    	System.out.println(rs.getReimbursementsByStatus(ReimbStatus.PENDING));

    	rs.process(rs.getReimbursementByID(21), ReimbStatus.DENIED, us.getByUserID(4));
//    	//rs.createReimbursement();
    	

    	
    }
}