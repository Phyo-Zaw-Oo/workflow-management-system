package com.workflowmanagement.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ot_type")
public class OvertimeTypeDTO {
	
	@Id
	@Column(name="ot_type_id")
	private String overtimeTypeId;
	@Column(name="ot_name")
	private String overtimeType;
	
	@OneToMany(mappedBy="overtimeType")
	private List<OvertimeFormDTO> overtimeForm;
	
	public OvertimeTypeDTO() {	
	}

	public OvertimeTypeDTO(String overtimeTypeId, String overtimeType, List<OvertimeFormDTO> overtimeForm) {
		super();
		this.overtimeTypeId = overtimeTypeId;
		this.overtimeType = overtimeType;
		this.overtimeForm = overtimeForm;
	}

	public String getOvertimeTypeId() {
		return overtimeTypeId;
	}

	public void setOvertimeTypeId(String overtimeTypeId) {
		this.overtimeTypeId = overtimeTypeId;
	}

	public String getOvertimeType() {
		return overtimeType;
	}

	public void setOvertimeType(String overtimeType) {
		this.overtimeType = overtimeType;
	}

	public List<OvertimeFormDTO> getOvertimeForm() {
		return overtimeForm;
	}

	public void setOvertimeForm(List<OvertimeFormDTO> overtimeForm) {
		this.overtimeForm = overtimeForm;
	}
	
	

	
}
