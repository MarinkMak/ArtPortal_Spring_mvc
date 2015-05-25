package com.artportal.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.artportal.domain.Present;
import com.artportal.repository.interfaces.PresentRepository;

@Repository("presentRepository")
public class JpaPresentRepository implements PresentRepository{
	
	@PersistenceContext(name="artPortalUnit", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Transactional
	@Override
	public void create(Present present) {
		em.persist(present);
	}

	@Override
	public Present read(Long id) {
		return em.find(Present.class, id);
	}

	@Transactional
	@Override
	public void update(Present present) {
		em.merge(present);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(id);
	}

	@Override
	public List<Present> findAll() {
		TypedQuery<Present> query = em.createNamedQuery("Present.findAll",Present.class);
		return query.getResultList();
	}

}
