package com.alkemy.icoons.icons.service;

import java.util.List;

import com.alkemy.icoons.icons.dto.ContinentDTO;

public interface ContinentService {
	public ContinentDTO save(ContinentDTO continentDTO);

	public List<ContinentDTO> getAllContinents();
}
