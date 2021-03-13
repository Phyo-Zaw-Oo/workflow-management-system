package com.workflowmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.workflowmanagement.dao.LeaveTypeService;
import com.workflowmanagement.dto.LeaveTypeDTO;
import com.workflowmanagement.model.LeaveTypeBean;

@Controller
public class LeaveTypeController {
	@Autowired
	LeaveTypeService service;
	
	@RequestMapping(value="/setup_add_leavetype",method=RequestMethod.GET)
	public ModelAndView setupAddLeaveType(Model model)
	{
		String leaveTypeId = service.generateLeaveTypeId();
		model.addAttribute("lvTypeId", leaveTypeId);
		
		return new ModelAndView("WFMS-LEV-001","leaveType",new LeaveTypeBean());
	}
	@RequestMapping(value="/add_leavetype",method=RequestMethod.POST)
	public String addLeaveType(@ModelAttribute("leaveType") @Validated LeaveTypeBean leaveType,BindingResult bs,ModelMap model)
	{
		LeaveTypeDTO dto=new LeaveTypeDTO();
		dto.setLeaveTypeId(leaveType.getLeaveTypeId());
		dto.setLeaveType(leaveType.getLeaveType());;
		int rs=service.save(dto);
		if(rs==0)
		{
			model.addAttribute("err", "Add leavetype is failed!");
			return "WFMS-LEV-001";
		}
		else
		{
			model.addAttribute("msg","LeaveType is added successfully!");
			return "WFMS-LEV-002";
		}
	}
	
	@RequestMapping(value = "/leaveType_list_setup", method = RequestMethod.GET)
	public ModelAndView leaveTypeList() {
		return new ModelAndView("WFMS-LEV-002", "leaveType", new LeaveTypeBean());
	}

	@RequestMapping(value = "/leaveType_list", method = RequestMethod.POST)
	public String leaveTypeList(@ModelAttribute("leaveType") LeaveTypeBean leaveType, ModelMap model) {

		LeaveTypeDTO dto=new LeaveTypeDTO();
		dto.setLeaveTypeId(leaveType.getLeaveTypeId());
		dto.setLeaveType(leaveType.getLeaveType());

		List<LeaveTypeDTO> levList = service.select(dto);

		if (levList.size() == 0)
			model.addAttribute("err", "Leave Type Not Found!!");
		else
			model.addAttribute("levList", levList);

		return "WFMS-LEV-002";
	}
	@RequestMapping(value = "/deleteleaveType", method = RequestMethod.GET)
	public String deleteLeaveType(@RequestParam("id") String leaveTypeId, ModelMap model,
			RedirectAttributes redirAttrs) {
		LeaveTypeDTO dto=service.selectOne(leaveTypeId);
		if(dto.getLeaveForm().size()==0) {
		int counter = service.delete(leaveTypeId);

		if (counter == 0) {
			redirAttrs.addFlashAttribute("err", "Delete failed!");
			return "redirect:/leaveType_list_setup";
		} else {
			redirAttrs.addFlashAttribute("msg", "Successfully deleted!");
			return "redirect:/leaveType_list_setup";
		}
		}else {
			redirAttrs.addFlashAttribute("err", "Leave Type can't be deleted!");
			return "redirect:/leaveType_list_setup";
		}
	}

	@RequestMapping(value="/setupUpdateLeaveType",method=RequestMethod.GET)
	public ModelAndView setupUpdateLeaveType(@RequestParam(value="id", required=true) String leaveTypeId)
	{
		LeaveTypeDTO dto=new LeaveTypeDTO();
		dto.setLeaveTypeId(leaveTypeId);
		dto.setLeaveType("");
		List<LeaveTypeDTO> levList=service.select(dto);
		LeaveTypeBean leavetype=new LeaveTypeBean();
		for(LeaveTypeDTO lv:levList)
		{
			leavetype.setLeaveTypeId(lv.getLeaveTypeId());
			leavetype.setLeaveType(lv.getLeaveType());
			
		}
		return new ModelAndView("WFMS-LEV-003","leaveType",leavetype);
	}
	@RequestMapping(value="/updateLeaveType",method=RequestMethod.POST)
	public String updateLeaveType(@ModelAttribute("leaveType") @Validated LeaveTypeBean leaveType,BindingResult bs,ModelMap model)
	{
		LeaveTypeDTO dto=new LeaveTypeDTO();
		dto.setLeaveTypeId(leaveType.getLeaveTypeId());
		dto.setLeaveType(leaveType.getLeaveType());
		int rs=service.update(dto,leaveType.getLeaveTypeId());
		if(rs==0)
		{
			model.addAttribute("err", "Update failed!");
			return "WFMS-LEV-003";
		}
		else
		{
			model.addAttribute("msg", "Successfully updated!");
			return "WFMS-LEV-002";
		}
	}
	
		
	

}
