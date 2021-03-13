package com.workflowmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.workflowmanagement.dto.OvertimeFormDTO;
import com.workflowmanagement.dto.OvertimeStatusDTO;
import com.workflowmanagement.dto.OvertimeTypeDTO;

@Repository
public interface OvertimeFormRepository extends CrudRepository<OvertimeFormDTO, String>{
	
	@Query(value= "select o from OvertimeFormDTO o where o.overtimeFormId=?1")
	List<OvertimeFormDTO> selectOneById(String id);
	
	@Query(value="select o.overtimeFormId from OvertimeFormDTO o where o.overtimeType=?1")
	List<String> selectByOtType(OvertimeTypeDTO dto);
	
	@Query(value= "select o from OvertimeFormDTO o where o.overtimeFormId=?1")
	OvertimeFormDTO selectOTFormById(String id);
	
	@Query(value="select o.overtimeFormId from OvertimeFormDTO o where o.startTime=?1 and o.endTime=?2")
	List<String> selectByTimeRange(String startTime,String endTime);
	
	@Query(value="select o.overtimeFormId from OvertimeFormDTO o where o.overtimeType=?1")
	List<OvertimeStatusDTO> selectByOtType1(OvertimeTypeDTO dto);
	
	@Query(value= "select count(*)+1 from OvertimeFormDTO o")
	Integer selectRowCount();

}

