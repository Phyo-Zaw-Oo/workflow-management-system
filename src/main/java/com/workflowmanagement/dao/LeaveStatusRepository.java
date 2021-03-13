package com.workflowmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.LeaveStatusDTO;
import com.workflowmanagement.dto.UserDTO;

@Repository
public interface LeaveStatusRepository extends CrudRepository<LeaveStatusDTO,String> {

	@Query("select s from LeaveStatusDTO s where s.leaveForm not in (select  s.leaveForm from LeaveStatusDTO s where s.status='Approved Complete' or s.status='Rejected')")
	List<LeaveStatusDTO> selectUnfinishedLeaveForm();
	
	@Query("select s from LeaveStatusDTO s where s.leaveForm in (select  s.leaveForm from LeaveStatusDTO s where s.status='Approved Complete' or s.status='Rejected')")
	List<LeaveStatusDTO> selectfinishedLeaveForm();
	
	@Query("select s from LeaveStatusDTO s where s.leaveForm=?1")
	List<LeaveStatusDTO> findByLeaveForm(LeaveFormDTO dto);
	
	@Query("select s from LeaveStatusDTO s where s.status='Approved Complete' or s.status='Rejected'")
	List<LeaveStatusDTO> selectAllfinishedLeaveForm();
	
	@Query("select s from LeaveStatusDTO s where s.leaveForm.leaveFormId=?1 and s.status='Pending'")
	LeaveStatusDTO selectPendingLeaveForm(String leaveFormId);
	
	@Query("select s from LeaveStatusDTO s where s.approver=?1 and s.status<>'Pending'")
	List<LeaveStatusDTO> approvalHistory(UserDTO user);
	
}