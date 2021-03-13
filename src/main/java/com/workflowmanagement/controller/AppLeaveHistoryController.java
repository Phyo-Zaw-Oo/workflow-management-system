package com.workflowmanagement.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.workflowmanagement.dao.LeaveTypeService;
import com.workflowmanagement.dao.StatusService;
import com.workflowmanagement.dto.DepartmentDTO;
import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.LeaveStatusDTO;
import com.workflowmanagement.dto.LeaveTypeDTO;
import com.workflowmanagement.model.DepartmentBean;
import com.workflowmanagement.model.LeaveFormBean;


@Controller
public class AppLeaveHistoryController {
	
	@Autowired 
	StatusService statusService;
	 
	@Autowired
	LeaveTypeService leaveTypeService;
	
	@RequestMapping(value = "/checkAppLeaveSetup", method = RequestMethod.GET)
	public ModelAndView searchAppLeaveSetUp(ModelMap model) {
	
			List<LeaveTypeDTO> leaveTypeList=leaveTypeService.selectAll();
			model.addAttribute("leaveTypeList",leaveTypeList);
			return new ModelAndView("WFMS-CHL-002","leaveForm",new LeaveFormBean());
		}
	
	@RequestMapping(value = "/checkAppLeave", method = RequestMethod.POST)
	public ModelAndView searchLeave(@ModelAttribute("leaveForm") LeaveFormBean bean,ModelMap model) {
		
		LeaveTypeDTO leaveTypeDto=new LeaveTypeDTO();
		leaveTypeDto = leaveTypeService.selectOne(bean.getLeaveType());
		LeaveFormDTO dto=new LeaveFormDTO();
		dto.setLeaveType(leaveTypeDto);
		
		String dateRange=bean.getDateRange();
		if(!dateRange.equals("")) {
		String startDate=dateRange.substring(0,10);
		String endDate=dateRange.substring(13);
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		}
		
		List<LeaveStatusDTO> leaveStatusList=statusService.applicationLeaveHistory(dto);
		
		List<LeaveTypeDTO> leaveTypeList=leaveTypeService.selectAll();
		if(leaveTypeList.size()==0) {
			model.addAttribute("err","Application leave history is not found!");
		}
		model.addAttribute("leaveTypeList",leaveTypeList);
		 
		return new ModelAndView("WFMS-CHL-002","leaveStatusList",leaveStatusList);
	}
	
	@RequestMapping(value = "/checkAppLeaveDetail", method = RequestMethod.GET)
	public ModelAndView checkAppLeaveDetail(@RequestParam(value = "id", required = true) String id) {
		
		LeaveFormDTO leaveForm=new LeaveFormDTO();
		leaveForm.setLeaveFormId(id);
		
		List<LeaveStatusDTO> detailList = statusService.findByLeaveForm(leaveForm);
		return new ModelAndView("WFMS-DHL-002", "detailList",detailList);

	}
	
	
	}

	
