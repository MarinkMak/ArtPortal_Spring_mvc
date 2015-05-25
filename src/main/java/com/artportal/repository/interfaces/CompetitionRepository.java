package com.artportal.repository.interfaces;

import java.util.List;

import com.artportal.domain.Competition;

public interface CompetitionRepository {
	public void create(Competition competition);
	public Competition read(Long id);
	public void update(Competition competition);
	public void delete(Long id);
	public List<Competition> findAll();
	public Competition findByName(String name);
}
