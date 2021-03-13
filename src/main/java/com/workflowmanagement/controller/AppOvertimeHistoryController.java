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

import com.workflowmanagement.dao.OvertimeTypeService;
import com.workflowmanagement.dao.StatusService;
import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.LeaveStatusDTO;
import com.workflowmanagement.dto.OvertimeFormDTO;
import com.workflowmanagement.dto.OvertimeStatusDTO;
import com.workflowmanagement.dto.OvertimeTypeDTO;

import com.workflowmanagement.model.OvertimeFormBean;

@Controller
public class AppOvertimeHistoryController {

	@Autowired
	StatusService statusService;

	@Autowired
	OvertimeTypeService otTypeService;

	@RequestMapping(value = "/checkAppOtSetup", method = RequestMethod.GET)
	public ModelAndView searchAppLeaveSetUp(ModelMap model) {

		List<OvertimeTypeDTO> overtimeTypeList = otTypeService.selectAll();

		model.addAttribute("overtimeTypeList", overtimeTypeList);

		return new ModelAndView("WFMS-CHO-002", "overtimeForm", new OvertimeFormBean());
	}

	@RequestMapping(value = "/checkAppOt", method = RequestMethod.POST)
	public ModelAndView searchOt(@ModelAttribute("overtimeForm") OvertimeFormBean bean, ModelMap model) {

		
//check OT Form Application History By All/OtType/TimeRange
		OvertimeTypeDTO otTypeDto=new OvertimeTypeDTO();
		otTypeDto = otTypeService.selectOne(bean.getOvertimeType());
		OvertimeFormDTO dto=new OvertimeFormDTO();
		dto.setOvertimeType(otTypeDto);
		
		String timeRange=bean.getTimeRange();
		if(!timeRange.equals("")) {
		String startTime=timeRange.substring(0,16);
		String endTime=timeRange.substring(19);
		dto.setStartTime(startTime);
		dto.setEndTime(endTime);
		}
		
		List<OvertimeStatusDTO> overtimeStatusList=statusService.applicationOTHistory(dto);
		 
		List<OvertimeTypeDTO> overtimeTypeList=otTypeService.selectAll();
		if(overtimeTypeList.size()==0) {
			model.addAttribute("err","Application overtime history is not found!");
		}
		model.addAttribute("overtimeTypeList",overtimeTypeList);
		  
		return new ModelAndView("WFMS-CHO-002","overtimeStatusList",overtimeStatusList);
	}

	@RequestMapping(value = "/checkAppOtDetail", method = RequestMethod.GET)
	public ModelAndView checkAppOtDetail(@RequestParam(value = "id", required = true) String id) {
		
		OvertimeFormDTO otForm=new OvertimeFormDTO();
		otForm.setOvertimeFormId(id);
		
		List<OvertimeStatusDTO> detailList = statusService.findByOtForm(otForm);
		return new ModelAndView("WFMS-DHO-002", "detailList",detailList);

	}
	
}
