package com.artportal.repository.interfaces;

import java.util.List;

import com.artportal.domain.Present;

public interface PresentRepository {
	public void create(Present present);
	public Present read(Long id);
	public void update(Present present);
	public void delete(Long id);
	public List<Present> findAll();
}
