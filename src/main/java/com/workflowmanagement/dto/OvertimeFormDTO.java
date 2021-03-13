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
@Table(name="ot_form")
public class OvertimeFormDTO {
	
	@Id
	@Column(name="ot_form_id")
	private String overtimeFormId;
	@ManyToOne
	@JoinColumn(name="ot_type", referencedColumnName="ot_type_id")
	private OvertimeTypeDTO overtimeType;
	@ManyToOne
	@JoinColumn(name="ot_applicant", referencedColumnName="user_id")
	private UserDTO applicant;
	@Column(name="start_time")
	private String startTime;
	@Column(name="end_time")
	private String endTime;
	@Column(name="time_range")
	private String timeRange;
	private String reason;
	@Column(name="created_date")
	private Date createdDate;
	
	
	@OneToMany(mappedBy="overtimeForm")
	private List<OvertimeStatusDTO> OvertimeStatus;
	
	public List<OvertimeStatusDTO> getOvertimeStatus() {
		return OvertimeStatus;
	}

	public void setOvertimeStatus(List<OvertimeStatusDTO> overtimeStatus) {
		OvertimeStatus = overtimeStatus;
	}

	public OvertimeFormDTO() {
	}

	public OvertimeFormDTO(OvertimeTypeDTO overtimeType, UserDTO applicant, String startTime,
			String endTime, String timeRange, String reason, Date createdDate) {
		super();
		this.overtimeType = overtimeType;
		this.applicant = applicant;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeRange = timeRange;
		this.reason = reason;
		this.createdDate = createdDate;
	}

	public String getOvertimeFormId() {
		return overtimeFormId;
	}

	public void setOvertimeFormId(String overtimeFormId) {
		this.overtimeFormId = overtimeFormId;
	}

	public OvertimeTypeDTO getOvertimeType() {
		return overtimeType;
	}

	public void setOvertimeType(OvertimeTypeDTO overtimeType) {
		this.overtimeType = overtimeType;
	}

	public UserDTO getApplicant() {
		return applicant;
	}

	public void setApplicant(UserDTO applicant) {
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}



	
}
