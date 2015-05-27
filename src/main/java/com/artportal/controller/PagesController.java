package com.artportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.artportal.domain.User;
import com.artportal.exception.CustomException;

@Controller
public class PagesController {
	
	@Autowired
	User user;
	
//----------- exceptions testing -------------
	
	 @RequestMapping(value = "/error", method = RequestMethod.GET)
	    public void testCustomException(Model model) throws Exception{
		 throw new CustomException("4XX", "Something goes wrong...");
	    }
	 
	 @RequestMapping(value = "/exc", method = RequestMethod.GET)
	 public void testException(Model model) throws Exception{
		 throw new Exception() ;
	 }
	 
//---------- managing pages -----------------------
	 
	 @RequestMapping(value = "/notReady", method = RequestMethod.GET)
	 public String notReady(Model model) {
		 return "notReady";
	 }
	 
	 @RequestMapping(value = "/about", method = RequestMethod.GET)
	    public String about(Model model) {
	        return "about";
	    }
	 
	 @RequestMapping(value = "/contacts", method = RequestMethod.GET)
	 public String contacts(Model model) {
		 return "contacts";
	 }
	 
	 @RequestMapping(value = {"/test", "/account/test"}, method = RequestMethod.GET)
		public String cancelButtonGoHome(Model model) {
			return "home";
		}
	 @RequestMapping(value = {"/test", "/account/test"}, method = RequestMethod.POST)
	 public String cancelButtonRegister(Model model) {
		 return "home";
	 }
	 
//-------- register page --------------------
	 
	 @RequestMapping(value = {"/registerForm","/workInfo/registerForm","/userInfo/registerForm"}, method = RequestMethod.GET) 
	 public String registerForm(Model model) {
		 return "redirect:/register";
	 }
	 //loads modelAttribute object for register form
	 @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	 public String register(Model model) {
		// model.addAttribute("user", new User());
		 model.addAttribute("user", user);
		 return "register";
	 }
	 
//-------- login page -----------------------
	 
	 @RequestMapping(value = {"/loginForm","/workInfo/loginForm","/userInfo/loginForm"}, method = RequestMethod.GET)
	 public String loginForm(Model model) {
		 return "redirect:/login";
	 }
	 
	 @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	 public String login(Model model) {
		 return "login";
	 }
	
// -----------  account page -----------	 
	 
	 @RequestMapping(value = {"/account","/account/account","/account/view","/workInfo/account","/userInfo/account"}, method = RequestMethod.GET)
	 public String accountForm(Model model, HttpSession session) {
		 return "redirect:/account/"+session.getAttribute("login");
	 }
	 
	 @RequestMapping(value = "/account/loadAvatar", method = RequestMethod.GET)
	 public String editAvatarForm(Model model, HttpSession session) {
		 model.addAttribute("user", session.getAttribute("user"));
		 return "accountavatar";
	 }
	 @RequestMapping(value = "/account/editData", method = RequestMethod.GET)
	 public String editDataForm(Model model, HttpSession session) {
		 model.addAttribute("user", session.getAttribute("user"));
		 return "accountedit";
	 }
	 @RequestMapping(value = "/account/accountDelete", method = RequestMethod.GET)
	 public String makeAccountNotActive(Model model, HttpSession session) {
		 model.addAttribute("accountEditMsg", "Are you sure? All your data will be deleted!");
		 return "accountdelete";
	 }
	  
	 @RequestMapping(value = "/account/changePsw", method = RequestMethod.GET)
	 public String changePswForm(Model model) {
		// model.addAttribute("user", new User());
		 model.addAttribute("user", user);
		 return "accountpsw";
	 }
	 @RequestMapping(value = "/account/userWorks", method = RequestMethod.GET)
	 public String userWorksForm(Model model, HttpSession session) {
		 model.addAttribute("user", session.getAttribute("user"));
		 return "accountworks";
	 }
		 	 
	 @RequestMapping(value = {"/cancelEdit","/account/cancelEdit"},method = RequestMethod.POST)
	 public String cancelEditAccount(Model model,HttpServletRequest request,HttpSession session) {
		 session.setAttribute("accountEditMsg", null);
		 session.setAttribute("workName", "");
		 return "redirect:/account/"+session.getAttribute("login");
	 }
}
