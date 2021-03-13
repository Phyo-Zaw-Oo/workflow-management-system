package com.workflowmanagement.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="leave_status")
public class LeaveStatusDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="status_id")
	private int statusId;
	@ManyToOne
	@JoinColumn(name="approver", referencedColumnName="user_id")
	private UserDTO approver;
	@ManyToOne
	@JoinColumn(name="leave_form",referencedColumnName="leave_form_id")
	private LeaveFormDTO leaveForm;
	private String status;
	private String comment;
	@Column(name="created_date")
	private Date createdDate;
	
	public LeaveStatusDTO() {
	}

	public UserDTO getApprover() {
		return approver;
	}

	public void setApprover(UserDTO approver) {
		this.approver = approver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public LeaveFormDTO getLeaveForm() {
		return leaveForm;
	}

	public void setLeaveForm(LeaveFormDTO leaveForm) {
		this.leaveForm = leaveForm;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	
	

}
