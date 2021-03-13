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

import com.workflowmanagement.dao.OvertimeTypeService;
import com.workflowmanagement.dto.OvertimeTypeDTO;
import com.workflowmanagement.model.OvertimeTypeBean;

@Controller
public class OvertimeTypeController {

	@Autowired
	OvertimeTypeService service;

	@RequestMapping(value = "/addOvertimeTypeSetUp", method = RequestMethod.GET)
	public ModelAndView addOvertimeTypeSetUp(Model model) {
		
		String otTypeId = service.generateOvertimeTypeId();
		model.addAttribute("otTypeId", otTypeId);
		return new ModelAndView("WFMS-OVT-001","overtimeType",new OvertimeTypeBean());
	}

	@RequestMapping(value = "/addOvertimeType",method=RequestMethod.POST)
	public String addOvertimeType(@ModelAttribute("overtimeType") @Validated OvertimeTypeBean overtimeType,
			 ModelMap model) {

		OvertimeTypeDTO dto=new OvertimeTypeDTO();
		dto.setOvertimeTypeId(overtimeType.getOvertimeTypeId());
		dto.setOvertimeType(overtimeType.getOvertimeType());

		int rs = service.save(dto);

		if (rs == 0) {
			model.addAttribute("err", "Add Overtime type is failed!");
			return "WFMS-OVT-001";
		} else {
			model.addAttribute("msg", "Overtime type is added successfully!");
			return "WFMS-OVT-002";
		}
	}

	@RequestMapping(value = "/overtimeTypeListSetUp", method = RequestMethod.GET)
	public ModelAndView overtimeTypeList() {
		return new ModelAndView("WFMS-OVT-002", "overtimeType", new OvertimeTypeBean());
	}

	@RequestMapping(value = "/overtimeTypeList", method = RequestMethod.POST)
	public String overtimeList(@ModelAttribute("overtimeType") OvertimeTypeBean overtimeType, ModelMap model) {

		OvertimeTypeDTO dto=new OvertimeTypeDTO();
		dto.setOvertimeTypeId(overtimeType.getOvertimeTypeId());
		dto.setOvertimeType(overtimeType.getOvertimeType());

		List<OvertimeTypeDTO> overtimeList = service.select(dto);

		if (overtimeList.size() == 0)
			model.addAttribute("err", "OvertimeType not found!");
		else
			model.addAttribute("overtimeList", overtimeList);

		return "WFMS-OVT-002";
	}

	@RequestMapping(value = "/deleteOvertimeType", method = RequestMethod.GET)
	public String deleteOvertimeType(@RequestParam("id") String overtimeTypeId, ModelMap model,
			RedirectAttributes redirAttrs) {
		OvertimeTypeDTO dto=service.selectOne(overtimeTypeId);
		if(dto.getOvertimeForm().size()==0) {
		int counter = service.delete(overtimeTypeId);

		if (counter == 0) {
			redirAttrs.addFlashAttribute("err", "Delete failed!");
			return "redirect:/overtimeTypeListSetUp";
		} else {
			redirAttrs.addFlashAttribute("msg", "Successfully deleted!");
			return "redirect:/overtimeTypeListSetUp";
		}
		}else {
			redirAttrs.addFlashAttribute("err", "Overtime Type can't be deleted!");
			return "redirect:/overtimeTypeListSetUp";
		}
	}

	@RequestMapping(value = "/updateOvertimeTypeSetUp", method = RequestMethod.GET)
	public ModelAndView UpdateOvertimeTypeSetUp(@RequestParam(value = "id",required=true) String overtimeTypeId) {
		OvertimeTypeDTO dto = new OvertimeTypeDTO();
		dto.setOvertimeTypeId(overtimeTypeId);
		dto.setOvertimeType("");
		List<OvertimeTypeDTO> overtimeList = service.select(dto);
		
		OvertimeTypeBean overtimeType = new OvertimeTypeBean();
		for (OvertimeTypeDTO o : overtimeList) {
			overtimeType.setOvertimeTypeId(o.getOvertimeTypeId());
			overtimeType.setOvertimeType(o.getOvertimeType());

		}

		return new ModelAndView("WFMS-OVT-003", "overtimeType", overtimeType);

	}

	@RequestMapping(value = "/updateOvertimeType", method = RequestMethod.POST)
	public String updateOvertimeType(@ModelAttribute("overtimeType") @Validated OvertimeTypeBean overtimeType, BindingResult result,
			ModelMap model) {
		
		if(result.hasErrors())
		{
			return "WFMS-OVT-003";
		}
		OvertimeTypeDTO dto=new OvertimeTypeDTO();
		dto.setOvertimeTypeId(overtimeType.getOvertimeTypeId());
		dto.setOvertimeType(overtimeType.getOvertimeType());
		
		int rs = service.update(dto,overtimeType.getOvertimeTypeId());

		if (rs == 0) {
			model.addAttribute("err", "Update failed!");
			return "WFMS-OVT-003";
		} else {
			model.addAttribute("msg", "Successfully updated!");
			return "WFMS-OVT-002";

		}
	}

}
