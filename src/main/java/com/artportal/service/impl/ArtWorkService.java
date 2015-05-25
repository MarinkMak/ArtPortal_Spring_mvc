package com.artportal.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.artportal.domain.ArtType;
import com.artportal.domain.ArtWork;
import com.artportal.domain.Comment;
import com.artportal.domain.Competition;
import com.artportal.domain.User;
import com.artportal.domain.Voice;
import com.artportal.repository.interfaces.ArtWorkRepository;
import com.artportal.repository.interfaces.CommentRepository;
import com.artportal.repository.interfaces.UserRepository;
import com.artportal.repository.interfaces.VoiceRepository;
import com.artportal.service.interfaces.IArtWorkService;

@Service("artWorkService")
public class ArtWorkService implements IArtWorkService {

	@Autowired
	ArtWorkRepository artWorkRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	VoiceRepository voiceRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void createWork(ArtWork work) {
		artWorkRepository.create(work);

	}

	@Override
	public ArtWork getWorkById(Long id) {
		return artWorkRepository.read(id);
	}

	@Override
	public void updateWork(ArtWork work) {
		artWorkRepository.update(work);

	}

	@Override
	public void deleteWork(Long id) {
		artWorkRepository.delete(id);

	}

	@Override
	public List<ArtWork> getAllWorks() {
		return artWorkRepository.findAll();
	}

	@Override
	public List<ArtWork> getAllApprovedWorksDescDate() {
		return artWorkRepository.findAllApprovedDescDate();
	}

	@Override
	public List<ArtWork> getWorksByUser(User user) {
		return artWorkRepository.findByUser(user);
	}

	@Override
	public List<ArtWork> getWorksByType(ArtType type) {
		return artWorkRepository.findByType(type);
	}

	@Override
	public ArtWork getWorkByName(String name) {
		return artWorkRepository.findByName(name);
	}

	@Override
	public List<ArtWork> getWorksByCompetition(Competition competition) {
		return artWorkRepository.findByCompetition(competition);
	}

	@Transactional
	@Override
	public void saveNewArtWork(ArtWork work, String filename,
			MultipartFile image) {
		artWorkRepository.create(work);
		String artPortalWorkPath = "/home/marina/Marina/ArtPortalImages/Works/";
		File file = new File(artPortalWorkPath + filename);
		try {
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ArtWork> getApprovedWorksOnPage(int pageNumber, int pageSize) {
		return artWorkRepository.findApprovedByPage(pageNumber, pageSize);
	}

	@Override
	public Long getPagesCountForApproved(int pageSize) {
		return artWorkRepository.getCountOfAllApprovedWorks() / pageSize + 1;
	}

	@Override
	public void addComment(Comment comment) {
//		artWorkRepository.update(comment.getWork());
//		userRepository.update(comment.getUser());
		commentRepository.create(comment);
	}

	@Override
	public void addVoice(Voice voice) {
		voiceRepository.create(voice);
		
	}

	@Override
	public List<ArtWork> getCompWorksOnPage(int pageNumberCW, int pageSize,Competition competition) {
		return artWorkRepository.findCompWorksByPage(pageNumberCW, pageSize, competition);
	}

	@Override
	public Long getPagesCountForCompWorks(int pageSize,Competition competition) {
		return artWorkRepository.getCountOfAllCompWorks(competition) / pageSize + 1;
	}

	

}
