package com.workflowmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workflowmanagement.dto.OvertimeTypeDTO;


@Repository
public interface OvertimeTypeRepository extends CrudRepository<OvertimeTypeDTO,String>{
	
	@Query(value="select o from OvertimeTypeDTO o where o.overtimeTypeId=?1")
	OvertimeTypeDTO selectOne(String id);
	
	@Query(value= "select ot from OvertimeTypeDTO ot where ot.overtimeTypeId=?1")
	List<OvertimeTypeDTO> selectOneById(String id);
	
	@Query(value= "select ot from OvertimeTypeDTO ot where ot.overtimeType=?1")
	List<OvertimeTypeDTO> selectOneByName(String name);
	
	@Query(value= "select count(*)+1 from OvertimeTypeDTO l")
	Integer selectRowCount();

}
