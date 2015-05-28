package com.artportal.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.artportal.domain.ArtType;
import com.artportal.domain.ArtWork;
import com.artportal.domain.Competition;
import com.artportal.domain.User;
import com.artportal.repository.interfaces.ArtWorkRepository;

@Repository("artWorkRepository")
public class JpaArtWorkRepository implements ArtWorkRepository{
	
	@PersistenceContext(name="artPortalUnit", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Transactional
	@Override
	public void create(ArtWork work) {
		em.persist(work);
	}

	@Override
	public ArtWork read(Long id) {
		return em.find(ArtWork.class, id);
	}

	@Transactional
	@Override
	public void update(ArtWork work) {
		em.merge(work);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(id);
	}

	@Override
	public List<ArtWork> findAll() {
		TypedQuery<ArtWork> query = em.createNamedQuery("ArtWork.findAll",ArtWork.class);
		return query.getResultList();
	}

	@Override
	public List<ArtWork> findByUser(User user) {
		TypedQuery<ArtWork> query = em.createNamedQuery("ArtWork.findByUser",ArtWork.class);
		return query.setParameter("user", user).getResultList();
	}

	@Override
	public List<ArtWork> findByType(ArtType type) {
		TypedQuery<ArtWork> query = em.createNamedQuery("ArtWork.findByType",ArtWork.class);
		return query.setParameter("type", type).getResultList();
	}

	@Override
	public List<ArtWork> findByCompetition(Competition competition) {
		TypedQuery<ArtWork> query = em.createNamedQuery("ArtWork.findByCompetition",ArtWork.class);
		return query.setParameter("competition", competition).getResultList();
	}

	@Override
	public List<ArtWork> findAllApprovedDescDate() {
		TypedQuery<ArtWork> query = em.createNamedQuery("ArtWork.findAllApprovedDescDate",ArtWork.class);
		return query.getResultList();
	}

	@Override
	public ArtWork findByName(String name) {
		try{
			TypedQuery<ArtWork> query = em.createNamedQuery("ArtWork.findByName",ArtWork.class);
			return query.setParameter("name",name).getSingleResult();
		 } catch(NoResultException e) {
			return null;
		 }
	}

	@Override
	public List<ArtWork> findApprovedByPage(int pageNumber, int pageSize) {
		TypedQuery<ArtWork> query = em.createNamedQuery("ArtWork.findAllApprovedDescDate",ArtWork.class);
		query.setFirstResult((pageNumber-1) * pageSize); 
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public Long getCountOfAllApprovedWorks() {
		try{
			TypedQuery<Long> query = em.createNamedQuery("ArtWork.getCountOfApproved", Long.class);
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}	
	}

	@Override
	public Long getCountOfAllWorks() {
		try{
			TypedQuery<Long> query = em.createNamedQuery("ArtWork.getCountAll", Long.class);
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public List<ArtWork> findCompWorksByPage(int pageNumberCW, int pageSize,Competition competition) {
		TypedQuery<ArtWork> query = em.createNamedQuery("ArtWork.findByCompetition",ArtWork.class);
		query.setParameter("competition", competition);
		query.setFirstResult((pageNumberCW-1) * pageSize); 
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public Long getCountOfAllCompWorks(Competition competition) {
		try{
			TypedQuery<Long> query =  em.createNamedQuery("ArtWork.getCountOfCompWorks",Long.class);
			query.setParameter("competition", competition);
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}	
	}

}
