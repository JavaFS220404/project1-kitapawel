package com.revature;

import com.revature.controllers.MenuController;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;

public class Driver {

    public static void main(String[] args) {
    	UserService us = new UserService();
    	System.out.println(us.getByUsername(null));
    	System.out.println(us.getByUsername("madn"));
    	
    	//us.createUser();
    	
    	ReimbursementDAO rd = new ReimbursementDAO();
    	rd.getById(12314);

    	
    }
}