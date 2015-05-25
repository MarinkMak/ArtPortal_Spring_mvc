package com.artportal.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.artportal.domain.ArtType;
import com.artportal.domain.ArtWork;
import com.artportal.domain.Comment;
import com.artportal.domain.Competition;
import com.artportal.domain.User;
import com.artportal.domain.Voice;

public interface IArtWorkService {
	void createWork(ArtWork work);
	ArtWork getWorkById(Long id);
	void updateWork(ArtWork work);
	void deleteWork(Long id);
	List<ArtWork> getAllWorks();
	List<ArtWork> getAllApprovedWorksDescDate();
	List<ArtWork> getWorksByUser(User user);
	List<ArtWork> getWorksByType(ArtType type);
	ArtWork getWorkByName(String name);
	List<ArtWork> getWorksByCompetition(Competition competition);
	void saveNewArtWork(ArtWork work, String filename, MultipartFile image);
	List<ArtWork> getApprovedWorksOnPage(int pageNumber, int pageSize);
	Long getPagesCountForApproved(int pageSize);
	void addComment(Comment comment);
	void addVoice(Voice voice);
	List<ArtWork> getCompWorksOnPage(int pageNumberCW, int pageSize, Competition competition);
	Long getPagesCountForCompWorks(int pageSize, Competition competition);
}
