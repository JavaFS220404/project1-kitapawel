package com.revature.repositories;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    //public Optional<User> getByUsername(String username) {
    public User getByUsername(String username) {
    	
    	User user = new User(1, username, "genericPassword", Role.EMPLOYEE, "Paul", "Kita", "aav@gg.com", "12345667", "Zerowa 1");
    	
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){    		
			String sql = "SELECT * FROM ers_users WHERE ers_users_username = ?;";			
			PreparedStatement statement = conn.prepareStatement(sql);			
			statement.setString(1, username);						
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				user.setId(result.getInt("ers_users_id"));
				user.setUsername(result.getString("ers_users_username"));
				user.setPassword(result.getString("ers_users_password"));
				user.setFirstName(result.getString("ers_users_firstname"));
				user.setLastName(result.getString("ers_users_lastname"));
				user.seteMail(result.getString("ers_users_email"));
				
				int userRoleId = result.getInt("ers_user_role_id");
				if(userRoleId == 2) {
					user.setRole(Role.FINANCE_MANAGER);
				} else {
					user.setRole(Role.EMPLOYEE);
				}
				//TODO add the rest...getting tired, going to sleep;)
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		

    	return user;
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
    //public User create(User userToBeRegistered) {
    public boolean createUser(User userToBeRegistered) {
    	try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "INSERT INTO ers_users (ers_users_username, ers_users_password, ers_users_firstname, "
					+ "ers_users_lastname, ers_users_email, ers_users_phone, ers_users_address, ers_user_role_id)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, userToBeRegistered.getUsername());
			statement.setString(++count, userToBeRegistered.getPassword());
			statement.setString(++count, userToBeRegistered.getFirstName());
			statement.setString(++count, userToBeRegistered.getLastName());
			statement.setString(++count, userToBeRegistered.geteMail());
			statement.setString(++count, userToBeRegistered.getPhoneNumber());
			statement.setString(++count, userToBeRegistered.getAddress());
			int roleID;
			if (userToBeRegistered.getRole() == Role.EMPLOYEE) {
				roleID = 1;
			} else {
				roleID = 2;
			}
			statement.setInt(++count, roleID);
			
			statement.execute();
			
			return true;
			
		}		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
    	
    	//return userToBeRegistered;
    }

}
