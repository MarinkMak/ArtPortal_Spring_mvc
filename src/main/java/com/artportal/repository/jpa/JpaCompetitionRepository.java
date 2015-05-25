package com.artportal.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.artportal.domain.Competition;
import com.artportal.repository.interfaces.CompetitionRepository;

@Repository("competitionRepository")
public class JpaCompetitionRepository implements CompetitionRepository{
	
	@PersistenceContext(name="artPortalUnit", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Transactional
	@Override
	public void create(Competition competition) {
		em.persist(competition);
	}

	@Override
	public Competition read(Long id) {
		return em.find(Competition.class, id);
	}

	@Transactional
	@Override
	public void update(Competition competition) {
		em.merge(competition);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(id);
	}

	@Override
	public List<Competition> findAll() {
		TypedQuery<Competition> query = em.createNamedQuery("Competition.findAll",Competition.class);
		return query.getResultList();
	}

	@Override
	public Competition findByName(String name) {
		try{
			TypedQuery<Competition> query = em.createNamedQuery("Competition.findByName",Competition.class);
			return query.setParameter("name",name).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

}
