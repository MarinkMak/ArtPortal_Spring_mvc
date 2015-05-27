package com.artportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artportal.domain.Competition;
import com.artportal.repository.interfaces.CompetitionRepository;
import com.artportal.service.interfaces.ICompetitionService;

@Service("competitionService")
public class CompetitionService implements ICompetitionService {
	
	@Autowired
	CompetitionRepository competitionRepository;
	
	@Override
	public void saveCompetition(Competition competition) {
		competitionRepository.create(competition);
	}

	@Override
	public Competition getCompetitionById(Long id) {
		return competitionRepository.read(id);
	}

	@Override
	public void updateCompetition(Competition competition) {
		competitionRepository.update(competition);
	}

	@Override
	public void deleteCompetition(Long id) {
		competitionRepository.delete(id);
	}

	@Override
	public List<Competition> getAllCompetitions() {
		return competitionRepository.findAll();
	}

	@Override
	public Competition getCompetitionByName(String name) {
		return competitionRepository.findByName(name);
	}

}
