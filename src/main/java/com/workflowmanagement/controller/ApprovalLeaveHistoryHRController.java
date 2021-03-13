package com.workflowmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.workflowmanagement.dao.DepartmentService;
import com.workflowmanagement.dao.LeaveTypeService;
import com.workflowmanagement.dao.StatusService;
import com.workflowmanagement.dto.DepartmentDTO;
import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.LeaveStatusDTO;
import com.workflowmanagement.dto.LeaveTypeDTO;
import com.workflowmanagement.dto.UserDTO;
import com.workflowmanagement.model.LeaveFormBean;

@Controller
public class ApprovalLeaveHistoryHRController {

	@Autowired
	StatusService statusService;

	@Autowired
	LeaveTypeService leaveTypeService;

	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "/leaveApprovalHRView", method = RequestMethod.GET)
	public ModelAndView approvalHistorySetUp(ModelMap model) {

		List<LeaveTypeDTO> leaveTypeList = leaveTypeService.selectAll();
		model.addAttribute("leaveTypeList", leaveTypeList);
		List<DepartmentDTO> deptList = departmentService.selectAll();
		model.addAttribute("deptList", deptList);
		return new ModelAndView("WFMS-RAL-002", "leaveForm", new LeaveFormBean());
	}

	@RequestMapping(value = "/approvalHistory", method = RequestMethod.POST)
	public ModelAndView searchLeave(@ModelAttribute("leaveForm") LeaveFormBean bean, ModelMap model) {

		LeaveFormDTO dto = new LeaveFormDTO();

		LeaveTypeDTO leaveTypeDto = new LeaveTypeDTO();
		leaveTypeDto = leaveTypeService.selectOne(bean.getLeaveType());
		dto.setLeaveType(leaveTypeDto);

		DepartmentDTO deptDto = new DepartmentDTO();
		deptDto = departmentService.selectOne(bean.getDepartment());
		UserDTO applicant = new UserDTO();
		applicant.setDepartment(deptDto);
		String name = bean.getApplicant();
		if (!name.equals("")) {
			applicant.setName(name);
		}

		dto.setApplicant(applicant);

		String dateRange = bean.getDateRange();
		if (!dateRange.equals("")) {
			String startDate = dateRange.substring(0, 10);
			String endDate = dateRange.substring(13);
			dto.setStartDate(startDate);
			dto.setEndDate(endDate);
		}

		List<LeaveStatusDTO> leaveStatusList = statusService.approvalLeaveHistory(dto);

		if(leaveStatusList.size()==0) {
			model.addAttribute("err", "Approval leave history is not found!");
		}
		List<LeaveTypeDTO> leaveTypeList = leaveTypeService.selectAll();
		model.addAttribute("leaveTypeList", leaveTypeList);
		List<DepartmentDTO> deptList = departmentService.selectAll();
		model.addAttribute("deptList", deptList);

		return new ModelAndView("WFMS-RAL-002", "leaveStatusList", leaveStatusList);
	}

}
