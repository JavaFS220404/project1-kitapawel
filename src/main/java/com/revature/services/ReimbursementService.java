package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.ReimbStatus;
import com.revature.models.ReimbType;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {
	protected ReimbursementDAO rDAO = new ReimbursementDAO();
    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */
    public Reimbursement process(Reimbursement unprocessedReimbursement, ReimbStatus finalStatus, User resolver) {
        return null;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(ReimbStatus status) {
    	
    	System.out.println("Reimbursements by status: " + rDAO.getByStatus(status));
        return rDAO.getByStatus(status);
    }
    
    //ers_reimbursements (ers_reimb_amount, ers_reimb_submitted, ers_reimb_resolved, "
	//		+ "ers_reimb_descr, ers_reimb_receipt, ers_reimb_author, ers_reimb_resolver, ers_reimb_status_id, ers_reimb_type_id)"
    
	public boolean createReimbursement() {
		UserService us = new UserService();
		final Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2018, 10, 7), LocalTime.of(8, 45, 0)));
		Reimbursement reimb = new Reimbursement(25.25, timestamp, "Testowy opis automatyczny", us.getByUserID(1), ReimbStatus.PENDING, ReimbType.LODGING);
		if (rDAO.createReimbursement(reimb)) {
			return true;
		}
		return false;
	}
    
}
