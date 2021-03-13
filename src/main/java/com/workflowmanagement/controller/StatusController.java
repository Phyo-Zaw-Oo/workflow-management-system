package com.workflowmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.workflowmanagement.dao.LeaveFormService;
import com.workflowmanagement.dao.OvertimeFormService;
import com.workflowmanagement.dao.StatusService;
import com.workflowmanagement.dto.LeaveStatusDTO;
import com.workflowmanagement.dto.OvertimeStatusDTO;
import com.workflowmanagement.dto.UserDTO;

@Controller
public class StatusController {

	@Autowired
	StatusService statusService;

	@Autowired
	LeaveFormService leaveFormService;

	@Autowired
	OvertimeFormService otService;

	@RequestMapping(value = "/checkStatus", method = RequestMethod.GET)
	public String checkStatus(ModelMap model, HttpSession session) {
		
		List<LeaveStatusDTO> leaveStatusList = statusService.selectUnfinishedleaveForm();
		model.addAttribute("leaveStatusList", leaveStatusList);
		
		List<OvertimeStatusDTO> otStatusList = statusService.selectUnfinishedOTForm();
		model.addAttribute("otStatusList", otStatusList);
		return "WFMS-CHS-002";
	}
}