package com.workflowmanagement.model;

import java.util.Date;

import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.OvertimeFormDTO;

public class StatusBean {
	
	private int statusId;
	private LeaveFormDTO leaveForm;
	private OvertimeFormDTO overtimeForm;
	private String status;
	private String commment;
	private Date createdDate;
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public LeaveFormDTO getLeaveForm() {
		return leaveForm;
	}
	public void setLeaveForm(LeaveFormDTO leaveForm) {
		this.leaveForm = leaveForm;
	}
	public String getStatus() {
		return status;
	}
	
	public OvertimeFormDTO getOvertimeForm() {
		return overtimeForm;
	}
	public void setOvertimeForm(OvertimeFormDTO overtimeForm) {
		this.overtimeForm = overtimeForm;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommment() {
		return commment;
	}
	public void setCommment(String commment) {
		this.commment = commment;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "StatusBean [statusId=" + statusId + ", leaveForm=" + leaveForm + ", status=" + status + ", commment="
				+ commment + ", createdDate=" + createdDate + "]";
	}
	
	

}
