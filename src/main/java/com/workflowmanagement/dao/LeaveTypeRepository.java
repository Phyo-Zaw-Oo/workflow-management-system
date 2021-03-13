package com.workflowmanagement.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workflowmanagement.dto.LeaveTypeDTO;

@Repository
public interface LeaveTypeRepository extends CrudRepository<LeaveTypeDTO, String>{
	
	@Query(value="select l from LeaveTypeDTO l where l.leaveTypeId=?1")
	LeaveTypeDTO selectOne(String id);
	
	@Query(value= "select lv from LeaveTypeDTO lv where lv.leaveTypeId=?1")
	List<LeaveTypeDTO> selectOneById(String id);
	
	@Query(value= "select lv from LeaveTypeDTO lv where lv.leaveType=?1")
	List<LeaveTypeDTO> selectOneByName(String name);
	
	@Query(value= "select count(*)+1 from LeaveTypeDTO l")
	Integer selectRowCount();

}
