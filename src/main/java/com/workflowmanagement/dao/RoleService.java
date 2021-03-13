package com.workflowmanagement.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.workflowmanagement.dto.RoleDTO;
@Service
public class RoleService {

	@Autowired
	RoleRepository repo;

	public List<RoleDTO> select(RoleDTO dto){
		List<RoleDTO> list=new ArrayList<RoleDTO>();
		list=(List<RoleDTO>) repo.findAll();
		
		if(!dto.getRoleId().equals("")) {
			list=repo.selectoneById(dto.getRoleId());
		}
		else if(!dto.getRoleName().equals(""))
		{
			list=repo.selectoneByName(dto.getRoleName());
		}
		return list;
	}
	public List<RoleDTO> selectAll(){
		List<RoleDTO> list=new ArrayList<RoleDTO>();
		list=(List<RoleDTO>) repo.findAll();
		return list;
	}
	public RoleDTO selectOne(String id) {
		return repo.selectOne(id);
	}
	
	
}
