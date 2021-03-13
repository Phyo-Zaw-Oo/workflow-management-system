package com.workflowmanagement.model;

import javax.validation.constraints.NotEmpty;

public class OvertimeTypeBean {
	
	private String overtimeTypeId;
	
	@NotEmpty(message="Leave Name must not be null!!!")
	private String overtimeType;

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
	

}
