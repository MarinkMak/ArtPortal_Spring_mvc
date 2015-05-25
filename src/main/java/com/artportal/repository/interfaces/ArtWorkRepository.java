package com.artportal.repository.interfaces;

import java.util.List;

import com.artportal.domain.ArtType;
import com.artportal.domain.ArtWork;
import com.artportal.domain.Competition;
import com.artportal.domain.User;

public interface ArtWorkRepository {
	public void create(ArtWork work);
	public ArtWork read(Long id);
	public void update(ArtWork work);
	public void delete(Long id);
	public List<ArtWork> findAll();
	public List<ArtWork> findAllApprovedDescDate();
	public List<ArtWork> findByUser(User user);
	public List<ArtWork> findByType(ArtType type);
	public ArtWork findByName(String name);
	public List<ArtWork> findByCompetition(Competition competition);
	public List<ArtWork> findApprovedByPage(int pageNumber, int pageSize);
	public Long getCountOfAllApprovedWorks();
	public Long getCountOfAllWorks();
	public List<ArtWork> findCompWorksByPage(int pageNumberCW, int pageSize,Competition competition);
	public Long getCountOfAllCompWorks(Competition competition);
}
