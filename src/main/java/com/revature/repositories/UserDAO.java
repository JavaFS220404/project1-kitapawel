package com.revature.repositories;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
    	User user = new User();

    			try {
    				Connection conn = ConnectionFactory.getConnection();
    				String sql = "SELECT * FROM ers_users WHERE ers_users_username = ?;";
    				
    				PreparedStatement statement = conn.prepareStatement(sql);
    				
    				statement.setString(1, username);
    				
    				ResultSet result = statement.executeQuery();
    				
    				Role userRole;
    				if (result.getInt("ers_user_role_id") == 1) {
    					userRole = Role.EMPLOYEE;
    				} else if (result.getInt("ers_user_role_id") == 2) {
    					userRole = Role.FINANCE_MANAGER;
    				} else {
    					userRole = Role.EMPLOYEE;
    				}    				

    				    user.setId(result.getInt("ers_users_id"));
    				    user.setUsername(result.getString("ers_users_username"));
    				    user.setPassword(result.getString("ers_users_password"));
    				    user.setUserFirstName(result.getString("ers_users_firstname")); 
    				    user.setUserLastName(result.getString("ers_users_lastname"));
    				    user.setUserEmail(result.getString("ers_users_email"));
    				    user.setRole(userRole);

    				} catch(SQLException e) {
    				e.printStackTrace();
    				}    			
    			
    	return Optional.ofNullable(user);
    }


    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
    	try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO ers_users (ers_users_username, ers_users_password, ers_users_firstname, ers_users_lastname, ers_users_email, ers_user_role_id)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 1;
						
			statement.setString(++count, userToBeRegistered.getUsername());
			statement.setString(++count, userToBeRegistered.getPassword());
			statement.setString(++count, userToBeRegistered.getUserFirstName());
			statement.setString(++count, userToBeRegistered.getUserLastName());
			statement.setString(++count, userToBeRegistered.getUserEmail());
			int roleNo;
			if (userToBeRegistered.getRole() == Role.FINANCE_MANAGER) {
				roleNo = 2;
			} else {
				roleNo = 1;
			}			
			statement.setInt(++count, roleNo);

			
			statement.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

    	return userToBeRegistered;
    } 
   
    
    

}
