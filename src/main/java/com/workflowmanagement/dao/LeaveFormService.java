package com.workflowmanagement.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.UserDTO;

@Service
public class LeaveFormService {

	@Autowired
	private LeaveFormRepository repo;

	public void save(LeaveFormDTO dto) {
		repo.save(dto);
	}

	public int insert(LeaveFormDTO dto) {
		int result = 0;

		try {
			repo.save(dto);
			result = 1;
		} finally {
		}
		return result;
	}

	public List<LeaveFormDTO> select(LeaveFormDTO dto) {
		List<LeaveFormDTO> list = new ArrayList<LeaveFormDTO>();
		list = (List<LeaveFormDTO>) repo.findAll();
		
		if (!dto.getLeaveFormId().equals("")) {
			list = repo.selectOneById(dto.getLeaveFormId());

		}
		
		return list;
	}
	
	public List<LeaveFormDTO> selectAll(){
		List<LeaveFormDTO> list = new ArrayList<LeaveFormDTO>();
		list = (List<LeaveFormDTO>) repo.findAll();
		return list;
	}
	
	public LeaveFormDTO selectLeaveFormById(String id) {
		LeaveFormDTO leaveFormDTO = new LeaveFormDTO();
		leaveFormDTO = repo.selectLeaveFormById(id);
		return leaveFormDTO;
	}

	public String generateLeaveFormId() {
		int count = 0;
		String leaveFormId = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String sDate = sdf.format(date);
		count = repo.selectRowCount();

		if (count < 10) {
			leaveFormId = "LV" + sDate + "-00"+ count;
		} else if (count >= 10 && count < 100) {
			leaveFormId = "LV" + sDate + "-0"+ count;
		} else if (count >= 100) {
			leaveFormId = "LV" + sDate +"-"+ count;
		}

		return leaveFormId;
	}
}
