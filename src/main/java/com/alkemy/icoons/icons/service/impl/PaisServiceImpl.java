package com.alkemy.icoons.icons.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.icoons.icons.dto.PaisDTO;
import com.alkemy.icoons.icons.dto.PaisFiltersDTO;
import com.alkemy.icoons.icons.dto.PaisSimpleDTO;
import com.alkemy.icoons.icons.entity.IconEntity;
import com.alkemy.icoons.icons.entity.PaisEntity;
import com.alkemy.icoons.icons.exception.ParamNotFound;
import com.alkemy.icoons.icons.mapper.PaisMapper;
import com.alkemy.icoons.icons.repository.IconRepository;
import com.alkemy.icoons.icons.repository.PaisRepository;
import com.alkemy.icoons.icons.repository.specifications.PaisSpecification;
import com.alkemy.icoons.icons.service.PaisService;

@Service
public class PaisServiceImpl implements PaisService {

	private PaisRepository paisRepository;
	private PaisSpecification paisSpecification;

	private PaisMapper paisMapper;

	private IconRepository iconRepository;

	@Autowired
	public PaisServiceImpl(PaisRepository paisRepository, PaisMapper paisMapper, IconRepository iconRepository,
			PaisSpecification paisSpecification) {
		this.paisRepository = paisRepository;
		this.paisMapper = paisMapper;
		this.iconRepository = iconRepository;
		this.paisSpecification = paisSpecification;

	}

	public PaisDTO getEntityById(Long idPais) {
		PaisEntity entity = this.paisRepository.findById(idPais)
				.orElseThrow(() -> new ParamNotFound("No existe un pais con id " + idPais));
		PaisDTO dto = this.paisMapper.paisEntityToDTO(entity, false);
		return dto;
	}

	public List<PaisSimpleDTO> getAll() {
		List<PaisEntity> entities = this.paisRepository.findAll();
		List<PaisSimpleDTO> dtos = this.paisMapper.paisEntity2PaisSimpleDTO(entities);
		return dtos;
	}

	public PaisDTO save(PaisDTO paisDTO) {
		PaisEntity entity = this.paisMapper.paisDTO2Entity(paisDTO);
		PaisEntity entitySaved = this.paisRepository.save(entity);
		PaisDTO result = this.paisMapper.paisEntityToDTO(entitySaved, false);
		return result;
	}

	public List<PaisDTO> getByFilters(String name, Long continent, String order) {
		PaisFiltersDTO filtersDTO = new PaisFiltersDTO(name, continent, order);
		List<PaisEntity> entities = this.paisRepository.findAll(this.paisSpecification.getByFilters(filtersDTO));
		List<PaisDTO> dtos = this.paisMapper.paisEntitySet2DTOList(entities, true);
		return dtos;
	}

	public PaisDTO update(Long id, PaisDTO paisDTO) {
		PaisEntity entity = this.paisRepository.findById(id)
				.orElseThrow(() -> new ParamNotFound("No existe un Pais con id " + id));
		this.paisMapper.paisEntityRefreshValues(entity, paisDTO);
		PaisEntity entitySaved = this.paisRepository.save(entity);
		PaisDTO result = this.paisMapper.paisEntityToDTO(entitySaved, false);
		return result;
	}

	public void addIcon(Long id, Long idIcon) {
		PaisEntity entity = this.paisRepository.getById(id);
		entity.getIcons().size();
		IconEntity iconEntity = this.iconRepository.getById(idIcon);
		entity.addIcon(iconEntity);
		this.paisRepository.save(entity);
	}

	public void removeIcon(Long id, Long idIcon) {
		PaisEntity entity = this.paisRepository.getById(id);
		entity.getIcons().size();
		IconEntity iconEntity = this.iconRepository.getById(idIcon);
		entity.removeIcon(iconEntity);
		this.paisRepository.save(entity);
	}

	public void delete(Long id) {
		PaisEntity entity = this.paisRepository.findById(id)
				.orElseThrow(() -> new ParamNotFound("No existe un Pais con id " + id));
		this.iconRepository.deleteById(entity.getId());
	}

}
