package com.artportal.repository.interfaces;

import java.util.List;

import com.artportal.domain.ArtType;

public interface ArtTypeRepository {
	public void create(ArtType type);
	public ArtType read(Long id);
	public void update(ArtType type);
	public void delete(Long id);
	public List<ArtType> findAll();
	public ArtType findByName(String name);
}
