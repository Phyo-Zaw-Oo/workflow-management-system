package com.workflowmanagement.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class OtFormBean {
	
	@NotEmpty(message="Field must not be null!!!")
	private String otType;
	
	@NotEmpty(message="Field must not be null!!!")
	private Date dateRange; 
	
	@NotEmpty(message="Field must not be null!!!")
	private String reason;
	
	@NotEmpty(message="Field must not be null!!!")
	private String userName;
	
	public String getOtType() {
		return otType;
	}
	public void setOtType(String otType) {
		this.otType = otType;
	}
	public Date getDateRange() {
		return dateRange;
	}
	public void setDateRange(Date dateRange) {
		this.dateRange = dateRange;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
