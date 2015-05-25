package com.artportal.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.artportal.domain.ArtWork;
import com.artportal.domain.Comment;
import com.artportal.domain.User;
import com.artportal.repository.interfaces.CommentRepository;

@Repository("commentRepository")
public class JpaCommentRepository implements CommentRepository{
	
	@PersistenceContext(name="artPortalUnit", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Transactional
	@Override
	public void create(Comment comment) {
		em.persist(comment);
	}

	@Override
	public Comment read(Long id) {
		return em.find(Comment.class, id);
	}

	@Transactional
	@Override
	public void update(Comment comment) {
		em.merge(comment);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		em.remove(id);
	}

	@Override
	public List<Comment> findAll() {
		TypedQuery<Comment> query = em.createNamedQuery("Comment.findAll",Comment.class);
		return query.getResultList();
	}

	@Override
	public List<Comment> findByWork(ArtWork work) {
		TypedQuery<Comment> query = em.createNamedQuery("Comment.findByWork",Comment.class);
		return query.setParameter("work",work).getResultList();
	}

	@Override
	public List<Comment> findByUser(User user) {
		TypedQuery<Comment> query = em.createNamedQuery("Comment.findByUser",Comment.class);
		return query.setParameter("user",user).getResultList();
	}

}
