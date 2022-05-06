package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
	public Optional<Reimbursement> getById(int id) {

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
				reimb.setReceipt(result.getBlob("ers_reimb_receipt"));

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

				//TODO add the rest...getting tired, going to sleep;)
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}		

		Optional<Reimbursement> optReimb = Optional.ofNullable(reimb);
		System.out.println(optReimb.toString());
		return optReimb;

	}


    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(ReimbStatus status) {
        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	return null;
    }
}
