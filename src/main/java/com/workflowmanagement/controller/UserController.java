package com.workflowmanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.workflowmanagement.dao.DepartmentRepository;
import com.workflowmanagement.dao.DepartmentService;
import com.workflowmanagement.dao.LeaveFormService;
import com.workflowmanagement.dao.RoleRepository;
import com.workflowmanagement.dao.RoleService;
import com.workflowmanagement.dao.UserService;
import com.workflowmanagement.dto.DepartmentDTO;
import com.workflowmanagement.dto.LeaveFormDTO;
import com.workflowmanagement.dto.RoleDTO;
import com.workflowmanagement.dto.UserDTO;
import com.workflowmanagement.model.UserBean;

@Controller
public class UserController {

	@Autowired
	UserService service;

	@Autowired
	public DepartmentService departmentService;

	@Autowired
	public RoleRepository roleRepo;

	@Autowired
	public RoleService roleService;

	@Autowired
	public DepartmentRepository departmentRepo;

	@Autowired
	public LeaveFormService leaveService;

	@RequestMapping("/")
	public ModelAndView setupLogin() {
		return new ModelAndView("WFMS-LGN-002", "login", new UserBean());

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("login") UserBean bean, HttpSession session, ModelMap model) {

		UserDTO dto = new UserDTO();
		dto.setUserId(bean.getUserId());
		List<UserDTO> list = service.select(dto);
		if (list.size() == 0) {
			model.addAttribute("err", "User not found!");
			return "WFMS-LGN-002";
		} else if (bean.getPassword().equals(list.get(0).getPassword())) {
			session.setAttribute("sesUser", list.get(0));
			if (list.get(0).getRole().getRoleName().equals("Staff")) {
				return "redirect:/checkStatus";
			} else if (list.get(0).getRole().getRoleName().equals("Admin")) {
				return "redirect:/checkStatus";
			} else if ((list.get(0).getRole().getRoleName().equals("Project Manager"))
					|| (list.get(0).getRole().getRoleName().equals("Head of Department"))) {
				return "redirect:/showLeaveApprovalWaitingForHODAndPM";
			} else {
				return "redirect:/showLeaveApprovalWaiting";
			}

		} else {
			model.addAttribute("err", "Your password is incorrect!");
			return "WFMS-LGN-002";
		}
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(@RequestParam("password") String password,
			@RequestParam("newPassword") String newPassword, @RequestParam("reTypePassword") String reTypePassword,
			HttpSession session, ModelMap model) {

		UserDTO sessionUser = (UserDTO) session.getAttribute("sesUser");
		if (!password.equals(sessionUser.getPassword())) {
			model.addAttribute("err", "Your password is incorrect!");
			return "redirect:/checkStatus";
		} else if (!newPassword.equals(reTypePassword)) {
			model.addAttribute("err", "Your new password and retype password do not match!");
			return "redirect:/checkStatus";
		}
			sessionUser.setPassword(newPassword);
			service.update(sessionUser, sessionUser.getUserId());
			model.addAttribute("msg", "Your password is updated successfully!");
			return "redirect:/checkStatus";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "redirect:/";
	}

	@RequestMapping(value = "/addUserSetup", method = RequestMethod.GET)
	public ModelAndView addUserSetup(ModelMap model, HttpServletRequest request) {

		List<DepartmentDTO> departmentList = departmentService.selectAll();
		model.addAttribute("departmentList", departmentList);

		String userId = service.generateUserId();
		model.addAttribute("userId", userId);

		return new ModelAndView("WFMS-USR-001", "user", new UserBean());

	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") @Validated UserBean userbean, BindingResult result, ModelMap model) {
		
		List<DepartmentDTO> departmentList = departmentService.selectAll();
		model.addAttribute("departmentList", departmentList);

		String userId = service.generateUserId();
		model.addAttribute("userId", userId);

		if (result.hasErrors()) {
			System.out.println("error");
			return "WFMS-USR-001";
		}
		System.out.println("no error");

		UserDTO dto = new UserDTO();
		dto.setUserId(userbean.getUserId());
		dto.setName(userbean.getUserName());
		if (userbean.getPassword().length() < 8 || userbean.getPassword().length() > 16) {
			model.addAttribute("err", "Password must be between 8 and 15 character!");
			return "WFMS-USR-001";
		}
		dto.setPassword(userbean.getPassword());
		if (isValid(userbean.getEmail()) == false) {
			model.addAttribute("err", "Email is invalid!");
			return "WFMS-USR-001";
		}
		dto.setEmail(userbean.getEmail());
		dto.setGender(userbean.getGender());
		dto.setPhoneNo(userbean.getPhoneNumber());
		dto.setAddress(userbean.getAddress());
		dto.setDepartment(departmentRepo.selectOne(userbean.getDepartment()));
		dto.setRole(roleService.selectOne(userbean.getRole()));

		int rs = service.save(dto);
		if (rs == 0) {
			model.addAttribute("err", "Add user is failed!");
			return "WFMS-USR-001";
		} else {
			model.addAttribute("msg", "User is added successfully!");
			return "WFMS-USR-002";

		}

	}

	@RequestMapping(value = "/searchUserSetup", method = RequestMethod.GET)
	public ModelAndView searchUserSetup(ModelMap model) {
		List<RoleDTO> roleList = roleService.selectAll();
		model.addAttribute("roleList", roleList);
		List<DepartmentDTO> departmentList = departmentService.selectAll();
		model.addAttribute("departmentList", departmentList);
		return new ModelAndView("WFMS-USR-002", "user", new UserBean());
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.POST)
	public ModelAndView searchUser(@ModelAttribute("user") UserBean userbean, ModelMap model) {

		UserDTO dto = new UserDTO();
		if (!userbean.getUserId().equals("")) {
			dto.setUserId(userbean.getUserId());
		}
		if (!userbean.getUserName().equals("")) {
			dto.setName(userbean.getUserName());
		}

		dto.setRole(roleService.selectOne(userbean.getRole()));
		dto.setDepartment(departmentService.selectOne(userbean.getDepartment()));

		List<UserDTO> list = service.selectAll(dto);

		List<RoleDTO> roleList = roleService.selectAll();
		model.addAttribute("roleList", roleList);
		List<DepartmentDTO> departmentList = departmentService.selectAll();
		model.addAttribute("departmentList", departmentList);

		return new ModelAndView("WFMS-USR-002", "userList", list);
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteDepartment(@RequestParam("id") String userId, ModelMap model, RedirectAttributes redirAttrs) {

		UserDTO user = service.selectOne(userId);
		List<LeaveFormDTO> list = leaveService.selectAll();
		if(list.size()!=0) {
		for (LeaveFormDTO form : list) {
			if (form.getApplicant().getUserId().equals(user.getUserId())) {
				redirAttrs.addFlashAttribute("err", "User can't be deleted!");
				return "redirect:/searchUserSetup";
			}
		}
		}
		int counter = service.delete(userId);

		if (counter == 0) {
			redirAttrs.addFlashAttribute("err", "Delete failed!");
			return "redirect:/searchUserSetup";
		} else {
			redirAttrs.addFlashAttribute("msg", "User is successfully deleted!");
			return "redirect:/searchUserSetup";
		}

	}

	@RequestMapping(value = "/updateUserSetup", method = RequestMethod.GET)
	public ModelAndView updateSetup(@RequestParam(value = "id", required = true) String userId, ModelMap model) {
		UserDTO user = service.selectOne(userId);
		UserBean userBean = new UserBean();

		userBean.setUserId(user.getUserId());
		userBean.setUserName(user.getName());
		userBean.setPassword(user.getPassword());
		userBean.setEmail(user.getEmail());
		userBean.setAddress(user.getAddress());
		userBean.setPhoneNumber(user.getPhoneNo());
		userBean.setGender(user.getGender());
		userBean.setRole(user.getRole().getRoleId());
		userBean.setDepartment(user.getDepartment().getDepartmentId());

		List<DepartmentDTO> departmentList = departmentService.selectAll();
		model.addAttribute("departmentList", departmentList);
		return new ModelAndView("WFMS-USR-003", "user", userBean);

	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") @Validated UserBean userbean, BindingResult result,
			ModelMap model) {
		System.out.println("bean : " + userbean.toString());
		if (result.hasErrors()) {
			return "WFMS-DEP-003";
		}
		UserDTO dto = new UserDTO();
		dto.setUserId(userbean.getUserId());
		dto.setName(userbean.getUserName());
		dto.setPassword(userbean.getPassword());
		dto.setEmail(userbean.getEmail());
		dto.setPhoneNo(userbean.getPhoneNumber());
		dto.setAddress(userbean.getAddress());
		dto.setGender(userbean.getGender());
		dto.setDepartment(departmentRepo.selectOne(userbean.getDepartment()));
		dto.setRole(roleService.selectOne(userbean.getRole()));

		int rs = service.update(dto, userbean.getDepartment());

		if (rs == 0) {
			model.addAttribute("err", "Update failed!");
			return "WFMS-USR-003";
		} else {
			model.addAttribute("msg", "Update Successful!");
			return "WFMS-USR-002";

		}
	}

	public boolean isValid(String email) {
		String pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(pattern);
	}

}
