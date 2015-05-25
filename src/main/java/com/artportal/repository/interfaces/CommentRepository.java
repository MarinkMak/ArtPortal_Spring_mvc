package com.artportal.repository.interfaces;

import java.util.List;

import com.artportal.domain.ArtWork;
import com.artportal.domain.Comment;
import com.artportal.domain.User;

public interface CommentRepository {
	public void create(Comment comment);
	public Comment read(Long id);
	public void update(Comment comment);
	public void delete(Long id);
	public List<Comment> findAll();
	public List<Comment> findByWork(ArtWork work);
	public List<Comment> findByUser(User user);
}
