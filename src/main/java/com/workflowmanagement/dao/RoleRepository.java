package com.workflowmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.workflowmanagement.dto.RoleDTO;

@Repository
public interface RoleRepository extends CrudRepository<RoleDTO, String>{
	
	@Query(value="select r from RoleDTO r where r.roleId=?1")
	List<RoleDTO> selectoneById(String Id);
	
	@Query(value="select r from RoleDTO r where r.roleName=?1")
	List<RoleDTO> selectoneByName(String Name);
	
	@Query(value="select r from RoleDTO r where r.roleId=?1")
	RoleDTO selectOne(String id);
	
}