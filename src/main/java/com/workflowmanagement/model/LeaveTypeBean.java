package com.workflowmanagement.model;

import javax.validation.constraints.NotEmpty;

public class LeaveTypeBean {
	
	String leaveTypeId;
	
	@NotEmpty(message="Leave Name must not be null!!!")
	String leaveType;
	
	public String getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(String leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	

}
