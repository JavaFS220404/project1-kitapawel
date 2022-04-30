package com.revature.repositories;
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
    public Optional<User> getByUsername(String username) {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM homes WHERE home_name = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				User user = new User();
				user.setUsername(result.getString("home_name"));
				//TODO add the rest...getting tired, going to sleep;)
				return user;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
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
        return userToBeRegistered;
    }
    

}
