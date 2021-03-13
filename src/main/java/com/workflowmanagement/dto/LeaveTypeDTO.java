package com.workflowmanagement.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "leave_type")
public class LeaveTypeDTO {

	@Id
	@Column(name = "leave_type_id")
	private String leaveTypeId;
	@Column(name = "leave_name")
	private String leaveType;

	@OneToMany(mappedBy = "leaveType")
	private List<LeaveFormDTO> leaveForm;

	public LeaveTypeDTO() {

	}

	public LeaveTypeDTO(String leaveTypeId, String leaveType, List<LeaveFormDTO> leaveForm) {
		super();
		this.leaveTypeId = leaveTypeId;
		this.leaveType = leaveType;
		this.leaveForm = leaveForm;
	}

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

	public List<LeaveFormDTO> getLeaveForm() {
		return leaveForm;
	}

	public void setLeaveForm(List<LeaveFormDTO> leaveForm) {
		this.leaveForm = leaveForm;
	}

	@Override
	public String toString() {
		return "LeaveTypeDTO [leaveTypeId=" + leaveTypeId + ", leaveType=" + leaveType + "]";
	}
	
	

}
