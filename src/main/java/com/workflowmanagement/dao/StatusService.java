package com.workflowmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.LeaveStatusDTO;
import com.workflowmanagement.dto.OvertimeFormDTO;
import com.workflowmanagement.dto.OvertimeStatusDTO;
import com.workflowmanagement.dto.UserDTO;

@Service
public class StatusService {

	@Autowired
	private LeaveStatusRepository leaveRepo;

	@Autowired
	private OvertimeStatusRepository otRepo;

	@Autowired
	private HttpSession session;

	public List<LeaveStatusDTO> selectUnfinishedleaveForm() {
		List<LeaveStatusDTO> list = leaveRepo.selectUnfinishedLeaveForm();
		List<LeaveStatusDTO> resultList = new ArrayList<>();
		UserDTO applicant = (UserDTO) session.getAttribute("sesUser");
		for (LeaveStatusDTO dto : list) {
			if (dto.getLeaveForm().getApplicant().getUserId().equals(applicant.getUserId())) {
				resultList.add(dto);
			}
		}
		return resultList;
	}

	public List<OvertimeStatusDTO> selectUnfinishedOTForm() {
		List<OvertimeStatusDTO> list = otRepo.selectUnfinishedOTForm();
		List<OvertimeStatusDTO> resultList = new ArrayList<>();
		UserDTO applicant = (UserDTO) session.getAttribute("sesUser");
		for (OvertimeStatusDTO dto : list) {
			if (dto.getOvertimeForm().getApplicant().getUserId().equals(applicant.getUserId())) {
				resultList.add(dto);
			}
		}
		return resultList;
	}
	//Application
	public List<LeaveStatusDTO> applicationLeaveHistory(LeaveFormDTO dto) {

		List<LeaveStatusDTO> list = leaveRepo.selectAllfinishedLeaveForm();
		UserDTO applicant = (UserDTO) session.getAttribute("sesUser");
		System.out.println(applicant);
		List<LeaveStatusDTO> resultList = new ArrayList<>();
		for (LeaveStatusDTO leaveDto : list) {
			
			if (leaveDto.getLeaveForm().getApplicant().getUserId().equals(applicant.getUserId())) {
			resultList.add(leaveDto);
		}
		}
		if ((dto.getStartDate() != null) && (dto.getEndDate() != null)) {
			List<LeaveStatusDTO> dateRangeList = new ArrayList<>();
			for (LeaveStatusDTO dateRangeDto : resultList) {
				if (dateRangeDto.getLeaveForm().getStartDate().equals(dto.getStartDate())
						&& dateRangeDto.getLeaveForm().getEndDate().equals(dto.getEndDate())) {
					dateRangeList.add(dateRangeDto);
				}
			}
			resultList = dateRangeList;

		} else if (dto.getLeaveType() != null) {

			List<LeaveStatusDTO> leaveTypeList = new ArrayList<>();
			for (LeaveStatusDTO leaveTypeDto : resultList) {
				if (leaveTypeDto.getLeaveForm().getLeaveType() == dto.getLeaveType()) {
					leaveTypeList.add(leaveTypeDto);
				}
			}
			resultList = leaveTypeList;
		}
		return resultList;
	}

