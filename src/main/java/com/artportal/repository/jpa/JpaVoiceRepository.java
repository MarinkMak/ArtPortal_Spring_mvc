package com.artportal.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.artportal.domain.ArtWork;
import com.artportal.domain.User;
import com.artportal.domain.Voice;
import com.artportal.repository.interfaces.VoiceRepository;

@Repository("voiceRepository")
public class JpaVoiceRepository implements VoiceRepository{
	
	@PersistenceContext(name="artPortalUnit", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Transactional
	@Override
	public void create(Voice voice) {
		em.persist(voice);
	}

	@Override
	public Voice read(Long id) {
		return em.find(Voice.class, id);
	}

	@Transactional
	@Override
	public void update(Voice voice) {
		em.merge(voice);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(id);
	}

	@Override
	public List<Voice> findAll() {
		TypedQuery<Voice> query = em.createNamedQuery("Voice.findAll",Voice.class);
		return query.getResultList();
	}

	@Override
	public List<Voice> findByWork(ArtWork work) {
		TypedQuery<Voice> query = em.createNamedQuery("Voice.findByWork",Voice.class);
		return query.setParameter("work", work).getResultList();
	}

	@Override
	public List<Voice> findByUser(User user) {
		TypedQuery<Voice> query = em.createNamedQuery("Voice.findByUser",Voice.class);
		return query.setParameter("user", user).getResultList();
	}

}
