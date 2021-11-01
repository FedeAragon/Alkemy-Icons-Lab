package com.alkemy.icoons.icons.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.alkemy.icoons.icons.entity.IconEntity;

@Component
public class IconMapper {
	
	@Autowired
    private PaisMapper paisMapper;
	
	public List<IconSimpleDTO> iconEntityToIconSimpleDTO(Collection<IconEntity> iconEntities){
		List<IconSimpleDTO> dtos = new ArrayList<>();
		IconSimpleDTO simpleDTO;
		for (IconEntity entity : iconEntities) {
			simpleDTO = new IconSimpleDTO();
			simpleDTO.setId(entity.getId());
			simpleDTO.setImagen(entity.getImagen());
			simpleDTO.setDenominacion(entity.getDenominacion());
			dtos.add(simpleDTO);
		}
		return dtos;
	}

	public IconEntity iconDTOToEntity(IconDTO iconDTO) {
		IconEntity entity = new IconEntity();
        entity.setImagen(iconDTO.getImagen());
        entity.setDenominacion(iconDTO.getDenominacion());
        entity.setFechaCreacion(
                this.stringToLocalDate(iconDTO.getFechaCreacion())
        );
        entity.setAltura(iconDTO.getAltura());
        entity.setHistoria(iconDTO.getHistoria());
        return entity;
	}

	private LocalDate stringToLocalDate(String fechaCreacion) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fechaCreacion, formatter);
        return date;
	}

	public IconDTO iconEntityToDTO(IconEntity entitySaved, boolean loadPaises) {
		IconDTO dto = new IconDTO();
        dto.setId(entitySaved.getId());
        dto.setImagen(entitySaved.getImagen());
        dto.setDenominacion(entitySaved.getDenominacion());
        dto.setFechaCreacion(entitySaved.getFechaCreacion().toString());
        dto.setAltura(entitySaved.getAltura());
        dto.setHistoria(entitySaved.getHistoria());
        if (loadPaises) {
            List<PaisDTO> paisesDTO = this.paisMapper.paisEntityListToDTOList(entitySaved.getPaises(), false);
            dto.setPaises(paisesDTO);
        }
        return dto;
	}

	public List<IconDTO> iconEntitySetToDTOList(Collection<IconEntity> entities, boolean loadPaises) {
		List<IconDTO> dtos = new ArrayList<>();
        for (IconEntity entity : entities) {
            dtos.add(this.iconEntityToDTO(entity, loadPaises));
        }
        return dtos;
	}
	
	public Set<IconEntity> iconDTOList2Entity(List<IconDTO> dtos){
		Set<IconEntity> entities = new HashSet<>();
		for (IconDTO dto : dtos) {
			entities.add(this.iconDTOToEntity(dto));
		}
		return entities;
	}
	
	public void iconEntityRefreshValues(IconEntity entity, IconDTO dto) {
		entity.setImagen(dto.getImagen());
		entity.setDenominacion(dto.getDenominacion());
		entity.setFechaCreacion(this.stringToLocalDate(dto.getFechaCreacion()));
		entity.setAltura(dto.getAltura());
		entity.setHistoria(dto.getHistoria());
	}
}
