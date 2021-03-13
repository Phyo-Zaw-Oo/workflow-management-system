package com.workflowmanagement.dto;

public class LeaveApprovalWaitingDTO {

	private String leaveFormId;
	private String applicant;
	private String startDate;
	private String endDate;
	private String duration;
	private String assignTo;
	private String reason;
	public String getLeaveFormId() {
		return leaveFormId;
	}
	public void setLeaveFormId(String leaveFormId) {
		this.leaveFormId = leaveFormId;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
