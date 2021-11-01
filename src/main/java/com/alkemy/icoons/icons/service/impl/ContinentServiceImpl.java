package com.alkemy.icoons.icons.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icoons.icons.dto.ContinentDTO;
import com.alkemy.icoons.icons.entity.ContinenteEntity;
import com.alkemy.icoons.icons.mapper.ContinentMapper;
import com.alkemy.icoons.icons.repository.ContinentRepository;
import com.alkemy.icoons.icons.service.ContinentService;

@Service
public class ContinentServiceImpl implements ContinentService {
	
	private ContinentMapper continentMapper;
	private ContinentRepository continentRepository;
	
	@Autowired
	public ContinentServiceImpl(ContinentMapper continentMapper, ContinentRepository continentRepository) {
		this.continentMapper = continentMapper;
		this.continentRepository = continentRepository;
	}
	
	public ContinentDTO save(ContinentDTO continentDTO) {
		ContinenteEntity entity = this.continentMapper.continenteDTO2Entity(continentDTO);
		ContinenteEntity entitySaved = continentRepository.save(entity);
		ContinentDTO result = continentMapper.continentEntity2DTO(entitySaved);
		return result;
	}

	public List<ContinentDTO> getAllContinents() {
		List<ContinenteEntity> entities = continentRepository.findAll();
		List<ContinentDTO> continentDTOs = this.continentMapper.continentEntity2ContinentDTOList(entities);
		return continentDTOs;
	}

}
