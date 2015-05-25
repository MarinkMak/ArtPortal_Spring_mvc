package com.artportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artportal.domain.ArtType;
import com.artportal.repository.interfaces.ArtTypeRepository;
import com.artportal.service.interfaces.IArtTypeService;

@Service("artTypeService")
public class ArtTypeService implements IArtTypeService {
	
	@Autowired
	ArtTypeRepository artTypeRepository;
	
	@Override
	public void saveArtType(ArtType type) {
		artTypeRepository.create(type);

	}

	@Override
	public ArtType findArtTypeById(Long id) {
		return artTypeRepository.read(id);
	}

	@Override
	public void updateArtType(ArtType type) {
		artTypeRepository.update(type);

	}

	@Override
	public void deleteArtType(Long id) {
		artTypeRepository.delete(id);

	}

	@Override
	public List<ArtType> getAllArtTypes() {
		return artTypeRepository.findAll();
	}

	@Override
	public ArtType getArtTypeByName(String name) {
		return artTypeRepository.findByName(name);
	}

}
