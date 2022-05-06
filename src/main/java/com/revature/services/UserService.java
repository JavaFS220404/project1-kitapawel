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
	
	public boolean createUser() {
		User user = new User(1, "Testowy", "testowe", Role.FINANCE_MANAGER, "Jan", "Nowak", "test@test.com", "11-11-11-11", "Testowa 10");
		if (userDAO.createUser(user)) {
			return true;
		}
		return false;
	}
	
	public User getByUserID(int userID) {
	//public User getByUsername(String username) {
		return userDAO.getByUserID(userID);
		
		//Optional<User> opt = userDAO.getByUsername(userID);
	    //return opt;
	}
}
