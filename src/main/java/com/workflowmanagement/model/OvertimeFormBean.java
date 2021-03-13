package com.workflowmanagement.model;



public class OvertimeFormBean {
	
	private String overtimeForm;

	private String overtimeType;
	
	private String timeRange; 
	
	private String reason;
	
	private String submitTo;
	
	private String applicant;
	
	private String department;
	

	

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getOvertimeType() {
		return overtimeType;
	}

	public void setOvertimeType(String overtimeType) {
		this.overtimeType = overtimeType;
	}

	public String getOvertimeForm() {
		return overtimeForm;
	}

	public void setOvertimeForm(String overtimeForm) {
		this.overtimeForm = overtimeForm;
	}

	

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}

	public String getSubmitTo() {
		return submitTo;
	}
	public void setSubmitTo(String submitTo) {
		this.submitTo = submitTo;
	}
	

}
