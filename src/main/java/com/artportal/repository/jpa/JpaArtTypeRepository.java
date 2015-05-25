package com.artportal.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.artportal.domain.ArtType;
import com.artportal.repository.interfaces.ArtTypeRepository;

@Repository("artTypeRepository")
public class JpaArtTypeRepository implements ArtTypeRepository{
	
	@PersistenceContext(name="artPortalUnit", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Transactional
	@Override
	public void create(ArtType type) {
		em.persist(type);
	}

	@Override
	public ArtType read(Long id) {
		return em.find(ArtType.class, id);
	}
	
	@Transactional
	@Override
	public void update(ArtType type) {
		em.merge(type);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(id);
	}

	@Override
	public List<ArtType> findAll() {
		TypedQuery<ArtType> query = em.createNamedQuery("ArtType.findAll",ArtType.class);
		return query.getResultList();
	}

	@Override
	public ArtType findByName(String name) {
		try{
			TypedQuery<ArtType> query = em.createNamedQuery("ArtType.findByName",ArtType.class);
			return query.setParameter("name",name).getSingleResult();
		 } catch(NoResultException e) {
			return null;
		 }
	}

}
