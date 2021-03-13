package com.workflowmanagement.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.workflowmanagement.dao.OvertimeFormService;
import com.workflowmanagement.dao.OvertimeTypeService;
import com.workflowmanagement.dao.StatusService;
import com.workflowmanagement.dao.UserService;
import com.workflowmanagement.dto.OvertimeFormDTO;
import com.workflowmanagement.dto.OvertimeStatusDTO;
import com.workflowmanagement.dto.OvertimeTypeDTO;
import com.workflowmanagement.dto.UserDTO;
import com.workflowmanagement.model.OvertimeFormBean;

@Controller
public class OvertimeFormController {

	@Autowired
	private OvertimeFormService otFormService;
	@Autowired
	private OvertimeTypeService otTypeService;
	@Autowired
	private UserService userService;
	@Autowired
	private StatusService statusService;

	@RequestMapping(value = "/otFormSetUp", method = RequestMethod.GET)
	public String overtimeFormSetUp(ModelMap model,HttpSession session) {
		
		OvertimeFormBean otForm=new OvertimeFormBean();
		String otFormId=otFormService.generateOvertimeFormId();
		otForm.setOvertimeForm(otFormId);

		List<OvertimeTypeDTO> otTypeList = otTypeService.selectAll();
		UserDTO sesUser=(UserDTO) session.getAttribute("sesUser");
		List<UserDTO> userList = userService.selectAll();
		List<UserDTO> list=new ArrayList<>();
		for(UserDTO user:userList) {
			if((user.getDepartment().getDepartmentId().equals(sesUser.getDepartment().getDepartmentId())&&
					!user.getRole().getRoleName().equals("Staff"))||(user.getRole().getRoleName().equals("HR"))){
				list.add(user);
			}
		}
		model.addAttribute("otTypeList", otTypeList);
		model.addAttribute("userList", list);
		model.addAttribute("otForm", otForm);
		return "WFMS-AOT-001";
	}

	@RequestMapping(value = "/addOvertimeForm", method = RequestMethod.POST)
	public String addOTForm(@ModelAttribute("otForm") @Validated OvertimeFormBean bean, BindingResult bs, 
			Model model,HttpSession session) {
		
		OvertimeFormBean otForm=new OvertimeFormBean();
		String otFormId=otFormService.generateOvertimeFormId();
		otForm.setOvertimeForm(otFormId);

		List<OvertimeTypeDTO> otTypeList = otTypeService.selectAll();
		UserDTO sesUser=(UserDTO) session.getAttribute("sesUser");
		List<UserDTO> userList = userService.selectAll();
		List<UserDTO> list=new ArrayList<>();
		for(UserDTO user:userList) {
			if((user.getDepartment().getDepartmentId().equals(sesUser.getDepartment().getDepartmentId())&&
					!user.getRole().getRoleName().equals("Staff"))||(user.getRole().getRoleName().equals("HR"))){
				list.add(user);
			}
		}
		model.addAttribute("otTypeList", otTypeList);
		model.addAttribute("userList", list);
		model.addAttribute("otForm", otForm);

		if (bs.hasErrors()) {
			model.addAttribute("err", "Fields must not be null");
			model.addAttribute("otForm", bean);
			return "WFMS-AOT-001";
		}

		OvertimeFormDTO otFormDto = new OvertimeFormDTO();

		OvertimeTypeDTO otTypeDto = otTypeService.selectOne(bean.getOvertimeType());

		otFormDto.setOvertimeFormId(bean.getOvertimeForm());
		System.out.println(bean.getOvertimeForm());

		otFormDto.setOvertimeType(otTypeDto);
		
		UserDTO applicant=(UserDTO) session.getAttribute("sesUser");
		otFormDto.setApplicant(applicant);
		

		String timeRange = bean.getTimeRange();
		String startTime = timeRange.substring(0, 16);
		String endTime = timeRange.substring(19);
		otFormDto.setStartTime(startTime);
		otFormDto.setEndTime(endTime);
		
		try {
			
			java.util.Date date1 = new SimpleDateFormat("MM/dd/yyyy hh:mm").parse(endTime);
			java.util.Date date2=new SimpleDateFormat("MM/dd/yyyy hh:mm").parse(startTime);
			long diffMs = date1.getTime() - date2.getTime();
			long diffSec = diffMs / 1000;
			long min = diffSec / 60;
			
			long hours = min / 60; 
			long minutes = min % 60;
			String duration=hours +" hr "+minutes+" min";
			otFormDto.setTimeRange(duration);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		otFormDto.setReason(bean.getReason());

		long millis = System.currentTimeMillis();
		otFormDto.setCreatedDate(new Date(millis));

		OvertimeStatusDTO otStatusDto = new OvertimeStatusDTO();

		UserDTO usrDto = userService.selectOne(bean.getSubmitTo());
		otStatusDto.setApprover(usrDto);

		otStatusDto.setOvertimeForm(otFormDto);

		otStatusDto.setStatus("Pending");

		int rs=otFormService.insert(otFormDto);
		if (rs == 0) {
			model.addAttribute("err", "Apply Overtime form is failed!");
			return "WFMS-AOT-001";
		} else {

			model.addAttribute("msg", "Overtime Form is applied successfully!");
			statusService.insertOvertime(otStatusDto);
			return "WFMS-AOT-001";

		}
		
	}

	
}