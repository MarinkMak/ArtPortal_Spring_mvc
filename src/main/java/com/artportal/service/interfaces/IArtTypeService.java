package com.artportal.service.interfaces;

import java.util.List;

import com.artportal.domain.ArtType;

public interface IArtTypeService {
	public void saveArtType(ArtType type);
	public ArtType findArtTypeById(Long id);
	public void updateArtType(ArtType type);
	public void deleteArtType(Long id);
	public List<ArtType> getAllArtTypes();
	public ArtType getArtTypeByName(String name);
}
