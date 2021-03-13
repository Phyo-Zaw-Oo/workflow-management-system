package com.workflowmanagement.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class DepartmentDTO {
	
	@Id
	@Column(name="department_id")
	private String departmentId;
	@Column(name="department_name")
	private String departmentName;
	
	
	@OneToMany(mappedBy="department")
	private List<UserDTO> user;
	
	public DepartmentDTO() {
	}



	public DepartmentDTO(String departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}



	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<UserDTO> getUser() {
		return user;
	}

	public void setUser(List<UserDTO> user) {
		this.user = user;
	}
	
	
	
	

}
