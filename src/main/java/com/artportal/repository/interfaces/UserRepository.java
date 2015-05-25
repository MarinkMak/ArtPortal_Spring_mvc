package com.artportal.repository.interfaces;

import java.util.List;

import com.artportal.domain.User;

public interface UserRepository{
	public void create(User user);
	public User read(Long id);
	public void update(User user);
	public void delete(User user);
	public User findUserByLogin(String login); 
	public List<User> findAllUsers();
	public List<User> findUsersByPage(int pageNumber, int pageSize);
	public Long getCountOfAllUsers();
	public List<User> findUsersByLoginPart(String userLogin);
	
}
