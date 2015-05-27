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

import com.artportal.domain.ArtWork;
import com.artportal.domain.User;
import com.artportal.repository.interfaces.UserRepository;


@Repository("userRepository")
public class JpaUserRepository implements UserRepository{
											//do not use EAGER - save extended context
	@PersistenceContext(name="artPortalUnit", type=PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Transactional
	@Override
	public void create(User user) {
		if (user.getActive()==null){
			user.setActive(true);
		}
		if (user.getCommentAble()==null){
			user.setCommentAble(true);
		}
		em.merge(user.getRole());
		em.persist(user);
	}

	@Override
	public User read(Long id) {
		return em.find(User.class, id);
	}

	@Transactional
	@Override
	public void update(User user) {
		em.merge(user);
	}

	@Transactional
	@Override
	public void delete(User user) {
		em.remove(user);
	}

	@Override
	public User findUserByLogin(String login) {
		try{
			TypedQuery<User> query = em.createNamedQuery("User.findByLogin",User.class);
			return query.setParameter("login",login).getSingleResult();
		 } catch(NoResultException e) {
			return null;
		 }
	}

	@Override
	public List<User> findAllUsers() {
		TypedQuery<User> query = em.createNamedQuery("User.findAll",User.class);
		return query.getResultList();
	}

	@Override
	public List<User> findUsersByPage(int pageNumber, int pageSize) {
		TypedQuery<User> query = em.createNamedQuery("User.findAll",User.class);
		query.setFirstResult((pageNumber-1) * pageSize); 
		query.setMaxResults(pageSize);		
		return query.getResultList();
	}

	@Override
	public Long getCountOfAllUsers() {
		try{
			TypedQuery<Long> query =  em.createNamedQuery("User.getCountOfAllUsers",Long.class);
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}		
	}

	@Override
	public List<User> findUsersByLoginPart(String userLogin) {
		TypedQuery<User> query = em.createNamedQuery("User.findUsersByLoginPart",User.class);
		return query.setParameter("loginPart",userLogin+"%").getResultList();
	}

}
