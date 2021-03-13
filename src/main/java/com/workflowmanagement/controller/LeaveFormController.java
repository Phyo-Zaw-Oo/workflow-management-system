package com.workflowmanagement.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.workflowmanagement.dao.LeaveFormService;
import com.workflowmanagement.dao.LeaveTypeService;
import com.workflowmanagement.dao.StatusService;
import com.workflowmanagement.dao.UserService;
import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.LeaveStatusDTO;
import com.workflowmanagement.dto.LeaveTypeDTO;
import com.workflowmanagement.dto.UserDTO;
import com.workflowmanagement.model.LeaveFormBean;


@Controller
public class LeaveFormController {

	@Autowired
	private LeaveFormService leaveFormService;
	@Autowired
	private LeaveTypeService leaveTypeService;
	@Autowired
	private UserService userService;
	@Autowired
	private StatusService statusService;

	@RequestMapping(value = "/leaveFormSetUp", method = RequestMethod.GET)
	public String leaveFormSetUp(ModelMap model,HttpSession session) {
		
		LeaveFormBean leaveForm=new LeaveFormBean();
		String leaveFormId=leaveFormService.generateLeaveFormId();
		leaveForm.setLeaveForm(leaveFormId);
		
		List<LeaveTypeDTO> leaveList = leaveTypeService.selectAll();
		UserDTO sesUser=(UserDTO) session.getAttribute("sesUser");
		List<UserDTO> userList = userService.selectAll();
		List<UserDTO> list=new ArrayList<>();
		for(UserDTO user:userList) {
			if(user.getDepartment().getDepartmentId().equals(sesUser.getDepartment().getDepartmentId())){
				list.add(user);
			}
		}
		List<UserDTO> roleList=new ArrayList<>();
		for(UserDTO user:userList) {
			if((user.getDepartment().getDepartmentId().equals(sesUser.getDepartment().getDepartmentId())&&
					!user.getRole().getRoleName().equals("Staff"))||(user.getRole().getRoleName().equals("HR"))){
				roleList.add(user);
			}
		}
		model.addAttribute("submitToUser", roleList);
		model.addAttribute("leaveList", leaveList);
		model.addAttribute("userList", list);
		model.addAttribute("leaveForm", leaveForm);
		return "WFMS-ALV-001";
	}

	@RequestMapping(value = "/addLeaveForm", method = RequestMethod.POST)
	public String addLeaveForm(@ModelAttribute("leaveForm") @Validated LeaveFormBean bean,
			Model model,HttpSession session) {
		
		LeaveFormBean leaveForm=new LeaveFormBean();
		String leaveFormId=leaveFormService.generateLeaveFormId();
		leaveForm.setLeaveForm(leaveFormId);
		
		List<LeaveTypeDTO> leaveList = leaveTypeService.selectAll();
		UserDTO sesUser=(UserDTO) session.getAttribute("sesUser");
		List<UserDTO> userList = userService.selectAll();
		List<UserDTO> list=new ArrayList<>();
		for(UserDTO user:userList) {
			if(user.getDepartment().getDepartmentId().equals(sesUser.getDepartment().getDepartmentId())){
				list.add(user);
			}
		}
		List<UserDTO> roleList=new ArrayList<>();
		for(UserDTO user:userList) {
			if((user.getDepartment().getDepartmentId().equals(sesUser.getDepartment().getDepartmentId())&&
					!user.getRole().getRoleName().equals("Staff"))||(user.getRole().getRoleName().equals("HR"))){
				roleList.add(user);
			}
		}
		model.addAttribute("submitToUser", roleList);
		model.addAttribute("leaveList", leaveList);
		model.addAttribute("userList", list);
		model.addAttribute("leaveForm", leaveForm);
	

		LeaveFormDTO leaveFormDto = new LeaveFormDTO();
		LeaveTypeDTO leaveTypeDto = leaveTypeService.selectOne(bean.getLeaveType());

		leaveFormDto.setLeaveFormId(bean.getLeaveForm());
		leaveFormDto.setLeaveType(leaveTypeDto);
			
		UserDTO applicant=(UserDTO) session.getAttribute("sesUser");
		leaveFormDto.setApplicant(applicant);
			
		leaveFormDto.setFullOrHalf(bean.getFullOrHalf());
		
		String dateRange=bean.getDateRange();
		String startDate=dateRange.substring(0,10);
		String endDate=dateRange.substring(13);
		
		leaveFormDto.setStartDate(startDate);
		leaveFormDto.setEndDate(endDate);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		LocalDate date1 = LocalDate.parse(startDate, dtf);
		LocalDate date2 = LocalDate.parse(endDate, dtf);
		
		double duration = ChronoUnit.DAYS.between(date1, date2);
		leaveFormDto.setDateRange(duration+1);
		
		
		UserDTO usrDto=userService.selectOne(bean.getAssignTo()); 

		leaveFormDto.setAssignTo(usrDto);
		leaveFormDto.setReason(bean.getReason());
		long millis=System.currentTimeMillis();
		leaveFormDto.setCreatedDate(new Date(millis));

		LeaveStatusDTO stsDto = new LeaveStatusDTO();
		UserDTO usrStatus=userService.selectOne(bean.getSubmitTo());
		stsDto.setApprover(usrStatus);
		stsDto.setLeaveForm(leaveFormDto);
		
		stsDto.setStatus("Pending");
		
		int rs=leaveFormService.insert(leaveFormDto);
		
		if (rs == 0) {
			model.addAttribute("err", "Apply Leave form is failed!");
			return "WFMS-ALV-001";
		} else {

			model.addAttribute("msg", "Leave Form is applied successfully!");
			statusService.insertLeave(stsDto);
			return "WFMS-ALV-001";

		}
		

	}

}
