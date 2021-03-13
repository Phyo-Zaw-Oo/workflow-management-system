package com.workflowmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.LeaveTypeDTO;
import com.workflowmanagement.dto.UserDTO;

@Repository
public interface LeaveFormRepository extends CrudRepository<LeaveFormDTO, String>{
	
	@Query(value= "select l from LeaveFormDTO l where l.leaveFormId=?1")
	List<LeaveFormDTO> selectOneById(String id);
	
	@Query(value= "select l from LeaveFormDTO l where l.leaveFormId=?1")
	LeaveFormDTO selectLeaveFormById(String id);
	
	@Query(value= "select l.leaveFormId from LeaveFormDTO l where l.leaveType=?1")
	List<String> selectByLeaveType(LeaveTypeDTO dto);
	
	@Query(value= "select l.leaveFormId from LeaveFormDTO l where l.startDate=?1 and l.endDate=?2")
	List<String> selectByDateRange(String startDate, String endDate);
	

	@Query(value= "select count(*)+1 from LeaveFormDTO l")
	Integer selectRowCount();
	
	@Query(value= "select l.leaveFormId from LeaveFormDTO l where l.applicant=?1")
	List<String> selectLeaveFormId(UserDTO user);
	
	

}
