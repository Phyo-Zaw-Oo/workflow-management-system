package com.workflowmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workflowmanagement.dto.LeaveTypeDTO;

@Service
public class LeaveTypeService {
	
	@Autowired
	LeaveTypeRepository repo;
	
	public int save(LeaveTypeDTO user) {
		int rs=0;
		repo.save(user);
		rs=1;
		return rs;
	}
	
	public int update(LeaveTypeDTO user, String id) {
		int rs=0;
		repo.save(user);
		rs=1;
		return rs;
	}
	
	public int delete(String id) {
		int rs=0;
		repo.deleteById(id);
		rs=1;
		return rs;
	}
	
	
	public List<LeaveTypeDTO> selectAll(){
		List<LeaveTypeDTO> list=new ArrayList<>();
		list=(List<LeaveTypeDTO>)repo.findAll();
		return list;
	}
	
	public LeaveTypeDTO selectOne(String id) {
		return repo.selectOne(id);	
	}
	
	public List<LeaveTypeDTO> select(LeaveTypeDTO dto){
		List<LeaveTypeDTO> list=new ArrayList<LeaveTypeDTO>();
		list=(List<LeaveTypeDTO>) repo.findAll();
		
		if(!dto.getLeaveTypeId().equals("")) {
			list=repo.selectOneById(dto.getLeaveTypeId());
		}else if(!dto.getLeaveType().equals("")) {
			list=repo.selectOneByName(dto.getLeaveType());
		}
		
		return list;
	}
	
	public String generateLeaveTypeId() {
		int count = 0;
		String leaveTypeId = null;
		count = repo.selectRowCount();
		if(count<10) {
			leaveTypeId="LV0"+count;
		}else if(count>=10) {
			leaveTypeId="LV"+count;
		}
		return leaveTypeId;
	}	

}
