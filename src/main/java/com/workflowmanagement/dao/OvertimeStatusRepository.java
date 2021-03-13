package com.workflowmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workflowmanagement.dto.LeaveStatusDTO;
import com.workflowmanagement.dto.OvertimeFormDTO;
import com.workflowmanagement.dto.OvertimeStatusDTO;
import com.workflowmanagement.dto.UserDTO;

@Repository
public interface OvertimeStatusRepository extends CrudRepository<OvertimeStatusDTO, String>{
	
	@Query("select s from OvertimeStatusDTO s where s.overtimeForm not in (select  s.overtimeForm from OvertimeStatusDTO s where s.status='Approved Complete' or s.status='Rejected')")
	List<OvertimeStatusDTO> selectUnfinishedOTForm();
	
	@Query("select s from OvertimeStatusDTO s where s.overtimeForm  in (select  s.overtimeForm from OvertimeStatusDTO s where s.status='Approved Complete' or s.status='Rejected')")
	List<OvertimeStatusDTO> selectfinishedOTForm();
	
	@Query("select s from OvertimeStatusDTO s where s.overtimeForm=?1")
	List<OvertimeStatusDTO> findByOtForm(OvertimeFormDTO dto);
	
	@Query("select s from OvertimeStatusDTO s where  s.status='Approved Complete' or s.status='Rejected'")
	List<OvertimeStatusDTO> selectAllfinishedOTForm();
	
	@Query("select s from OvertimeStatusDTO s where s.overtimeForm.overtimeFormId=?1 and s.status='Pending'")
	OvertimeStatusDTO selectPendingOTForm(String overtimeFormId);
	
	@Query("select s from OvertimeStatusDTO s where s.approver=?1 and s.status<>'Pending'")
	List<OvertimeStatusDTO> approvalHistory(UserDTO user);

}