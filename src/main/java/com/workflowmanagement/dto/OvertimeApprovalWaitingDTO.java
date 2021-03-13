package com.workflowmanagement.dto;

public class OvertimeApprovalWaitingDTO {

	private String overtimeFormId;
	private String applicant;
	private String startTime;
	private String endTime;
	private String duration;
	private String reason;
	public String getOvertimeFormId() {
		return overtimeFormId;
	}
	public void setOvertimeFormId(String overtimeFormId) {
		this.overtimeFormId = overtimeFormId;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
