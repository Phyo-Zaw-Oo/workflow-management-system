package com.workflowmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workflowmanagement.dto.OvertimeTypeDTO;

@Service
public class OvertimeTypeService {

	@Autowired
	OvertimeTypeRepository repo;

	public int save(OvertimeTypeDTO user) {
		int rs = 0;
		repo.save(user);
		rs = 1;
		return rs;
	}

	public int update(OvertimeTypeDTO user, String id) {
		int rs = 0;
		repo.save(user);
		rs = 1;
		return rs;
	}

	public int delete(String id) {
		int rs = 0;
		repo.deleteById(id);
		rs = 1;
		return rs;
	}

	public List<OvertimeTypeDTO> selectAll() {
		List<OvertimeTypeDTO> list = new ArrayList<>();
		list = (List<OvertimeTypeDTO>) repo.findAll();
		return list;
	}

	public OvertimeTypeDTO selectOne(String id) {
		return repo.selectOne(id);
	}

	public List<OvertimeTypeDTO> select(OvertimeTypeDTO dto) {
		List<OvertimeTypeDTO> list = new ArrayList<OvertimeTypeDTO>();
		list = (List<OvertimeTypeDTO>) repo.findAll();

		if (!dto.getOvertimeTypeId().equals("")) {
			list = repo.selectOneById(dto.getOvertimeTypeId());
		} else if (!dto.getOvertimeType().equals("")) {
			list = repo.selectOneByName(dto.getOvertimeType());
		}

		return list;
	}

	public String generateOvertimeTypeId() {
		int count = 0;
		String overtimeTypeId = null;
		count = repo.selectRowCount();
		if (count < 10) {
			overtimeTypeId = "OT0"+ count;
		} else if (count >= 10) {
			overtimeTypeId = "OT" + count;
		}
		return overtimeTypeId;
	}

}
