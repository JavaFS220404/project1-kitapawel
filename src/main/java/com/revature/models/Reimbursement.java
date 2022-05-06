package com.revature.models;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {

	private Blob receipt;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;	
	private ReimbType reimbType; 
	
    public Reimbursement() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int id, ReimbStatus reimbStatus, User author, User resolver, double amount) {
        super(id, reimbStatus, author, resolver, amount);
    }
    
    public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description, Blob receipt, User author, User resolver, ReimbStatus reimbStatus, ReimbType reimbType) {
        super(id, reimbStatus, author, resolver, amount);
        this.receipt = receipt;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.reimbType = reimbType;
    }

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReimbType getReimbType() {
		return reimbType;
	}

	public void setReimbType(ReimbType reimbType) {
		this.reimbType = reimbType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(description, reimbType, resolved, submitted);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Objects.equals(description, other.description) && reimbType == other.reimbType
				&& Objects.equals(resolved, other.resolved) && Objects.equals(submitted, other.submitted);
	}

	@Override
	public String toString() {
		return "Reimbursement [ID=" + super.getId() + ", receipt=" + receipt + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", reimbType=" + reimbType + ", author=" + super.getAuthor() + ", resolver=" + super.getResolver() + ", status=" + super.getStatus() + ", type=" + reimbType + "]";
	}    
    
}
