package com.workflowmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workflowmanagement.dto.DepartmentDTO;
import com.workflowmanagement.dto.LeaveTypeDTO;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentDTO, String>{
	
	@Query(value= "select d from DepartmentDTO d where d.departmentId=?1")
	List<DepartmentDTO> selectOneById(String id);
	
	@Query(value= "select d from DepartmentDTO d where d.departmentName=?1")
	List<DepartmentDTO> selectOneByName(String name);
	
	@Query(value= "select d from DepartmentDTO d where d.departmentName=?1")
	DepartmentDTO selectOneByDepartmentName(String name);
	
	@Query(value= "select d from DepartmentDTO d where d.departmentId=?1")
	DepartmentDTO selectOne(String id);
	
	@Query(value= "select count(*)+1 from DepartmentDTO d")
	Integer selectRowCount();

}
