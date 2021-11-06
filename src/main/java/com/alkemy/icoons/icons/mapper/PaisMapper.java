package com.alkemy.icoons.icons.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.icoons.icons.dto.IconDTO;
import com.alkemy.icoons.icons.dto.IconSimpleDTO;
import com.alkemy.icoons.icons.dto.PaisDTO;
import com.alkemy.icoons.icons.dto.PaisSimpleDTO;
import com.alkemy.icoons.icons.entity.PaisEntity;

@Component
public class PaisMapper {
	
	@Autowired
    private IconMapper iconMapper;
	
	public List<PaisDTO> paisEntityListToDTOList(List<PaisEntity> entities, boolean loadIcons) {
        List<PaisDTO> dtos = new ArrayList<>();
        for (PaisEntity entity : entities) {
            dtos.add(this.paisEntityToDTO(entity, loadIcons));
        }
        return dtos;
    }

	public PaisDTO paisEntityToDTO(PaisEntity entity, boolean loadIcons) {
		PaisDTO dto = new PaisDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setDenominacion(entity.getDenominacion());
        dto.setCantidadHabitantes(entity.getCantidadHabitantes());
        dto.setContinenteId(entity.getContinenteId());
        dto.setSuperficie(entity.getSuperficie());
        if(loadIcons) {
            List<IconDTO> iconDTOS = this.iconMapper.iconEntitySetToDTOList(entity.getIcons(), false);
            dto.setIcons(iconDTOS);
        }
        return dto;
	}
	
	public PaisEntity paisDTO2Entity(PaisDTO dto) {
		PaisEntity entity = new PaisEntity();
		entity.setImagen(dto.getImagen());
		entity.setDenominacion(dto.getDenominacion());
		entity.setCantidadHabitantes(dto.getCantidadHabitantes());
		entity.setSuperficie(dto.getSuperficie());
		entity.setContinenteId(dto.getContinenteId());
		return entity;
	}
	
	public List<PaisDTO> paisEntitySet2DTOList(Collection<PaisEntity> entities, Boolean loadIcons) {
		List<PaisDTO> dtos = new ArrayList<>();
		for (PaisEntity entity : entities) {
			dtos.add(this.paisEntityToDTO(entity, loadIcons));
		}
		return dtos;
	}
	
	public Set<PaisEntity> paisDTOList2Entity(List<PaisDTO> dtos){
		Set<PaisEntity> entities = new HashSet<>();
		for (PaisDTO dto : dtos) {
			entities.add(this.paisDTO2Entity(dto));
		}
		return entities;
	}
	
	public void paisEntityRefreshValues(PaisEntity entity, PaisDTO dto) {
		entity.setImagen(dto.getImagen());
		entity.setDenominacion(dto.getDenominacion());
		entity.setCantidadHabitantes(dto.getCantidadHabitantes());
		entity.setSuperficie(dto.getSuperficie());
		entity.setContinenteId(dto.getContinenteId());
	}

	public List<PaisSimpleDTO> paisEntity2PaisSimpleDTO(Collection<PaisEntity> entities) {
		List<PaisSimpleDTO> dtos = new ArrayList<>();
		PaisSimpleDTO simpleDTO;
		for (PaisEntity entity : entities) {
			simpleDTO = new PaisSimpleDTO();
			simpleDTO.setId(entity.getId());
			simpleDTO.setImagen(entity.getImagen());
			simpleDTO.setDenominacion(entity.getDenominacion());
			simpleDTO.setCantidadHabitantes(entity.getCantidadHabitantes());
			dtos.add(simpleDTO);
		}
		return dtos;
	}
}
