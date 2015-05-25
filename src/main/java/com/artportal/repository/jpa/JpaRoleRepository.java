package com.artportal.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.artportal.domain.Role;
import com.artportal.repository.interfaces.RoleRepository;

@Repository("roleRepository")
public class JpaRoleRepository implements RoleRepository{
	
	@PersistenceContext(name="artPortalUnit", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Transactional
	@Override
	public void create(Role role) {
		em.persist(role);
	}

	@Override
	public Role read(Long id) {
		return em.find(Role.class, id);
	}

	@Transactional
	@Override
	public void update(Role role) {
		em.merge(role);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(id);
	}

	@Override
	public List<Role> findAll() {
		TypedQuery<Role> query = em.createNamedQuery("findAll",Role.class);
		return query.getResultList();
	}

	@Override
	public List<String> findRoleNames() {
		TypedQuery<String> query = em.createNamedQuery("findRoleNames", String.class);
		return query.getResultList();
	}

	@Override
	public Role findByName(String name) {
		try{
			TypedQuery<Role> query = em.createNamedQuery("findByName",Role.class);
			return query.setParameter("name",name).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

}
