package com.artportal.service.interfaces;

import java.util.List;

import com.artportal.domain.Competition;

public interface ICompetitionService {
	public void saveCompetition(Competition competition);
	public Competition getCompetitionById(Long id);
	public void updateCompetition(Competition competition);
	public void deleteCompetition(Long id);
	public List<Competition> getAllCompetitions();
	public Competition getCompetitionByName(String name);
}
