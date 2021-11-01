package com.alkemy.icoons.icons.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.icoons.icons.dto.ContinentDTO;
import com.alkemy.icoons.icons.entity.ContinenteEntity;

@Component
public class ContinentMapper {
	
	public ContinenteEntity continenteDTO2Entity(ContinentDTO continentDTO) {
		ContinenteEntity continenteEntity = new ContinenteEntity();
		continenteEntity.setImage(continentDTO.getImage());
		continenteEntity.setDenomination(continentDTO.getDenomination());
		return continenteEntity;
	}
	
	public ContinentDTO continentEntity2DTO(ContinenteEntity entity) {
		ContinentDTO dto = new ContinentDTO();
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setDenomination(entity.getDenomination());
		return dto;
	}

	public List<ContinentDTO> continentEntity2ContinentDTOList(List<ContinenteEntity> entities) {
		List<ContinentDTO> dtos = new ArrayList<>();
		for (ContinenteEntity continenteEntity : entities) {
			dtos.add(this.continentEntity2DTO(continenteEntity));
		}
		return dtos;
	}
}
