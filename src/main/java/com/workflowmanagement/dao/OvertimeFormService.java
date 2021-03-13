package com.workflowmanagement.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workflowmanagement.dto.OvertimeFormDTO;

@Service
public class OvertimeFormService {

	@Autowired
	OvertimeFormRepository repo;

	public int insert(OvertimeFormDTO dto) {
		int result = 0;

		try {
			repo.save(dto);
			result = 1;
		} finally {
		}
		return result;
	}

	public List<OvertimeFormDTO> select(OvertimeFormDTO dto) {
		List<OvertimeFormDTO> list = new ArrayList<OvertimeFormDTO>();
		list = (List<OvertimeFormDTO>) repo.findAll();

		if (!dto.getOvertimeFormId().equals("")) {
			list = repo.selectOneById(dto.getOvertimeFormId());

		}

		return list;
	}

	public OvertimeFormDTO selectOTFormById(String id) {
		OvertimeFormDTO overtimeFormDTO = new OvertimeFormDTO();
		overtimeFormDTO = repo.selectOTFormById(id);
		return overtimeFormDTO;

	}

	public String generateOvertimeFormId() {
		int count = 0;
		String overtimeFormId = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String sDate = sdf.format(date);
		count = repo.selectRowCount();
		if (count < 10) {
			overtimeFormId = "OT" + sDate + "-00" + count;
		} else if (count >= 10 && count < 100) {
			overtimeFormId = "OT" + sDate + "-0"+ count;
		} else if (count >= 100) {
			overtimeFormId = "OT" + sDate +"-"+ count;
		}
		return overtimeFormId;
	}

}
