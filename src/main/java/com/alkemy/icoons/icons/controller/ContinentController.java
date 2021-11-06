package com.alkemy.icoons.icons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.icoons.icons.dto.ContinentDTO;
import com.alkemy.icoons.icons.service.ContinentService;

@RestController
@RequestMapping("continent")
@CrossOrigin("*")
public class ContinentController {
	
	@Autowired
	private ContinentService continentService;
	
	@GetMapping
	public ResponseEntity<List<ContinentDTO>> getAll(){
		List<ContinentDTO> continents = this.continentService.getAllContinents();
		return ResponseEntity.ok().body(continents);
	}
	
	@PostMapping
	public ResponseEntity<ContinentDTO> save(@RequestBody ContinentDTO continentDTO){
		ContinentDTO continentSaved = this.continentService.save(continentDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(continentSaved);
	}
}
