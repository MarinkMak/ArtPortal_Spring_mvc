package com.artportal.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.artportal.domain.ArtWork;
import com.artportal.domain.User;
import com.artportal.repository.interfaces.RoleRepository;
import com.artportal.repository.interfaces.UserRepository;
import com.artportal.service.interfaces.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Override
	public User findUserById(Long id) {
		return userRepository.read(id);
	}

	@Override
	public User findUserByLogin(String login) {
		return userRepository.findUserByLogin(login);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAllUsers();
	}

	@Override
	public void saveUser(User user) {
		user.setRole(roleRepository.read((long) 2));
		userRepository.create(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.update(user);
	}

	@Transactional
	@Override
	public void updateUserAvatar(User user, String filename, MultipartFile image) {
		userRepository.update(user);
		String artPortalAvatarPath = "/home/marina/Marina/ArtPortalImages/Avatars/";
		File file = new File(artPortalAvatarPath + filename);
		try {
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void makeUserNotActive(User user) {
		user.setActive(false);
		userRepository.update(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public List<User> getUsersOnPage(int pageNumber, int pageSize) {
		return userRepository.findUsersByPage(pageNumber, pageSize);
	}

	@Override
	public Long getPagesCount(int pageSize) {
		return userRepository.getCountOfAllUsers() / pageSize + 1;
	}

	@Override
	public List<User> findUsersByLoginPart(String userLogin) {
		return userRepository.findUsersByLoginPart(userLogin);
	}

	
//----- check inputing data on login page -----------
	
	@Override
	public boolean checkUserLoginData(String login, String password, Model model){
		User user = findUserByLogin(login);
		if (user!=null&&user.getPassword().equals(password)){
			if(user.getActive()==false){
				model.addAttribute("failLoginMsg", true);
				return false;
			}
			return true;			
		}
		model.addAttribute("wrongLoginMsg", true);
		return false;
	}
	
	@Override
	public boolean checkLoginUnique(String login, Model model){
		if (findUserByLogin(login)==null){
			return true;
		}else{
			model.addAttribute("checkLoginUniqueMsg", true);
		}
		return false;
	}
	
//----- check inputing data on register page -----------
	
	@Override
	public boolean checkLoginNotEmpty(String login, Model model){
		if (!Pattern.matches("[\\s]+",login)){
			return true;
		}else{
			model.addAttribute("emptyLoginMsg", true);
		}
		return false;
	}
	
	@Override
	public boolean checkPasswordConfirm(String password, String confirmPassword,Model model){
		if(password==null){
			model.addAttribute("emptyPswMsg", true);
			return false;
		}
		if(confirmPassword!=null&&password.equals(confirmPassword)){
			return true;
		}
		model.addAttribute("confirmPswMsg", true);
		return false;
	}  
}