	// Check Application Overtime History By All/OTType/TimeRange
	public List<OvertimeStatusDTO> applicationOTHistory(OvertimeFormDTO dto) {
		List<OvertimeStatusDTO> list = otRepo.selectAllfinishedOTForm();
		List<OvertimeStatusDTO> resultList = new ArrayList<>();
		UserDTO applicant = (UserDTO) session.getAttribute("sesUser");
		System.out.println(applicant);
		for (OvertimeStatusDTO otDto : list) {
			
			if (otDto.getOvertimeForm().getApplicant().getUserId().equals(applicant.getUserId())) {
			resultList.add(otDto);
		}
		}
		if ((dto.getStartTime() != null) && (dto.getEndTime() != null)) {
			List<OvertimeStatusDTO> timeRangeList = new ArrayList<>();
			for (OvertimeStatusDTO timeRangeDto : resultList) {
				if (timeRangeDto.getOvertimeForm().getStartTime().equals(dto.getStartTime())
						&& timeRangeDto.getOvertimeForm().getEndTime().equals(dto.getEndTime())) {
					timeRangeList.add(timeRangeDto);
				}
			}
			resultList = timeRangeList;

		} else if (dto.getOvertimeType() != null) {

			List<OvertimeStatusDTO> otTypeList = new ArrayList<>();
			for (OvertimeStatusDTO otTypeDto : resultList) {
				if (otTypeDto.getOvertimeForm().getOvertimeType() == dto.getOvertimeType()) {
					otTypeList.add(otTypeDto);
				}
			}
			resultList = otTypeList;
		}
		return resultList;
	}

	public List<LeaveStatusDTO> findByLeaveForm(LeaveFormDTO dto) {
		List<LeaveStatusDTO> list = leaveRepo.findByLeaveForm(dto);
		return list;
	}

	public List<OvertimeStatusDTO> findByOtForm(OvertimeFormDTO dto) {
		List<OvertimeStatusDTO> list = otRepo.findByOtForm(dto);
		return list;
	}

	public int insertLeave(LeaveStatusDTO dto) {
		int result = 0;

		try {
			leaveRepo.save(dto);
			result = 1;
		} finally {
		}
		return result;
	}

	public int insertOvertime(OvertimeStatusDTO dto) {
		int result = 0;

		try {
			otRepo.save(dto);
			result = 1;
		} finally {
		}
		return result;
	}

	// Select All Type Function Staff History Leave
	public List<LeaveStatusDTO> selectAllfinishedLeaveForm(LeaveFormDTO dto) {
		System.out.println(dto);
		List<LeaveStatusDTO> resultList = new ArrayList<>();
		resultList = leaveRepo.selectAllfinishedLeaveForm();

		if (dto.getLeaveType() != null) {
			List<LeaveStatusDTO> leaveList = new ArrayList<>();

			for (LeaveStatusDTO leaveDTO : resultList) {
				if (leaveDTO.getLeaveForm().getLeaveType() == dto.getLeaveType()) {
					leaveList.add(leaveDTO);
				}
			}
			resultList = leaveList;

		} else if ((dto.getStartDate() != null) && (dto.getEndDate() != null)) {
			List<LeaveStatusDTO> daterangeList = new ArrayList<>();

			for (LeaveStatusDTO leaveDTO : resultList) {
				if ((leaveDTO.getLeaveForm().getStartDate().equals(dto.getStartDate()))
						&& (leaveDTO.getLeaveForm().getEndDate().equals(dto.getEndDate()))) {
					daterangeList.add(leaveDTO);
				}
			}
			resultList = daterangeList;

		} else if (dto.getApplicant().getDepartment() != null) {
			List<LeaveStatusDTO> departmentList = new ArrayList<>();

			for (LeaveStatusDTO leaveDTO : resultList) {
				if (leaveDTO.getLeaveForm().getApplicant().getDepartment() == dto.getApplicant().getDepartment()) {
					departmentList.add(leaveDTO);
				}
			}
			resultList = departmentList;

		} else if (dto.getApplicant().getName() != null) {
			List<LeaveStatusDTO> nameList = new ArrayList<>();

			for (LeaveStatusDTO leaveDTO : resultList) {
				if (leaveDTO.getLeaveForm().getApplicant().getName().equals(dto.getApplicant().getName())) {
					nameList.add(leaveDTO);
				}
			}
			resultList = nameList;
		}

		return resultList;
	}

