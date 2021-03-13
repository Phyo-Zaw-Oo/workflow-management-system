package com.workflowmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workflowmanagement.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	public int save(UserDTO user) {
		int rs = 0;
		repo.save(user);
		rs = 1;
		return rs;
	}

	public int update(UserDTO user, String id) {
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

	public List<UserDTO> select(UserDTO dto) {
		List<UserDTO> list = new ArrayList<UserDTO>();
		list = (List<UserDTO>) repo.findAll();

		if (!dto.getUserId().equals("")) {
			list = repo.selectOneById(dto.getUserId());
		} else if (!dto.getName().equals("")) {
			list = repo.selectOneByName(dto.getName());
		}

		return list;
	}

	public List<UserDTO> selectAll() {
		List<UserDTO> list = new ArrayList<>();
		list = (List<UserDTO>) repo.findAll();
		return list;

	}

	public UserDTO selectOne(String id) {
		return repo.selectOne(id);
	}

	public UserDTO selectOneByApproverName(String name) {
		UserDTO userDTO = new UserDTO();
		userDTO = repo.selectOneByApproverName(name);
		return userDTO;
	}

	public List<UserDTO> selectApproverNameList() {
		List<UserDTO> nameList = new ArrayList<UserDTO>();
		nameList = repo.selectApprovalNameList();
		return nameList;
	}

	public String generateUserId() {
		int count = 0;
		String userId = null;
		count = repo.selectRowCount();
		if (count < 10) {
			userId = "U00"+ count;
		} else if (count >= 10 && count < 100) {
			userId = "U0"+ count;
		} else if (count == 100) {
			userId = "U"+ count;
		}
		return userId;
	}

	public int updatePassword(String password, String id) {
		int rs = 0;
		repo.updatePassword(password, id);
		rs = 1;
		return rs;
	}

	public List<UserDTO> selectAll(UserDTO dto) {
		System.out.println(dto);
		List<UserDTO> resultList = new ArrayList<UserDTO>();
		resultList = (List<UserDTO>) repo.findAll();

		if (dto.getName() != null) {
			resultList = repo.selectOneByName(dto.getName());
		} else if (dto.getUserId() != null) {
			resultList = repo.selectOneById(dto.getUserId());
		} else if (dto.getRole() != null) {
			List<UserDTO> roleList = new ArrayList<UserDTO>();
			for (UserDTO user : resultList) {
				if (user.getRole() == dto.getRole()) {
					roleList.add(user);
				}
			}
			resultList = roleList;
		} else if (dto.getDepartment() != null) {
			List<UserDTO> deptList = new ArrayList<UserDTO>();

			for (UserDTO user : resultList) {
				if (user.getDepartment() == dto.getDepartment()) {
					deptList.add(user);
				}
			}
			resultList = deptList;
		}

		return resultList;
	}

}
