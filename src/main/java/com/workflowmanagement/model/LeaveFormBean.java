package com.workflowmanagement.model;

public class LeaveFormBean {

	private String leaveForm;

	private String leaveType;
	
	private String applicant;
	
	private String department;

	private double fullOrHalf;

	private String dateRange;

	private String assignTo;

	private String reason;

	private String submitTo;

	@Override
	public String toString() {
		return "LeaveFormBean [leaveForm=" + leaveForm + ", leaveType=" + leaveType + ", fullOrHalf=" + fullOrHalf
				+ ", dateRange=" + dateRange + ", assignTo=" + assignTo + ", reason=" + reason + ", submitTo="
				+ submitTo + "]";
	}

	
	

	public String getLeaveForm() {
		return leaveForm;
	}




	public void setLeaveForm(String leaveForm) {
		this.leaveForm = leaveForm;
	}




	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}


	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getSubmitTo() {
		return submitTo;
	}

	public void setSubmitTo(String submitTo) {
		this.submitTo = submitTo;
	}

	public double getFullOrHalf() {
		return fullOrHalf;
	}

	public void setFullOrHalf(double fullOrHalf) {
		this.fullOrHalf = fullOrHalf;
	}

	public String getDateRange() {
		return dateRange;
	}

	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
