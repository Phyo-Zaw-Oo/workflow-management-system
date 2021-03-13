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

import com.workflowmanagement.dao.DepartmentService;
import com.workflowmanagement.dto.DepartmentDTO;
import com.workflowmanagement.model.DepartmentBean;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService service;

	@RequestMapping(value = "/addDeptSetUp", method = RequestMethod.GET)
	public ModelAndView addDeprtmentSetUp(Model model) {
		String depId = service.generateDepId();
		model.addAttribute("depId", depId);
		return new ModelAndView("WFMS-DEP-001", "department", new DepartmentBean());
	}

	@RequestMapping(value = "/addDept", method = RequestMethod.POST)
	public String addDepartment(@ModelAttribute("department") @Validated DepartmentBean department,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "WFMS-DEP-001";
		}

		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentId(department.getDepartmentId());
		dto.setDepartmentName(department.getDepartmentName());

		int rs = service.save(dto);

		if (rs == 0) {
			model.addAttribute("err", "Add department is failed!");
			return "WFMS-DEP-001";
		} else {

			model.addAttribute("msg", "Department is added successfully!");
			return "WFMS-DEP-002";

		}
	}

	@RequestMapping(value = "/deptListSetUp", method = RequestMethod.GET)
	public ModelAndView usermanagement() {
		return new ModelAndView("WFMS-DEP-002", "department", new DepartmentBean());
	}

	@RequestMapping(value = "/deptList", method = RequestMethod.POST)
	public String departmentList(@ModelAttribute("department") DepartmentBean department, ModelMap model) {

		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentId(department.getDepartmentId());
		dto.setDepartmentName(department.getDepartmentName());
		List<DepartmentDTO> deptList=service.select(dto);
		

		if (deptList.size() == 0)
			model.addAttribute("err", "Department not found");
		else
			model.addAttribute("deptList", deptList);

		return "WFMS-DEP-002";
	}

	@RequestMapping(value = "/deleteDept", method = RequestMethod.GET)
	public String deleteDepartment(@RequestParam("id") String departmentId, ModelMap model,
			RedirectAttributes redirAttrs) {
		DepartmentDTO dto=service.selectOne(departmentId);

		if(dto.getUser().size()==0) {
		int counter = service.delete(departmentId);
		
		if (counter == 0) {
			redirAttrs.addFlashAttribute("err", "Delete fail....");
			return "redirect:/deptListSetUp";
		} else {
			redirAttrs.addFlashAttribute("msg", "Successfully deleted....");
			return "redirect:/deptListSetUp";
		}
		}else {
			redirAttrs.addFlashAttribute("err", "Department can't be deleted....");
			return "redirect:/deptListSetUp";
		}
	}

	@RequestMapping(value = "/updateDeptSetUp", method = RequestMethod.GET)
	public ModelAndView UpdateDepartmentSetUp(@RequestParam(value = "id", required = true) String departmentId) {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentId(departmentId);
		List<DepartmentDTO> deptList = service.select(dto);
		DepartmentBean department = new DepartmentBean();
		for (DepartmentDTO d : deptList) {
			department.setDepartmentId(d.getDepartmentId());
			department.setDepartmentName(d.getDepartmentName());

		}

		return new ModelAndView("WFMS-DEP-003", "department", department);

	}

	@RequestMapping(value = "/updateDept", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("department") @Validated DepartmentBean department, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "WFMS-DEP-003";
		}

		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentId(department.getDepartmentId());
		dto.setDepartmentName(department.getDepartmentName());
		
		int rs = service.update(dto, department.getDepartmentId());

		if (rs == 0) {
			model.addAttribute("err", "Update failed");
			return "WFMS-DEP-003";
		} else {
			model.addAttribute("msg", "update Successful");
			return "WFMS-DEP-002";

		}
	}

}
