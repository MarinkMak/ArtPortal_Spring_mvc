package com.artportal.service.interfaces;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.artportal.domain.User;

public interface IUserService {
	User findUserById(Long id);
	User findUserByLogin(String login);
	List<User> getAllUsers();
	void saveUser(User user);
	void updateUser(User user);
	void deleteUser(User user);
	void makeUserNotActive(User user);
	void updateUserAvatar(User user, String filename, MultipartFile image);
	List<User> getUsersOnPage(int pageNumber, int pageSize);
	Long getPagesCount(int pageSize);
	List<User> findUsersByLoginPart(String userLogin);
	boolean checkUserLoginData(String login, String password, Model model);
	boolean checkLoginUnique(String login, Model model);
	boolean checkLoginNotEmpty(String login, Model model);
	boolean checkPasswordConfirm(String password, String confirmPassword,Model model);
	
}
