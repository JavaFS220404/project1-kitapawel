package com.revature.services;

import com.revature.exceptions.UserDoesNotExistException;
import com.revature.exceptions.WrongPasswordException;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

import java.util.Optional;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {

    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     */
    public Optional<User> login(String username, String password) throws WrongPasswordException {
    	UserService us = new UserService();
    	UserDAO ud = new UserDAO();
    	
    	Optional<User> opt = us.getByUsername(username);
    	
    	if(opt.isPresent()) {
			if(opt.get().getPassword().equals(password)) {
				return opt;
			}else {
				throw new WrongPasswordException();
			}
		}
		//Optional<User> nullOpt = Optional.of(null);
		//return nullOpt;
    	return Optional.empty();
//    	if (loginUser != null){
//        	try {
//    			if (loginUser.getPassword().equals(password)) {
//    				System.out.println("Access Granted! Welcome!");
//    				return loginUser;
//    			} else {
//    				throw new WrongPasswordException("Wrong username or password.");
//    			}
//        	} catch(WrongPasswordException e) {
//        		return null;
//        	}
//    	} else {
//    		throw new UserDoesNotExistException("User does not exist.");
//    	}	
    }




    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
    public User register(User userToBeRegistered) {
        return null;
    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> exampleRetrieveCurrentUser() {
        return Optional.empty();
    }
}
