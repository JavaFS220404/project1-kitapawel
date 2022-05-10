package com.revature.services;

import java.util.Optional;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {
	protected UserDAO userDAO = new UserDAO();
	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public Optional<User> getByUsername(String username) {		
		Optional<User> opt = userDAO.getByUsername(username);
	    return opt;
	}
	
	public boolean createUser(String username, String password, Role role, String firstName, String lastName, String email, String phone, String address) {
		User user = new User(username, password, role, firstName, lastName, email, phone, address);
		if (userDAO.createUser(user)) {
			return true;
		}
		return false;
	}
	
//	public Optional<User> getByUserID(int userID) {
//		Optional<User> optUser = userDAO.getByUserID(userID);
//		if(optUser.isPresent()) {
//			return optUser;
//		} else {
//			Optional<User> nullOpt = Optional.of(null);
//			return nullOpt;
//		}
//	}
	
	public User getByUserID(int userID) {
		User user = userDAO.getByUserID(userID);
		return user;
	}
}
