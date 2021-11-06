package com.alkemy.icoons.icons.service;

import java.util.List;

import com.alkemy.icoons.icons.dto.PaisDTO;
import com.alkemy.icoons.icons.dto.PaisSimpleDTO;

public interface PaisService {
	
	public List<PaisSimpleDTO> getAll();

	public PaisDTO save(PaisDTO paisDTO);

	public PaisDTO getEntityById(Long idPais);

	public List<PaisDTO> getByFilters(String name, Long continent,String order);
	
	public PaisDTO update(Long id, PaisDTO paisDTO);
	
	public void addIcon(Long id, Long idIcon);
	
	public void removeIcon(Long id, Long idIcon);
	
	public void delete(Long id);
}