	// Select All Type Function Staff History Overtime
	public List<OvertimeStatusDTO> selectAllfinishedOTForm(OvertimeFormDTO dto) {
		System.out.println(dto);
		List<OvertimeStatusDTO> resultList = new ArrayList<>();
		resultList = otRepo.selectAllfinishedOTForm();

		if (dto.getOvertimeType() != null) {
			List<OvertimeStatusDTO> otList = new ArrayList<>();

			for (OvertimeStatusDTO otDTO : resultList) {
				if (otDTO.getOvertimeForm().getOvertimeType() == dto.getOvertimeType()) {
					otList.add(otDTO);
				}
			}
			resultList = otList;

		} else if ((dto.getStartTime() != null) && (dto.getEndTime() != null)) {
			List<OvertimeStatusDTO> timerangeList = new ArrayList<>();

			for (OvertimeStatusDTO otDTO : resultList) {
				if ((otDTO.getOvertimeForm().getStartTime().equals(dto.getStartTime()))
						&& (otDTO.getOvertimeForm().getEndTime().equals(dto.getEndTime()))) {
					timerangeList.add(otDTO);
				}
			}
			resultList = timerangeList;

		} else if (dto.getApplicant().getDepartment() != null) {
			List<OvertimeStatusDTO> departmentList = new ArrayList<>();

			for (OvertimeStatusDTO otDTO : resultList) {
				if (otDTO.getOvertimeForm().getApplicant().getDepartment() == dto.getApplicant().getDepartment()) {
					departmentList.add(otDTO);
				}
			}
			resultList = departmentList;

		} else if (dto.getApplicant().getName() != null) {
			List<OvertimeStatusDTO> nameList = new ArrayList<>();

			for (OvertimeStatusDTO otDTO : resultList) {
				if (otDTO.getOvertimeForm().getApplicant().getName().equals(dto.getApplicant().getName())) {
					nameList.add(otDTO);
				}
			}
			resultList = nameList;
		}

		return resultList;
	}

//Select All Approval Waiting Leave
	public LeaveStatusDTO selectPendingLeaveForm(String leaveFormId) {
		LeaveStatusDTO leaveStatusDto = new LeaveStatusDTO();
		leaveStatusDto = leaveRepo.selectPendingLeaveForm(leaveFormId);
		return leaveStatusDto;
	}

	public OvertimeStatusDTO selectPendingOTForm(String overtimeFormId) {
		OvertimeStatusDTO overtimeStatusDto = new OvertimeStatusDTO();
		overtimeStatusDto = otRepo.selectPendingOTForm(overtimeFormId);
		return overtimeStatusDto;
	}

//Select All Approval History Leave Status
	public List<LeaveStatusDTO> selectAllLeaveStatus() {
		List<LeaveStatusDTO> leaveStatusList = new ArrayList<LeaveStatusDTO>();
		leaveStatusList = (List<LeaveStatusDTO>) leaveRepo.findAll();
		return leaveStatusList;
	}

//Select All Approval History Overtime Status 
	public List<OvertimeStatusDTO> selectAllOTStatus() {
		List<OvertimeStatusDTO> overtimeStatusList = new ArrayList<OvertimeStatusDTO>();
		overtimeStatusList = (List<OvertimeStatusDTO>) otRepo.findAll();
		return overtimeStatusList;
	}

	public int leaveStatusUpdate(LeaveStatusDTO leaveStatusDTO, int id) {
		int rs = 0;
		leaveRepo.save(leaveStatusDTO);
		rs = 1;
		return rs;
	}

	public int overtimeStatusUpdate(OvertimeStatusDTO overtimeStatusDTO, int id) {
		int rs = 0;
		otRepo.save(overtimeStatusDTO);
		rs = 1;
		return rs;
	}

