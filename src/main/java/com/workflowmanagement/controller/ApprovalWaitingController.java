package com.workflowmanagement.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.workflowmanagement.dao.LeaveFormService;
import com.workflowmanagement.dao.LeaveTypeService;
import com.workflowmanagement.dao.OvertimeFormService;
import com.workflowmanagement.dao.OvertimeTypeService;
import com.workflowmanagement.dao.StatusService;
import com.workflowmanagement.dao.UserService;
import com.workflowmanagement.dto.LeaveApprovalWaitingDTO;
import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.LeaveStatusDTO;
import com.workflowmanagement.dto.LeaveTypeDTO;
import com.workflowmanagement.dto.OvertimeApprovalWaitingDTO;
import com.workflowmanagement.dto.OvertimeFormDTO;
import com.workflowmanagement.dto.OvertimeStatusDTO;
import com.workflowmanagement.dto.OvertimeTypeDTO;
import com.workflowmanagement.dto.UserDTO;
import com.workflowmanagement.model.LeaveFormBean;
import com.workflowmanagement.model.OvertimeFormBean;

@Controller
public class ApprovalWaitingController {

	@Autowired
	private LeaveFormService leaveFormService;

	@Autowired
	private OvertimeFormService overtimeFormService;

	@Autowired
	private LeaveTypeService leaveTypeService;

	@Autowired
	private OvertimeTypeService overtimeTypeService;

	@Autowired
	private UserService userService;

	@Autowired
	StatusService statusService;

	@RequestMapping(value = "/showLeaveApprovalWaiting", method = RequestMethod.GET)
	public String showLeaveApprovalWaitingForHR(ModelMap model, HttpSession session) {
		List<LeaveApprovalWaitingDTO> leaveApprovalWaitingDTOList = new ArrayList<LeaveApprovalWaitingDTO>();
		List<OvertimeApprovalWaitingDTO> overtimeApprovalWaitingDTOList = new ArrayList<OvertimeApprovalWaitingDTO>();
		UserDTO userDTO = (UserDTO) session.getAttribute("sesUser");
		List<LeaveStatusDTO> leaveStatusDTOList = statusService.selectAllLeaveStatus();
		List<OvertimeStatusDTO> overtimeStatusDTOList = statusService.selectAllOTStatus();
		for (LeaveStatusDTO dto : leaveStatusDTOList) {
			if (dto.getApprover().getUserId().equals(userDTO.getUserId()) && dto.getStatus().equals("Pending")) {
				LeaveApprovalWaitingDTO leaveApprovalWaitingDTO = new LeaveApprovalWaitingDTO();
				leaveApprovalWaitingDTO.setLeaveFormId(dto.getLeaveForm().getLeaveFormId());
				leaveApprovalWaitingDTO.setApplicant(dto.getLeaveForm().getApplicant().getName());
				leaveApprovalWaitingDTO.setStartDate(dto.getLeaveForm().getStartDate());
				leaveApprovalWaitingDTO.setEndDate(dto.getLeaveForm().getEndDate());
				leaveApprovalWaitingDTO.setDuration(Double.toString(dto.getLeaveForm().getDateRange()));
				leaveApprovalWaitingDTO.setAssignTo(dto.getLeaveForm().getAssignTo().getName());
				leaveApprovalWaitingDTO.setReason(dto.getLeaveForm().getReason());
				leaveApprovalWaitingDTOList.add(leaveApprovalWaitingDTO);
			}
		}
		for (OvertimeStatusDTO dto : overtimeStatusDTOList) {
			if (dto.getApprover().getUserId().equals(userDTO.getUserId()) && dto.getStatus().equals("Pending")) {
				OvertimeApprovalWaitingDTO overtimeApprovalWaitingDTO = new OvertimeApprovalWaitingDTO();
				overtimeApprovalWaitingDTO.setOvertimeFormId(dto.getOvertimeForm().getOvertimeFormId());
				overtimeApprovalWaitingDTO.setApplicant(dto.getOvertimeForm().getApplicant().getName());
				overtimeApprovalWaitingDTO.setStartTime(dto.getOvertimeForm().getStartTime());
				overtimeApprovalWaitingDTO.setEndTime(dto.getOvertimeForm().getEndTime());
				overtimeApprovalWaitingDTO.setDuration(dto.getOvertimeForm().getTimeRange());
				overtimeApprovalWaitingDTO.setReason(dto.getOvertimeForm().getReason());
				overtimeApprovalWaitingDTOList.add(overtimeApprovalWaitingDTO);
			}
		}
		model.addAttribute("leaveApprovalWaitingDTOList", leaveApprovalWaitingDTOList);
		model.addAttribute("overtimeApprovalWaitingDTOList", overtimeApprovalWaitingDTOList);
		return "WFMS-HRH-002";
	}

