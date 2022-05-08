package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
	//public Optional<Reimbursement> getById(int id) {
	public Reimbursement getById(int id) {

		Reimbursement reimb = new Reimbursement();

		try(Connection conn = ConnectionFactory.getInstance().getConnection()){    		
			String sql = "SELECT * FROM ers_reimbursements WHERE ers_reimb_id = ?;";			
			PreparedStatement statement = conn.prepareStatement(sql);			
			statement.setInt(1, id);						
			ResultSet result = statement.executeQuery();

			//Optional<User> user = new Optional<User>();
			UserService us = new UserService();

			while(result.next()) {				
				reimb.setId(result.getInt("ers_reimb_id"));
				reimb.setAmount(result.getDouble("ers_reimb_amount"));
				reimb.setSubmitted(result.getTimestamp("ers_reimb_submitted"));
				reimb.setResolved(result.getTimestamp("ers_reimb_resolved"));
				reimb.setDescription(result.getString("ers_reimb_descr"));

				int tempUserID = result.getInt("ers_reimb_author");
				User tempUser = us.getByUserID(tempUserID);
				reimb.setAuthor(tempUser);

				tempUserID = result.getInt("ers_reimb_resolver");
				tempUser = us.getByUserID(tempUserID);
				reimb.setResolver(tempUser);
				

				int reimbStatusID = result.getInt("ers_reimb_status_id");
				if(reimbStatusID == 1) {
					reimb.setStatus(ReimbStatus.APPROVED);
				} else if (reimbStatusID == 2) {
					reimb.setStatus(ReimbStatus.DENIED);
				} else {
					reimb.setStatus(ReimbStatus.PENDING);
				}

				int reimbTypeID = result.getInt("ers_reimb_type_id");
				if(reimbTypeID == 1) {
					reimb.setReimbType(ReimbType.FOOD);
				} else if (reimbTypeID == 2) {
					reimb.setReimbType(ReimbType.LODGING);
				} else if (reimbTypeID == 3) {
					reimb.setReimbType(ReimbType.TRAVEL);
				} else {
					reimb.setReimbType(ReimbType.OTHER);
				}

			}

		}catch(SQLException e) {
			e.printStackTrace();
		}		

		//Optional<Reimbursement> optReimb = Optional.ofNullable(reimb);
		return reimb;

	}


    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(ReimbStatus status) {
    	
    	List<Reimbursement> reimbList = new ArrayList<>();
    	UserService us = new UserService();
    	
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){    		
			String sql = "SELECT * FROM ers_reimbursements WHERE ers_reimb_status_id = ?;";			
			PreparedStatement statement = conn.prepareStatement(sql);			
			
			int tempStatusId;
			switch(status){
				case APPROVED:
					tempStatusId = 1;
					break;
				case DENIED:
					tempStatusId = 2;
					break;
				case PENDING:
					tempStatusId = 3;
					break;
				default:
					tempStatusId = 3;
					break;
			}			
			statement.setInt(1, tempStatusId);						
			ResultSet result = statement.executeQuery();			
			
			while(result.next()) {
		    	Reimbursement reimb = new Reimbursement();
				reimb.setId(result.getInt("ers_reimb_id"));
				reimb.setAmount(result.getDouble("ers_reimb_amount"));
				reimb.setSubmitted(result.getTimestamp("ers_reimb_submitted"));
				reimb.setResolved(result.getTimestamp("ers_reimb_resolved"));
				reimb.setDescription(result.getString("ers_reimb_descr"));

				int tempUserID = result.getInt("ers_reimb_author");
				User tempUser = us.getByUserID(tempUserID);
				reimb.setAuthor(tempUser);

				tempUserID = result.getInt("ers_reimb_resolver");
				tempUser = us.getByUserID(tempUserID);
				reimb.setResolver(tempUser);
				

				int reimbStatusID = result.getInt("ers_reimb_status_id");
				if(reimbStatusID == 1) {
					reimb.setStatus(ReimbStatus.APPROVED);
				} else if (reimbStatusID == 2) {
					reimb.setStatus(ReimbStatus.DENIED);
				} else {
					reimb.setStatus(ReimbStatus.PENDING);
				}

				int reimbTypeID = result.getInt("ers_reimb_type_id");
				if(reimbTypeID == 1) {
					reimb.setReimbType(ReimbType.FOOD);
				} else if (reimbTypeID == 2) {
					reimb.setReimbType(ReimbType.LODGING);
				} else if (reimbTypeID == 3) {
					reimb.setReimbType(ReimbType.TRAVEL);
				} else {
					reimb.setReimbType(ReimbType.OTHER);
				}				
				
				reimbList.add(reimb);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
    	
    	return reimbList;
    }

    public boolean createReimbursement(Reimbursement reimbursementToBeRegistered) {
    	
    	try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "INSERT INTO ers_reimbursements (ers_reimb_amount, ers_reimb_submitted, ers_reimb_resolved, "
					+ "ers_reimb_descr, ers_reimb_author, ers_reimb_status_id, ers_reimb_type_id)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			//statement.setInt(++count, 1);
			statement.setDouble(++count, reimbursementToBeRegistered.getAmount());
			statement.setTimestamp(++count, reimbursementToBeRegistered.getSubmitted());
			statement.setTimestamp(++count, null);
			statement.setString(++count, reimbursementToBeRegistered.getDescription());

			int tempVar = 1;
			//TODO get logged in user's ID
			statement.setInt(++count, tempVar);
			
			//if (reimbursementToBeRegistered.getStatus() == ReimbStatus.APPROVED) {tempVar = 1;}
			//else if(reimbursementToBeRegistered.getStatus() == ReimbStatus.DENIED) {tempVar = 2;}
			//else {tempVar = 3;}
			statement.setInt(++count, 3); // hardcoded as we create with pending always
			
			if (reimbursementToBeRegistered.getReimbType() == ReimbType.FOOD) {tempVar = 1;}
			else if(reimbursementToBeRegistered.getReimbType() == ReimbType.LODGING) {tempVar = 2;}
			else if(reimbursementToBeRegistered.getReimbType() == ReimbType.TRAVEL) {tempVar = 3;}
			else {tempVar = 4;}
			statement.setInt(++count, tempVar);
			
			statement.execute();
			
			return true;
			
		}		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
    	
    	//return userToBeRegistered;
    }
    
    
    
    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE ers_reimbursements"
			+ "SET ers_reimb_resolver = ?, ers_reimb_resolved = ?, ers_reimb_status_id = ?"
			+ "WHERE ers_reimb_id = ?;"
			+ "VALUES (?, ?, ?, ?)";
			
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			
			int count = 0;

			prepStatement.setInt(++count, unprocessedReimbursement.getResolver().getId());
			prepStatement.setTimestamp(++count, unprocessedReimbursement.getResolved());
			int tempVar = 1;			
			if (unprocessedReimbursement.getStatus() == ReimbStatus.APPROVED) {tempVar = 1;}
			else if(unprocessedReimbursement.getStatus() == ReimbStatus.DENIED) {tempVar = 2;}
			else {tempVar = 3;}
			prepStatement.setInt(++count, tempVar);
			prepStatement.setInt (++count, unprocessedReimbursement.getId());
//			
//			prepStatement.setInt(1, 4);
//			prepStatement.setTimestamp(2, unprocessedReimbursement.getResolved());
//			int tempVar = 1;			
//			if (unprocessedReimbursement.getStatus() == ReimbStatus.APPROVED) {tempVar = 1;}
//			else if(unprocessedReimbursement.getStatus() == ReimbStatus.DENIED) {tempVar = 2;}
//			else {tempVar = 3;}
//			prepStatement.setInt(3, 2);
//			prepStatement.setInt (4, 21);
			
			prepStatement.execute();
			
			return unprocessedReimbursement;
			
		}		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return unprocessedReimbursement;
    }
}
