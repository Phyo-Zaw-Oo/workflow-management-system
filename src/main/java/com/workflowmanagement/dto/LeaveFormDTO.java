package com.workflowmanagement.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="leave_form")
public class LeaveFormDTO {
	
	@Id
	@Column(name="leave_form_id")
	private String leaveFormId;
	@ManyToOne
	@JoinColumn(name="leave_type", referencedColumnName="leave_type_id")
	private LeaveTypeDTO leaveType;
	@ManyToOne
	@JoinColumn(name="leave_applicant", referencedColumnName="user_id")
	private UserDTO applicant;
	@Column(name="start_date")
	private String startDate;
	@Column(name="end_date")
	private String endDate;
	@Column(name="date_range")
	private double dateRange;
	@Column(name="full_or_half")
	private double fullOrHalf;
	private String reason;
	@JoinColumn(name="assign_to")
	@ManyToOne
	private UserDTO assignTo;
	@Column(name="createdDate")
	private Date createdDate;
	
	@OneToMany(mappedBy="leaveForm")
	private List<LeaveStatusDTO> leaveStatus;
	
	public LeaveFormDTO() {
	}

	public LeaveFormDTO(LeaveTypeDTO leaveType, UserDTO applicant, String startDate, String endDate, double dateRange,
			double fullOrHalf, String reason, UserDTO assignTo, Date createdDate) {
		super();
		this.leaveType = leaveType;
		this.applicant = applicant;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dateRange = dateRange;
		this.fullOrHalf = fullOrHalf;
		this.reason = reason;
		this.assignTo = assignTo;
		this.createdDate = createdDate;
	}

	public String getLeaveFormId() {
		return leaveFormId;
	}

	public void setLeaveFormId(String leaveFormId) {
		this.leaveFormId = leaveFormId;
	}

	public LeaveTypeDTO getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveTypeDTO leaveType) {
		this.leaveType = leaveType;
	}

	public UserDTO getApplicant() {
		return applicant;
	}

	public void setApplicant(UserDTO applicant) {
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

	public double getFullOrHalf() {
		return fullOrHalf;
	}

	public void setFullOrHalf(double fullOrHalf) {
		this.fullOrHalf = fullOrHalf;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public UserDTO getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(UserDTO assignTo) {
		this.assignTo = assignTo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<LeaveStatusDTO> getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(List<LeaveStatusDTO> leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public double getDateRange() {
		return dateRange;
	}

	public void setDateRange(double dateRange) {
		this.dateRange = dateRange;
	}

	@Override
	public String toString() {
		return "LeaveFormDTO [leaveType=" + leaveType + ", applicant=" + applicant + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

	
}