	// ApprovalLeaveHistory
	public List<LeaveStatusDTO> approvalLeaveHistory(LeaveFormDTO dto) {
		System.out.println(dto);
		List<LeaveStatusDTO> resultList = new ArrayList<>();
		UserDTO user = (UserDTO) session.getAttribute("sesUser");

		resultList = leaveRepo.approvalHistory(user);

		if (dto.getLeaveType() != null) {
			List<LeaveStatusDTO> leaveList = new ArrayList<>();

			for (LeaveStatusDTO leaveDTO : resultList) {
				if (leaveDTO.getLeaveForm().getLeaveType() == dto.getLeaveType()) {
					leaveList.add(leaveDTO);
				}
			}
			resultList = leaveList;

		} else if ((dto.getStartDate() != null) && (dto.getEndDate() != null)) {
			List<LeaveStatusDTO> daterangeList = new ArrayList<>();

			for (LeaveStatusDTO leaveDTO : resultList) {
				if ((leaveDTO.getLeaveForm().getStartDate().equals(dto.getStartDate()))
						&& (leaveDTO.getLeaveForm().getEndDate().equals(dto.getEndDate()))) {
					daterangeList.add(leaveDTO);
				}
			}
			resultList = daterangeList;

		} else if (dto.getApplicant().getDepartment() != null) {
			List<LeaveStatusDTO> departmentList = new ArrayList<>();

			for (LeaveStatusDTO leaveDTO : resultList) {
				if (leaveDTO.getLeaveForm().getApplicant().getDepartment() == dto.getApplicant().getDepartment()) {
					departmentList.add(leaveDTO);
				}
			}
			resultList = departmentList;

		} else if (dto.getApplicant().getName() != null) {
			List<LeaveStatusDTO> nameList = new ArrayList<>();

			for (LeaveStatusDTO leaveDTO : resultList) {
				if (leaveDTO.getLeaveForm().getApplicant().getName().equals(dto.getApplicant().getName())) {
					nameList.add(leaveDTO);
				}
			}
			resultList = nameList;
		}

		return resultList;
	}
	
	
	// ApprovalOvertimeHistory
		public List<OvertimeStatusDTO> approvalOvertimeHistory(OvertimeFormDTO dto) {
			System.out.println(dto);
			List<OvertimeStatusDTO> resultList = new ArrayList<>();
			UserDTO user = (UserDTO) session.getAttribute("sesUser");

			resultList = otRepo.approvalHistory(user);

			if (dto.getOvertimeType() != null) {
				List<OvertimeStatusDTO> otList = new ArrayList<>();

				for (OvertimeStatusDTO otDTO : resultList) {
					if (otDTO.getOvertimeForm().getOvertimeType() == dto.getOvertimeType()) {
						otList.add(otDTO);
					}
				}
				resultList = otList;

			} else if ((dto.getStartTime() != null) && (dto.getEndTime() != null)) {
				List<OvertimeStatusDTO> daterangeList = new ArrayList<>();

				for (OvertimeStatusDTO otDTO : resultList) {
					if ((otDTO.getOvertimeForm().getStartTime().equals(dto
							.getStartTime()))
							&& (otDTO.getOvertimeForm().getEndTime().equals(dto.getEndTime()))) {
						daterangeList.add(otDTO);
					}
				}
				resultList = daterangeList;

			} else if (dto.getApplicant().getDepartment() != null) {
				List<OvertimeStatusDTO> departmentList = new ArrayList<>();

				for (OvertimeStatusDTO otDTO : resultList) {
					if (otDTO.getOvertimeForm().getApplicant().getDepartment() == dto.getApplicant().getDepartment()) {
						departmentList.add(otDTO);
					}
				}
				resultList = departmentList;

			} else if (dto.getApplicant().getName() != null) {
				List<OvertimeStatusDTO> nameList = new ArrayList<>();

				for (OvertimeStatusDTO otDTO : resultList) {
					if (otDTO.getOvertimeForm().getApplicant().getName().equals(dto.getApplicant().getName())) {
						nameList.add(otDTO);
					}
				}
				resultList = nameList;
			}

			return resultList;
		}


}
