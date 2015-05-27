package com.artportal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.artportal.domain.User;
import com.artportal.service.interfaces.IUserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	//loads modelAttribute object for account view
	@RequestMapping(value="account/{userlogin}", method=RequestMethod.GET)
	public String showUserAccount(@PathVariable String userlogin, HttpSession session, Model model) {
		model.addAttribute("user", session.getAttribute("user"));
		return "account";
	}
	
	//loads modelAttribute object for user information view
	@RequestMapping(value="userInfo/{userlogin}", method=RequestMethod.GET)
	public String showUserInfo(@PathVariable String userlogin, HttpSession session, Model model) {
		model.addAttribute("userInfo", userService.findUserByLogin(userlogin));
		return "userInfo";
	}

//---------- edit user data ---------------	
	
	@RequestMapping(value="account/submitAccountEdit",method = RequestMethod.POST)
	public String accountEdit(Model model,HttpServletRequest request, HttpSession session,
			@Valid @ModelAttribute User user, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()){
			User updatedUser = (User) session.getAttribute("user");
			updatedUser.setName(user.getName());
			updatedUser.setSurname(user.getSurname());
			updatedUser.setEmail(user.getEmail());
			session.setAttribute("user", updatedUser);
			//update user in database and model
			userService.updateUser(updatedUser);
			model.addAttribute("user", updatedUser);
			model.addAttribute("accountDataEditMsg",true);
			return "account";
		}
		return "accountedit";
	}
	
	@RequestMapping(value = {"/account/submitAccountNotActive"}, method = RequestMethod.POST)
	public String accountDelete(Model model, HttpServletRequest request,HttpSession session){
		userService.makeUserNotActive(userService.findUserByLogin((String) session.getAttribute("login")));
		session.invalidate();
		return "redirect:/test";
	}
		
	@RequestMapping(value="account/submitPswEdit",method = RequestMethod.POST)
	public String passwordEdit(Model model,HttpServletRequest request, HttpSession session,
			@Valid @ModelAttribute User user, BindingResult bindingResult) {
		boolean isPswConfirmed = userService.checkPasswordConfirm(user.getPassword(),user.getConfirmPassword(),model);
		if (!bindingResult.hasErrors()&&isPswConfirmed){
			User updatedUser = (User) session.getAttribute("user");
			updatedUser.setPassword(user.getPassword());
			session.setAttribute("user", updatedUser);
			//update user in database and model
			userService.updateUser(updatedUser);
			model.addAttribute("user", updatedUser);
			model.addAttribute("accountPswEditMsg", true);
			return "account";
		}
		return "accountpsw";
	}
	
	@RequestMapping(value="account/submitChangeAvatar",method = RequestMethod.POST)
	public String avatarEdit(Model model,HttpServletRequest request, HttpSession session,
			@RequestParam(value="image", required=false) MultipartFile image) {
			
			if(!image.isEmpty()&&image.getContentType().equals("image/jpeg")) {
				User updatedUser = (User) session.getAttribute("user");
				String workName = session.getAttribute("login") + "new.jpg";
				updatedUser.setPhotoPath(workName);
				//update user in database and save image in file system
				userService.updateUserAvatar(updatedUser, workName, image);
				//update user in model
				session.setAttribute("user", updatedUser);
				model.addAttribute("user", updatedUser);
				model.addAttribute("accountAvatarLoadedMsg", true);
				return "account";
			}
			
			if(!image.getContentType().equals("image/jpeg")||image.isEmpty()){
				model.addAttribute("loadJpgMsg", true);
			}
			return "accountavatar";
	}
	

//----------- register----------	
	
	@RequestMapping(value="/submitregister",method = RequestMethod.POST)
	public String register(Model model,HttpServletRequest request, HttpSession session,
			@Valid @ModelAttribute User user, BindingResult bindingResult) {
		boolean isLoginUnique = userService.checkLoginUnique(user.getLogin(),model);
		boolean isLoginNotEmpty = userService.checkLoginNotEmpty(user.getLogin(),model);
		boolean isPswConfirmed = userService.checkPasswordConfirm(user.getPassword(),user.getConfirmPassword(),model);
		if (!bindingResult.hasErrors()&&isLoginUnique&&isLoginNotEmpty&&isPswConfirmed){
			session.invalidate();
			session = request.getSession(); 
			session.setAttribute("login",user.getLogin());
			session.setAttribute("user", user);
			//save new user in database
			userService.saveUser(user);
			//redirect on account page for edit
			return "redirect:/account/"+user.getLogin();
		}
		return "register";
	}
	
//--------- login ---------------
	
	@RequestMapping(value = {"/workInfo/login","/login"}, method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request,
			HttpSession session, @RequestParam("login") String login,
			@RequestParam("password") String password) {
		if (userService.checkUserLoginData(login, password, model)){
			session.invalidate();
			session = request.getSession(); 
			//fixed trouble with first letter in login
			session.setAttribute("login", userService.findUserByLogin(login).getLogin());
			session.setAttribute("user", userService.findUserByLogin(login));
			return "home";
		}
		return "login";
	}
	
//----------- logout -------------	
	
	@RequestMapping(value = {"/workInfo/logout","/userInfo/logout","/logout","account/logout"}, method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request,HttpSession session){
		session.invalidate();
		return "redirect:/test";
	}
			

}
