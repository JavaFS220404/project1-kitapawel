package com.revature;

import java.sql.Timestamp;

import com.revature.controllers.UserController;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class Driver {

    public static void main(String[] args) {
    	
    	UserService us = new UserService();
    	UserDAO ud = new UserDAO();
    	ReimbursementDAO rd = new ReimbursementDAO(); 
    	ReimbursementService rs = new ReimbursementService();
    	
    	
//    	System.out.println(us.getByUsername(null));
//    	System.out.println(us.getByUsername("madn"));
//    	System.out.println(us.getByUserID(4));
//    	System.out.println(us.getByUsername("deadd"));
//    	System.out.println(us.getByUserID(2211));
//    	System.out.println(ud.getByUsername("madn"));
//    	System.out.println(ud.getByUsername("madnf"));
//    	
//    	us.createUser("Tom", "abc", Role.EMPLOYEE, "Tomas", "Woseba", "Tom@wos.com", "12223-456", "Topalawa 12");

//    	   	
//    	
    	System.out.println("================================================");
    	
    	System.out.println(rs.getAll());
//    	System.out.println(rs.getReimbursementByID(2));
//    	System.out.println(rs.getReimbursementsByStatus(ReimbStatus.APPROVED));
//    	System.out.println(rs.getReimbursementsByStatus(ReimbStatus.DENIED));
//    	System.out.println(rs.getReimbursementsByStatus(ReimbStatus.PENDING));
//    	System.out.println(rs.getReimbursementByID(1111));

    	//rs.process(rs.getReimbursementByID(6), ReimbStatus.APPROVED, us.getByUserID(1));
//    	rs.createReimbursement(22, "a", ReimbType.FOOD);
//    	rs.createReimbursement(56, "aaa", ReimbType.OTHER);
//    	rs.createReimbursement(214, "bb", ReimbType.TRAVEL);
//    	rs.createReimbursement(320, "bbb", ReimbType.LODGING);
    	
    	System.out.println("================================================");
    	AuthService as = new AuthService();
    	


    	
    }
}