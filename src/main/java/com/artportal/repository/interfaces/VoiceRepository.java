package com.artportal.repository.interfaces;

import java.util.List;

import com.artportal.domain.ArtWork;
import com.artportal.domain.User;
import com.artportal.domain.Voice;

public interface VoiceRepository {
	public void create(Voice voice);
	public Voice read(Long id);
	public void update(Voice voice);
	public void delete(Long id);
	public List<Voice> findAll();
	public List<Voice> findByWork(ArtWork work);
	public List<Voice> findByUser(User user);
}
