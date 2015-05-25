package com.artportal.repository.interfaces;

import java.util.List;

import com.artportal.domain.Role;

public interface RoleRepository {
	public void create(Role role);
	public Role read(Long id);
	public void update(Role role);
	public void delete(Long id);
	public List<Role> findAll();
	public Role findByName(String name);
	public List<String> findRoleNames();
	
}
