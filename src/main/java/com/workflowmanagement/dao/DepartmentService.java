package com.workflowmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workflowmanagement.dto.DepartmentDTO;
import com.workflowmanagement.dto.LeaveTypeDTO;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository repo;
	
	public int save(DepartmentDTO dept) {
		int rs=0;
		repo.save(dept);
		rs=1;
		return rs;
	}
	
	public int update(DepartmentDTO dept, String Id) {
		int rs=0;
		repo.save(dept);
		rs=1;
		return rs;
	}
	
	public int delete(String Id) {
		int rs=0;
		repo.deleteById(Id);
		rs=1;
		return rs;
	}
	
	
	
	public List<DepartmentDTO> select(DepartmentDTO dto){
		List<DepartmentDTO> list=new ArrayList<DepartmentDTO>();
		list=(List<DepartmentDTO>) repo.findAll();
		
		if(!dto.getDepartmentId().equals("")) {
			list=repo.selectOneById(dto.getDepartmentId());
		}else if(!dto.getDepartmentName().equals("")) {
			list=repo.selectOneByName(dto.getDepartmentName());
		}
		
		return list;
	}
	
	public List<DepartmentDTO> selectAll(){
		List<DepartmentDTO> list=new ArrayList<>();
		list=(List<DepartmentDTO>)repo.findAll();
		return list;
	}
	
	public DepartmentDTO selectOne(String id){
		DepartmentDTO deptDto=repo.selectOne(id);
		return deptDto;
}
	
	public String generateDepId() {
		int count = 0;
		String depId = null;
		count = repo.selectRowCount();
		if(count<10) {
			depId="DP0"+count;
		}else if(count>=10) {
			depId="DP"+count;
		}
		return depId;
	}	
}