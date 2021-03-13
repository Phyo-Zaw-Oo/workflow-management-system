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
import com.workflowmanagement.dao.OvertimeTypeService;
import com.workflowmanagement.dao.StatusService;
import com.workflowmanagement.dto.DepartmentDTO;
import com.workflowmanagement.dto.OvertimeFormDTO;
import com.workflowmanagement.dto.OvertimeStatusDTO;
import com.workflowmanagement.dto.OvertimeTypeDTO;
import com.workflowmanagement.dto.UserDTO;
import com.workflowmanagement.model.OvertimeFormBean;

@Controller
public class StaffOvertimeHistoryController {
	
	@Autowired
	StatusService statusService;

	@Autowired
	OvertimeTypeService otTypeService;
	
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping(value = "/checkStaffOtSetup", method = RequestMethod.GET)
	public ModelAndView searchAppLeaveSetUp(ModelMap model) {

		List<OvertimeTypeDTO> overtimeTypeList = otTypeService.selectAll();
		model.addAttribute("overtimeTypeList", overtimeTypeList);
		
		List<DepartmentDTO> deptList= departmentService.selectAll();
		model.addAttribute("deptList",deptList);

		return new ModelAndView("WFMS-CAO-002", "overtimeForm", new OvertimeFormBean());
	}
	
	@RequestMapping(value = "/checkStaffOt", method = RequestMethod.POST)
	public ModelAndView searchOt(@ModelAttribute("overtimeForm") OvertimeFormBean bean, ModelMap model) {
		
		
	
		
		OvertimeFormDTO dto = new OvertimeFormDTO();
		
		OvertimeTypeDTO otTypeDto = new OvertimeTypeDTO();
		otTypeDto = otTypeService.selectOne(bean.getOvertimeType());
		dto.setOvertimeType(otTypeDto);

		DepartmentDTO deptDto = new DepartmentDTO();
		deptDto = departmentService.selectOne(bean.getDepartment());
		UserDTO applicant = new UserDTO();
		applicant.setDepartment(deptDto);
		String name=bean.getApplicant();
		if(!name.equals("")) {
			applicant.setName(name);
		}
		
		dto.setApplicant(applicant);

		String timeRange = bean.getTimeRange();
		if (!timeRange.equals("")) {
			String startTime = timeRange.substring(0, 16);
			String endTime = timeRange.substring(19);
			dto.setStartTime(startTime);
			dto.setEndTime(endTime);
		}

		List<OvertimeStatusDTO> overtimeStatusList = statusService.selectAllfinishedOTForm(dto);
		
		if(overtimeStatusList.size()==0) {
			model.addAttribute("err", "Staff overtime history is not found!");
		}

		List<OvertimeTypeDTO> overtimeTypeList = otTypeService.selectAll();
		model.addAttribute("overtimeTypeList", overtimeTypeList);
		List<DepartmentDTO> deptList = departmentService.selectAll();
		model.addAttribute("deptList", deptList);
		
		return new ModelAndView("WFMS-CAO-002","overtimeStatusList",overtimeStatusList);
	}
	
	@RequestMapping(value = "/checkStaffOtDetail", method = RequestMethod.GET)
	public ModelAndView checkStaffLeaveDetail(@RequestParam(value = "id", required = true) String id) {
		
		OvertimeFormDTO overtimeForm=new OvertimeFormDTO();
		overtimeForm.setOvertimeFormId(id);
		
		List<OvertimeStatusDTO> detailList = statusService.findByOtForm(overtimeForm);
		return new ModelAndView("WFMS-SOH-002", "detailList",detailList);

	}

}