	@RequestMapping(value = "/showLeaveApprovalWaitingForHODAndPM", method = RequestMethod.GET)
	public String showLeaveApprovalWaitingForHODAndPM(ModelMap model, HttpSession session) {
		List<LeaveApprovalWaitingDTO> leaveApprovalWaitingDTOList = new ArrayList<LeaveApprovalWaitingDTO>();
		List<OvertimeApprovalWaitingDTO> overtimeApprovalWaitingDTOList = new ArrayList<OvertimeApprovalWaitingDTO>();
		UserDTO userDTO = (UserDTO) session.getAttribute("sesUser");
		List<LeaveStatusDTO> leaveStatusDTOList = statusService.selectAllLeaveStatus();
		List<OvertimeStatusDTO> overtimeStatusDTOList = statusService.selectAllOTStatus();
		for (LeaveStatusDTO dto : leaveStatusDTOList) {
			if (dto.getApprover().getUserId().equals(userDTO.getUserId()) && dto.getStatus().equals("Pending")) {
				LeaveApprovalWaitingDTO leaveApprovalWaitingDTO = new LeaveApprovalWaitingDTO();
				leaveApprovalWaitingDTO.setLeaveFormId(dto.getLeaveForm().getLeaveFormId());
				leaveApprovalWaitingDTO.setApplicant(dto.getLeaveForm().getApplicant().getName());
				leaveApprovalWaitingDTO.setStartDate(dto.getLeaveForm().getStartDate());
				leaveApprovalWaitingDTO.setEndDate(dto.getLeaveForm().getEndDate());
				leaveApprovalWaitingDTO.setDuration(Double.toString(dto.getLeaveForm().getDateRange()));
				leaveApprovalWaitingDTO.setAssignTo(dto.getLeaveForm().getAssignTo().getName());
				leaveApprovalWaitingDTO.setReason(dto.getLeaveForm().getReason());
				leaveApprovalWaitingDTOList.add(leaveApprovalWaitingDTO);
			}
		}
		for (OvertimeStatusDTO dto : overtimeStatusDTOList) {
			if (dto.getApprover().getUserId().equals(userDTO.getUserId()) && dto.getStatus().equals("Pending")) {
				OvertimeApprovalWaitingDTO overtimeApprovalWaitingDTO = new OvertimeApprovalWaitingDTO();
				overtimeApprovalWaitingDTO.setOvertimeFormId(dto.getOvertimeForm().getOvertimeFormId());
				overtimeApprovalWaitingDTO.setApplicant(dto.getOvertimeForm().getApplicant().getName());
				overtimeApprovalWaitingDTO.setStartTime(dto.getOvertimeForm().getStartTime());
				overtimeApprovalWaitingDTO.setEndTime(dto.getOvertimeForm().getEndTime());
				overtimeApprovalWaitingDTO.setDuration(dto.getOvertimeForm().getTimeRange());
				overtimeApprovalWaitingDTO.setReason(dto.getOvertimeForm().getReason());
				overtimeApprovalWaitingDTOList.add(overtimeApprovalWaitingDTO);
			}
		}
		List<UserDTO> userDTOList = userService.selectApproverNameList();
		List<UserDTO> nameList = new ArrayList<UserDTO>();
		for (UserDTO dto : userDTOList) {
			if (userDTO.getDepartment().getDepartmentId().equals(dto.getDepartment().getDepartmentId())
					&& dto.getRole().getRoleName().equals("Project Manager")
					|| userDTO.getDepartment().getDepartmentId().equals(dto.getDepartment().getDepartmentId())
							&& dto.getRole().getRoleName().equals("Head of Department")
					|| dto.getRole().getRoleName().equals("HR"))
				nameList.add(dto);
		}
		model.addAttribute("leaveApprovalWaitingDTOList", leaveApprovalWaitingDTOList);
		model.addAttribute("overtimeApprovalWaitingDTOList", overtimeApprovalWaitingDTOList);
		model.addAttribute("approverNameList", nameList);
		return "WFMS-PHH-002";
	}

