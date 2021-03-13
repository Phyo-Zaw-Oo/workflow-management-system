package com.workflowmanagement.dto;

import java.util.Date;

public class LeaveApprovalDTO {

	private String leaveFormId;
	private String applicant;
	private String department;
	private String leaveType;
	private String dateRange;
	private String startDate;
	private String endDate;
	private String fullOrHalf;
	private String reason;
	private String status;
	private Date createdDate;
	
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getDateRange() {
		return dateRange;
	}
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
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
	public String getFullOrHalf() {
		return fullOrHalf;
	}
	public void setFullOrHalf(String fullOrHalf) {
		this.fullOrHalf = fullOrHalf;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
