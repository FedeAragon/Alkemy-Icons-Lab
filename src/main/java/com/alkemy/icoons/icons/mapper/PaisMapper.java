package com.alkemy.icoons.icons.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.icoons.icons.dto.IconDTO;
import com.alkemy.icoons.icons.dto.PaisDTO;
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

	private PaisDTO paisEntityToDTO(PaisEntity entity, boolean loadIcons) {
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
	
	private PaisEntity paisDTO2Entity(PaisDTO dto) {
		PaisEntity entity = new PaisEntity();
		entity.setImagen(dto.getImagen());
		entity.setDenominacion(dto.getDenominacion());
		entity.setCantidadHabitantes(dto.getCantidadHabitantes());
		entity.setSuperficie(dto.getSuperficie());
		entity.setContinenteId(dto.getContinenteId());
		return entity;
	}
}
