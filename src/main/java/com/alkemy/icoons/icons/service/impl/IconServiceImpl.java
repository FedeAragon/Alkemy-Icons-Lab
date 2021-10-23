package com.alkemy.icoons.icons.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alkemy.icoons.icons.dto.IconSimpleDTO;
import com.alkemy.icoons.icons.entity.IconEntity;
import com.alkemy.icoons.icons.mapper.IconMapper;
import com.alkemy.icoons.icons.repository.IconRepository;
import com.alkemy.icoons.icons.service.IconService;

@Service
public class IconServiceImpl implements IconService{

	private IconRepository iconRepository;
	private IconMapper iconMapper;
	
	public IconServiceImpl(IconRepository iconRepository, IconMapper iconMapper) {
		this.iconRepository = iconRepository;
		this.iconMapper = iconMapper;
	}
	
	public List<IconSimpleDTO> getAll() {
		List<IconEntity> iconEntities = this.iconRepository.findAll();
		List<IconSimpleDTO> simpleDTOs = this.iconMapper.iconEntityToIconSimpleDTO(iconEntities);
		return simpleDTOs;
	}

}
