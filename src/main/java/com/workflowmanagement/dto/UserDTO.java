package com.workflowmanagement.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserDTO {
	
	@Id
	@Column(name="user_id")
	private String userId;
	private String name;
	private String password;
	private String email;
	@Column(name="phone_no")
	private String phoneNo;
	private String gender;
	private String address;
	@ManyToOne
	@JoinColumn(name="role", referencedColumnName="role_id")
	private RoleDTO role;
	@ManyToOne
	@JoinColumn(name="department", referencedColumnName="department_id")
	private DepartmentDTO department;
	
	@OneToMany(mappedBy = "approver")
	private List<LeaveStatusDTO> status;
	
	@OneToMany(mappedBy = "applicant")
	private List<LeaveFormDTO> leaveForm;
	
	@OneToMany(mappedBy = "applicant")
	private List<OvertimeFormDTO> overtimeForm;
	
	public UserDTO() {
	}
	
	
	public UserDTO(String userId, String name, String password, String email, String phoneNo, String gender,
			String address, RoleDTO role, DepartmentDTO department) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.address = address;
		this.role = role;
		this.department = department;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public DepartmentDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", department=" + department + "]";
	}	
	
	

}