	@RequestMapping(value = "/rejectForm", method = RequestMethod.POST)
	public String rejectForm(@RequestParam(value = "comment") String comment, @RequestParam(value = "id") String id,
			ModelMap model,HttpSession session) {
		if (id.substring(0, 2).equalsIgnoreCase("lv")) {
			LeaveStatusDTO leaveStatusDTO = statusService.selectPendingLeaveForm(id);
			leaveStatusDTO.setComment(comment);
			leaveStatusDTO.setStatus("Rejected");
			long millis = System.currentTimeMillis();
			leaveStatusDTO.setCreatedDate(new Date(millis));
			statusService.leaveStatusUpdate(leaveStatusDTO, leaveStatusDTO.getStatusId());
			List<LeaveStatusDTO> leaveStatusList = statusService.selectUnfinishedleaveForm();
			model.addAttribute("leaveStatusList", leaveStatusList);
		} else if (id.substring(0, 2).equalsIgnoreCase("ot")) {
			OvertimeStatusDTO overtimeStatusDTO = statusService.selectPendingOTForm(id);
			overtimeStatusDTO.setComment(comment);
			overtimeStatusDTO.setStatus("Rejected");
			long millis = System.currentTimeMillis();
			overtimeStatusDTO.setCreatedDate(new Date(millis));
			statusService.overtimeStatusUpdate(overtimeStatusDTO, overtimeStatusDTO.getStatusId());
			List<OvertimeStatusDTO> overtimeStatusList = statusService.selectUnfinishedOTForm();
			model.addAttribute("otStatusList", overtimeStatusList);
		}
		UserDTO userDTO = (UserDTO) session.getAttribute("sesUser");
		if(userDTO.getRole().getRoleName().equals("Project Manager") || userDTO.getRole().getRoleName().equals("Head of Department")) {
			return "redirect:/showLeaveApprovalWaitingForHODAndPM";
		}
		return "redirect:/showLeaveApprovalWaiting";
	}

	@RequestMapping(value = "/sendBackForm", method = RequestMethod.POST)
	public String sendBackForm(@RequestParam(value = "comment") String comment, @RequestParam(value = "id") String id,
			ModelMap model,HttpSession session) {
		if (id.substring(0, 2).equalsIgnoreCase("lv")) {
			LeaveStatusDTO leaveStatusDTO = statusService.selectPendingLeaveForm(id);
			leaveStatusDTO.setComment(comment);
			leaveStatusDTO.setStatus("Sendback");
			long millis = System.currentTimeMillis();
			leaveStatusDTO.setCreatedDate(new Date(millis));
			statusService.leaveStatusUpdate(leaveStatusDTO, leaveStatusDTO.getStatusId());
			List<LeaveStatusDTO> leaveStatusList = statusService.selectUnfinishedleaveForm();
			model.addAttribute("leaveStatusList", leaveStatusList);
		} else if (id.substring(0, 2).equalsIgnoreCase("ot")) {
			OvertimeStatusDTO overtimeStatusDTO = statusService.selectPendingOTForm(id);
			overtimeStatusDTO.setComment(comment);
			overtimeStatusDTO.setStatus("Sendback");
			long millis = System.currentTimeMillis();
			overtimeStatusDTO.setCreatedDate(new Date(millis));
			statusService.overtimeStatusUpdate(overtimeStatusDTO, overtimeStatusDTO.getStatusId());
			List<OvertimeStatusDTO> overtimeStatusList = statusService.selectUnfinishedOTForm();
			model.addAttribute("otStatusList", overtimeStatusList);
		}
		UserDTO userDTO = (UserDTO) session.getAttribute("sesUser");
		if(userDTO.getRole().getRoleName().equals("Project Manager") || userDTO.getRole().getRoleName().equals("Head of Department")) {
			return "redirect:/showLeaveApprovalWaitingForHODAndPM";
		}
		return "redirect:/showLeaveApprovalWaiting";
	}

