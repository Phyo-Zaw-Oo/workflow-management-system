package com.workflowmanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.workflowmanagement.dto.UserDTO;

@Repository
public interface UserRepository extends CrudRepository<UserDTO, String>{
	
	@Query(value= "select u from UserDTO u where u.userId=?1")
	List<UserDTO> selectOneById(String id);
	
	@Query(value= "select u from UserDTO u where u.name=?1")
	List<UserDTO> selectOneByName(String name);

	@Query(value= "select u from UserDTO u where u.id=?1")
	UserDTO selectOne(String id);
	
	@Query(value= "select u from UserDTO u where u.name=?1")
	UserDTO selectOneByApproverName(String name);
	
	@Query(value= "select u from UserDTO u where u.role.roleName in ('Project Manager','Head of Department','HR')")
	List<UserDTO> selectApprovalNameList();
	
	@Query(value= "select u from UserDTO u where u.id=?1")
	void updatePassword(String password, String id);

	@Query(value= "select count(*)+1 from UserDTO u")
	Integer selectRowCount();

}
