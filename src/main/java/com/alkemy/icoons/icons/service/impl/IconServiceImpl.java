package com.alkemy.icoons.icons.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icoons.icons.dto.IconDTO;
import com.alkemy.icoons.icons.dto.IconFiltersDTO;
import com.alkemy.icoons.icons.dto.IconSimpleDTO;
import com.alkemy.icoons.icons.entity.IconEntity;
import com.alkemy.icoons.icons.entity.PaisEntity;
import com.alkemy.icoons.icons.exception.ParamNotFound;
import com.alkemy.icoons.icons.mapper.IconMapper;
import com.alkemy.icoons.icons.repository.IconRepository;
import com.alkemy.icoons.icons.repository.specifications.IconSpecification;
import com.alkemy.icoons.icons.service.IconService;
import com.alkemy.icoons.icons.service.PaisService;

@Service
public class IconServiceImpl implements IconService {

	private IconRepository iconRepository;
	private IconSpecification iconSpecification;

	private IconMapper iconMapper;

	private PaisService paisService;

	@Autowired
	public IconServiceImpl(IconRepository iconRepository, IconMapper iconMapper, IconSpecification iconSpecification,
			PaisService paisService) {
		this.iconRepository = iconRepository;
		this.iconMapper = iconMapper;
		this.iconSpecification = iconSpecification;
		this.paisService = paisService;
	}

	public List<IconSimpleDTO> getAll() {
		List<IconEntity> iconEntities = this.iconRepository.findAll();
		List<IconSimpleDTO> simpleDTOs = this.iconMapper.iconEntityToIconSimpleDTO(iconEntities);
		return simpleDTOs;
	}

	public IconDTO save(IconDTO iconDTO) {
		IconEntity entity = this.iconMapper.iconDTOToEntity(iconDTO);
		IconEntity entitySaved = this.iconRepository.save(entity);
		IconDTO result = this.iconMapper.iconEntityToDTO(entitySaved, false);
		return result;
	}

	public IconDTO getDetailsById(Long id) {
		IconEntity entity = this.iconRepository.findById(id)
				.orElseThrow(() -> new ParamNotFound("No existe un Icono con id " + id));
		IconDTO dto = this.iconMapper.iconEntityToDTO(entity, false);
		return dto;
	}

	public List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order) {
		IconFiltersDTO filtersDTO = new IconFiltersDTO(name, date, cities, order);
		List<IconEntity> entities = this.iconRepository.findAll(this.iconSpecification.getByFilters(filtersDTO));
		List<IconDTO> dtos = this.iconMapper.iconEntitySetToDTOList(entities, true);
		return dtos;
	}

	public IconDTO update(Long id, IconDTO iconDTO) {
		IconEntity entity = this.iconRepository.findById(id)
				.orElseThrow(() -> new ParamNotFound("No existe un Icono con id " + id));
		this.iconMapper.iconEntityRefreshValues(entity, iconDTO);
		IconEntity entitySaved = this.iconRepository.save(entity);
		IconDTO result = this.iconMapper.iconEntityToDTO(entitySaved, false);
		return result;
	}

	public void addPais(Long id, Long idPais) {
		IconEntity entity = this.iconRepository.getById(id);
		entity.getPaises().size();
		PaisEntity paisEntity = this.paisService.getEntityById(idPais);
		entity.addPais(paisEntity);
		this.iconRepository.save(entity);
	}

	public void removePais(Long id, Long idPais) {
		IconEntity entity = this.iconRepository.getById(id);
		entity.getPaises().size();
		PaisEntity paisEntity = this.paisService.getEntityById(idPais);
		entity.removePais(paisEntity);
		this.iconRepository.save(entity);
	}

	public void delete(Long id) {
		IconEntity entity = this.iconRepository.findById(id)
				.orElseThrow(() -> new ParamNotFound("No existe un Icono con id " + id));
		this.iconRepository.deleteById(entity.getId());
	}

}
