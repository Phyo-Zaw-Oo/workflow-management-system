package com.workflowmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class StaffLeaveHistoryController {

	@Autowired
	StatusService statusService;

	@Autowired
	LeaveTypeService leaveTypeService;

	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "/checkStaffLeaveSetup", method = RequestMethod.GET)
	public ModelAndView searchStaffLeaveSetUp(ModelMap model) {

		List<LeaveTypeDTO> leaveTypeList = leaveTypeService.selectAll();
		model.addAttribute("leaveTypeList", leaveTypeList);
		List<DepartmentDTO> deptList = departmentService.selectAll();
		model.addAttribute("deptList", deptList);
		return new ModelAndView("WFMS-CAL-002", "leaveForm", new LeaveFormBean());
	}

	@RequestMapping(value = "/checkStaffLeave", method = RequestMethod.POST)
	public ModelAndView searchLeave(@ModelAttribute("leaveForm") LeaveFormBean bean, ModelMap model) {
		
		LeaveFormDTO dto = new LeaveFormDTO();
		
		LeaveTypeDTO leaveTypeDto = new LeaveTypeDTO();
		leaveTypeDto = leaveTypeService.selectOne(bean.getLeaveType());
		dto.setLeaveType(leaveTypeDto);

		DepartmentDTO deptDto = new DepartmentDTO();
		deptDto = departmentService.selectOne(bean.getDepartment());
		UserDTO applicant = new UserDTO();
		applicant.setDepartment(deptDto);
		String name=bean.getApplicant();
		if(!name.equals("")) {
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

		List<LeaveStatusDTO> leaveStatusList = statusService.selectAllfinishedLeaveForm(dto);
		
		if(leaveStatusList.size()==0) {
			model.addAttribute("err", "Staff leave history is not found!");
		}

		List<LeaveTypeDTO> leaveTypeList = leaveTypeService.selectAll();
		model.addAttribute("leaveTypeList", leaveTypeList);
		List<DepartmentDTO> deptList = departmentService.selectAll();
		model.addAttribute("deptList", deptList);

		return new ModelAndView("WFMS-CAL-002", "leaveStatusList", leaveStatusList);
	}
	
	@RequestMapping(value = "/checkStaffLeaveDetail", method = RequestMethod.GET)
	public ModelAndView checkStaffLeaveDetail(@RequestParam(value = "id", required = true) String id) {
		
		LeaveFormDTO leaveForm=new LeaveFormDTO();
		leaveForm.setLeaveFormId(id);
		
		List<LeaveStatusDTO> detailList = statusService.findByLeaveForm(leaveForm);
		return new ModelAndView("WFMS-SLH-002", "detailList",detailList);

	}
}