	@RequestMapping(value = "/approveCompleteForm", method = RequestMethod.POST)
	public String approveCompleteForm(@RequestParam(value = "comment") String comment,
			@RequestParam(value = "id") String id, ModelMap model,HttpSession session) {
		if (id.substring(0, 2).equalsIgnoreCase("lv")) {
			LeaveStatusDTO leaveStatusDTO = statusService.selectPendingLeaveForm(id);
			leaveStatusDTO.setComment(comment);
			leaveStatusDTO.setStatus("Approved Complete");
			long millis = System.currentTimeMillis();
			leaveStatusDTO.setCreatedDate(new Date(millis));
			statusService.leaveStatusUpdate(leaveStatusDTO, leaveStatusDTO.getStatusId());
			List<LeaveStatusDTO> leaveStatusList = statusService.selectUnfinishedleaveForm();
			model.addAttribute("leaveStatusList", leaveStatusList);
		} else if (id.substring(0, 2).equalsIgnoreCase("ot")) {
			OvertimeStatusDTO overtimeStatusDTO = statusService.selectPendingOTForm(id);
			overtimeStatusDTO.setComment(comment);
			overtimeStatusDTO.setStatus("Approved Complete");
			long millis = System.currentTimeMillis();
			overtimeStatusDTO.setCreatedDate(new Date(millis));
			statusService.overtimeStatusUpdate(overtimeStatusDTO, overtimeStatusDTO.getStatusId());
			List<OvertimeStatusDTO> overtimeStatusList = statusService.selectUnfinishedOTForm();
			model.addAttribute("otStatusList", overtimeStatusList);
		}
		UserDTO userDTO = (UserDTO) session.getAttribute("sesUser");
		if(userDTO.getRole().getRoleName().equals("Project Manager") || userDTO.getRole().getRoleName().equals("Head of Department")) {
			return "redirect:/showLeaveApprovalWaitingForHODAndPM";
		}
		return "redirect:/showLeaveApprovalWaiting";
	}

	@RequestMapping(value = "/submitApproveCompleteForm", method = RequestMethod.POST)
	public String submitApproveCompleteForm(@RequestParam(value = "comment") String comment,
			@RequestParam(value = "id") String id, @RequestParam(value = "name") String name, ModelMap model,
			HttpSession session) {
		if (id.substring(0, 2).equalsIgnoreCase("lv")) {
			LeaveStatusDTO leaveStatusDTO = statusService.selectPendingLeaveForm(id);
			// UserDTO userDTO1 = (UserDTO) session.getAttribute("sesUser");
			// UserDTO userDTO2 = userService.selectOneByApproverName(userDTO1.getName());
			// leaveStatusDTO.setApprover(userDTO2);
			leaveStatusDTO.setComment(comment);
			leaveStatusDTO.setStatus("Approved");
			long millis = System.currentTimeMillis();
			leaveStatusDTO.setCreatedDate(new Date(millis));
			statusService.leaveStatusUpdate(leaveStatusDTO, leaveStatusDTO.getStatusId());

			LeaveStatusDTO leaveStatusDTO1 = new LeaveStatusDTO();
			UserDTO userDTO = userService.selectOneByApproverName(name);
			leaveStatusDTO1.setLeaveForm(leaveStatusDTO.getLeaveForm());
			leaveStatusDTO1.setApprover(userDTO);
			leaveStatusDTO1.setStatus("Pending");
			leaveStatusDTO1.setComment("");
			long millis1 = System.currentTimeMillis();
			leaveStatusDTO1.setCreatedDate(new Date(millis1));
			statusService.insertLeave(leaveStatusDTO1);
			List<LeaveStatusDTO> leaveStatusList = statusService.selectUnfinishedleaveForm();
			model.addAttribute("leaveStatusList", leaveStatusList);
		} else if (id.substring(0, 2).equalsIgnoreCase("ot")) {
			OvertimeStatusDTO overtimeStatusDTO = statusService.selectPendingOTForm(id);
			overtimeStatusDTO.setComment(comment);
			overtimeStatusDTO.setStatus("Approved");
			statusService.overtimeStatusUpdate(overtimeStatusDTO, overtimeStatusDTO.getStatusId());

			OvertimeStatusDTO overtimeStatusDTO1 = new OvertimeStatusDTO();
			UserDTO userDTO = userService.selectOneByApproverName(name);
			overtimeStatusDTO1.setOvertimeForm(overtimeStatusDTO.getOvertimeForm());
			overtimeStatusDTO1.setApprover(userDTO);
			overtimeStatusDTO1.setStatus("Pending");
			overtimeStatusDTO1.setComment("");
			long millis = System.currentTimeMillis();
			overtimeStatusDTO1.setCreatedDate(new Date(millis));
			statusService.insertOvertime(overtimeStatusDTO1);
			List<OvertimeStatusDTO> overtimeStatusList = statusService.selectUnfinishedOTForm();
			model.addAttribute("otStatusList", overtimeStatusList);
		}
		UserDTO userDTO = (UserDTO) session.getAttribute("sesUser");
		if(userDTO.getRole().getRoleName().equals("Project Manager") || userDTO.getRole().getRoleName().equals("Head of Department")) {
			return "redirect:/showLeaveApprovalWaitingForHODAndPM";
		}
		return "redirect:/showLeaveApprovalWaiting";
	}

