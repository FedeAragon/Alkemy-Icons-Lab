package com.alkemy.icoons.icons.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icoons.icons.entity.PaisEntity;
import com.alkemy.icoons.icons.exception.ParamNotFound;
import com.alkemy.icoons.icons.repository.PaisRepository;
import com.alkemy.icoons.icons.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService {

	@Autowired
	PaisRepository paisRepository;
	
	public PaisEntity getEntityById(Long idPais) {
		PaisEntity entity = this.paisRepository.findById(idPais)
				.orElseThrow(() -> new ParamNotFound("No existe un pais con id " + idPais));
		return entity;
	}
	
	
	
}
