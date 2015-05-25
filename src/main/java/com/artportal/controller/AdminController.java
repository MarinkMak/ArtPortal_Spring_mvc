package com.artportal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.artportal.domain.ArtWork;
import com.artportal.domain.User;
import com.artportal.exception.CustomException;
import com.artportal.service.interfaces.IUserService;

@Controller
public class AdminController {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
//	@Autowired
//	@Qualifier("artWorkService")
//	private IArtWorkService artWorkService;

	private int pageSize = 6;
	private int pageNumber = 1;
	private Long pageCount;
	
	@RequestMapping(value = {"/findUser"}, method = RequestMethod.POST)
	public String findUser(ModelMap model, HttpSession session,
			@RequestParam("userLogin") String userLogin) {
		User user = (User) session.getAttribute("user");
		if(user==null||!user.getRole().getName().equals("admin")){
			throw new CustomException("401", "Access denied!");
		}
		List<User> users = userService.findUsersByLoginPart(userLogin);
		model.addAttribute("users",users);
		session.setAttribute("loginPart", userLogin);
		return "adminusersfind";
	}
	
	@RequestMapping(value = "/adminUsersFind", method = RequestMethod.GET)
	public String adminUsersFind(ModelMap model,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user==null||!user.getRole().getName().equals("admin")){
			throw new CustomException("401", "Access denied!");
		}
		return "adminusersfind";
	}
	
	@RequestMapping(value = "/adminUsers", method = RequestMethod.GET)
	public String adminUsers(ModelMap model,HttpSession session) {
		User adminuser = (User) session.getAttribute("user");
		if(adminuser==null||!adminuser.getRole().getName().equals("admin")){
			throw new CustomException("401", "Access denied!");
		}
		User user = (User) session.getAttribute("user");
		if(user==null||!user.getRole().getName().equals("admin")){
			throw new CustomException("401", "Access denied!");
		}
		List<User> users = userService.getUsersOnPage(pageNumber, pageSize);
		model.addAttribute("users",users);
		pageCount = userService.getPagesCount(pageSize);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNumber", pageNumber);
		return "adminusers";
	}
	
	@RequestMapping(value = {"/editUserCommentAble"}, method = RequestMethod.POST)
	public String editUserCommentAble(ModelMap model,HttpSession session, @RequestParam("userId") Long userId) {
		User adminuser = (User) session.getAttribute("user");
		if(adminuser==null||!adminuser.getRole().getName().equals("admin")){
			throw new CustomException("401", "Access denied!");
		}
		User user = userService.findUserById(userId);
		user.setCommentAble(!user.getCommentAble());
		userService.updateUser(user);
		List<User> users = userService.getUsersOnPage(pageNumber, pageSize);
		model.addAttribute("users",users);
		pageCount = userService.getPagesCount(pageSize);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNumber", pageNumber);
		return "redirect:/adminUsers";
	}
	
	@RequestMapping(value = {"/editUserActive"}, method = RequestMethod.POST)
	public String editUserActive(ModelMap model,HttpSession session, @RequestParam("userId") Long userId) {
		User adminuser = (User) session.getAttribute("user");
		if(adminuser==null||!adminuser.getRole().getName().equals("admin")){
			throw new CustomException("401", "Access denied!");
		}
		User user = userService.findUserById(userId);
		user.setActive(!user.getActive());
		userService.updateUser(user);
		List<User> users = userService.getAllUsers();
		model.addAttribute("users",users);
		pageCount = userService.getPagesCount(pageSize);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNumber", pageNumber);
		return "redirect:/adminUsers";
	}
	
	@RequestMapping(value = {"/editFoundUserActive"}, method = RequestMethod.POST)
	public String editFoundUserActive(ModelMap model,HttpSession session, @RequestParam("userId") Long userId) {
		User adminuser = (User) session.getAttribute("user");
		if(adminuser==null||!adminuser.getRole().getName().equals("admin")){
			throw new CustomException("401", "Access denied!");
		}
		User user = userService.findUserById(userId);
		user.setActive(!user.getActive());
		userService.updateUser(user);
		List<User> users = userService.findUsersByLoginPart(session.getAttribute("loginPart").toString());
		model.addAttribute("users",users);
		// return "redirect:/adminUsersFind";
		return "adminusersfind";
	}
	
	@RequestMapping(value = {"/editFoundUserCommentAble"}, method = RequestMethod.POST)
	public String editFoundUserCommentAble(ModelMap model,HttpSession session, @RequestParam("userId") Long userId) {
		User adminuser = (User) session.getAttribute("user");
		if(adminuser==null||!adminuser.getRole().getName().equals("admin")){
			throw new CustomException("401", "Access denied!");
		}
		User user = userService.findUserById(userId);
		user.setCommentAble(!user.getCommentAble());
		userService.updateUser(user);
		List<User> users = userService.findUsersByLoginPart(session.getAttribute("loginPart").toString());
		model.addAttribute("users",users);
		return "adminusersfind";
	}
	
	//----- paging -------
	@RequestMapping(value = { "/adminUsers/aunext", "/aunext" }, method = RequestMethod.GET)
	public String usersNextPage(Model model) {
		pageNumber++;
		List<User> users = userService.getAllUsers();
		pageCount = userService.getPagesCount(pageSize);
		model.addAttribute("users",users);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNumber", pageNumber);
		return "redirect:/adminUsers";
	}

	@RequestMapping(value = { "/adminUsers/auprev", "/auprev" }, method = RequestMethod.GET)
	public String usersPrevPage(Model model) {
		pageNumber--;
		List<User> users = userService.getAllUsers();
		pageCount = userService.getPagesCount(pageSize);
		model.addAttribute("users",users);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNumber", pageNumber);
		return "redirect:/adminUsers";
	}

	@RequestMapping(value = { "/adminUsers/aufirst", "/aufirst" }, method = RequestMethod.GET)
	public String usersFirstPage(Model model) {
		pageNumber = 1;
		List<User> users = userService.getAllUsers();
		pageCount = userService.getPagesCount(pageSize);
		model.addAttribute("users",users);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNumber", pageNumber);
		return "redirect:/adminUsers";
	}

	@RequestMapping(value = { "/adminUsers/aulast", "/aulast" }, method = RequestMethod.GET)
	public String usersLastPage(Model model) {
		List<User> users = userService.getAllUsers();
		pageCount = userService.getPagesCount(pageSize);
		pageNumber = pageCount.intValue();
		model.addAttribute("users",users);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageNumber", pageNumber);
		return "redirect:/adminUsers";
	}

	
}