	@RequestMapping(value = "/sendBackLeaveFormSetUp", method = RequestMethod.GET)
	public String rejectOTFormSetUp(@RequestParam String id, ModelMap model,HttpSession session) {

		LeaveFormDTO leaveFormDTO = leaveFormService.selectLeaveFormById(id);
		LeaveFormBean leaveFormBean = new LeaveFormBean();
		leaveFormBean.setLeaveForm(leaveFormDTO.getLeaveFormId());
		
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

		/*
		 * leaveFormBean.setApplicant(leaveFormDTO.getApplicant().getName());
		 * leaveFormBean.setAssignTo(leaveFormDTO.getAssignTo().getName());
		 * leaveFormBean.setCreatedDate(leaveFormDTO.getCreatedDate());
		 * leaveFormBean.setDateRange(leaveFormDTO.getDateRange());
		 * leaveFormBean.setFullOrHalf(leaveFormDTO.getFullOrHalf());
		 */

		List<LeaveTypeDTO> leaveList = leaveTypeService.selectAll();
		
		model.addAttribute("submitToUser", roleList);
		model.addAttribute("leaveList", leaveList);
		model.addAttribute("userList", list);
		model.addAttribute("leaveForm", leaveFormBean);
		return "WFMS-ALV-003";
	}

	@RequestMapping(value = "/sendBackOTFormSetUp", method = RequestMethod.GET)
	public String rejectLeaveFormSetUp(@RequestParam String id, ModelMap model,HttpSession session) {
		
		List<OvertimeTypeDTO> otTypeList = overtimeTypeService.selectAll();
		UserDTO sesUser=(UserDTO) session.getAttribute("sesUser");
		List<UserDTO> userList = userService.selectAll();
		List<UserDTO> list=new ArrayList<>();
		for(UserDTO user:userList) {
			if((user.getDepartment().getDepartmentId().equals(sesUser.getDepartment().getDepartmentId())&&
					!user.getRole().getRoleName().equals("Staff"))||(user.getRole().getRoleName().equals("HR"))){
				list.add(user);
			}
		}

		OvertimeFormDTO overtimeFormDTO = overtimeFormService.selectOTFormById(id);
		OvertimeFormBean overtimeFormBean = new OvertimeFormBean();
		overtimeFormBean.setOvertimeForm(overtimeFormDTO.getOvertimeFormId());

		List<OvertimeTypeDTO> overtimeList = overtimeTypeService.selectAll();
		

		model.addAttribute("otTypeList", overtimeList);
		model.addAttribute("userList", list);
		model.addAttribute("otForm", overtimeFormBean);
		return "WFMS-AOT-003";
	}

