package com.alkemy.icoons.icons.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.icoons.icons.dto.IconSimpleDTO;
import com.alkemy.icoons.icons.entity.IconEntity;

@Component
public class IconMapper {

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
}