	@RequestMapping(value = "/sendBackAddLeaveForm", method = RequestMethod.POST)
	public String sendBackAddLeaveForm(@ModelAttribute("leaveForm") @Validated LeaveFormBean bean, BindingResult bs,
			ModelMap model, HttpSession session) {

		System.out.println(bean.toString());

		if (bs.hasErrors()) {
			model.addAttribute("err", "Fields must not be null");
			model.addAttribute("leaveForm", bean);
			return "WFMS-ALV-003";
		}

		LeaveFormDTO leaveFormDto = new LeaveFormDTO();
		LeaveTypeDTO leaveTypeDto = leaveTypeService.selectOne(bean.getLeaveType());

		leaveFormDto.setLeaveFormId(bean.getLeaveForm());
		leaveFormDto.setLeaveType(leaveTypeDto);

		UserDTO userDTO = (UserDTO) session.getAttribute("sesUser");
		UserDTO applicant = userService.selectOne(userDTO.getUserId());
		leaveFormDto.setApplicant(applicant);
		leaveFormDto.setFullOrHalf(bean.getFullOrHalf());

		String dateRange = bean.getDateRange();
		String startDate = dateRange.substring(0, 10);
		String endDate = dateRange.substring(13);

		leaveFormDto.setStartDate(startDate);
		leaveFormDto.setEndDate(endDate);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		LocalDate date1 = LocalDate.parse(startDate, dtf);
		LocalDate date2 = LocalDate.parse(endDate, dtf);
		// long daysBetween = Duration.between(date1, date2).toDays();

		double duration = ChronoUnit.DAYS.between(date1, date2);
		leaveFormDto.setDateRange(duration + 1);

		UserDTO usrDto = userService.selectOne(bean.getAssignTo());

		leaveFormDto.setAssignTo(usrDto);
		leaveFormDto.setReason(bean.getReason());
		long millis = System.currentTimeMillis();
		leaveFormDto.setCreatedDate(new Date(millis));

		LeaveStatusDTO stsDto = new LeaveStatusDTO();
		UserDTO usrStatus = userService.selectOne(bean.getSubmitTo());
		stsDto.setApprover(usrStatus);
		stsDto.setLeaveForm(leaveFormDto);

		stsDto.setStatus("Pending");

		int rs = leaveFormService.insert(leaveFormDto);

		if (rs == 0) {
			model.addAttribute("err", "Add Leave form is failed!");
			return "WFMS-ALV-003";
		} else {

			model.addAttribute("msg", "Leave Form is added successfully!");
			statusService.insertLeave(stsDto);
			return "WFMS-ALV-003";

		}

	}

	@RequestMapping(value = "/sendBackAddOTForm", method = RequestMethod.POST)
	public String sendBackAddOTForm(@ModelAttribute("otForm") @Validated OvertimeFormBean bean, BindingResult bs,
			ModelMap model, HttpSession session) {
		if (bs.hasErrors()) {
			model.addAttribute("err", "Fields must not be null");
			model.addAttribute("otForm", bean);
			return "WFMS-AOT-003";
		}

		OvertimeFormDTO otFormDto = new OvertimeFormDTO();

		OvertimeTypeDTO otTypeDto = overtimeTypeService.selectOne(bean.getOvertimeType());

		otFormDto.setOvertimeFormId(bean.getOvertimeForm());

		otFormDto.setOvertimeType(otTypeDto);

		UserDTO userDTO = (UserDTO) session.getAttribute("sesUser");
		UserDTO applicant = userService.selectOne(userDTO.getUserId());
		otFormDto.setApplicant(applicant);

		String timeRange = bean.getTimeRange();
		String startTime = timeRange.substring(0, 16);
		String endTime = timeRange.substring(19);
		otFormDto.setStartTime(startTime);
		otFormDto.setEndTime(endTime);

		try {

			java.util.Date date1 = new SimpleDateFormat("MM/dd/yyyy hh:mm").parse(endTime);
			java.util.Date date2 = new SimpleDateFormat("MM/dd/yyyy hh:mm").parse(startTime);
			long diffMs = date1.getTime() - date2.getTime();
			long diffSec = diffMs / 1000;
			long min = diffSec / 60;

			long hours = min / 60;
			long minutes = min % 60;
			String duration = hours + " hr " + minutes + " min";
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

		int rs = overtimeFormService.insert(otFormDto);
		if (rs == 0) {
			model.addAttribute("err", "Add Overtime form is failed!");
			return "WFMS-AOT-003";
		} else {

			model.addAttribute("msg", "Overtime Form is added successfully!");
			statusService.insertOvertime(otStatusDto);
			return "WFMS-AOT-003";

		}
	}
}
