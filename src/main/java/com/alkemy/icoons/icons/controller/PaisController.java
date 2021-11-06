package com.alkemy.icoons.icons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.icoons.icons.dto.PaisDTO;
import com.alkemy.icoons.icons.dto.PaisSimpleDTO;
import com.alkemy.icoons.icons.service.PaisService;

@RestController
@RequestMapping("cities")
@CrossOrigin("*")
public class PaisController {

	private PaisService paisService;

	@Autowired
	public PaisController(PaisService paisService) {
		this.paisService = paisService;
	}

	@GetMapping("all")
	public ResponseEntity<List<PaisSimpleDTO>> getAll() {
		List<PaisSimpleDTO> simpleDTOs = this.paisService.getAll();
		return ResponseEntity.ok(simpleDTOs);
	}

	@PostMapping
	public ResponseEntity<PaisDTO> save(@RequestBody PaisDTO dto) {
		PaisDTO result = this.paisService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaisDTO> getDetailsById(@PathVariable Long id){
		PaisDTO dto = this.paisService.getEntityById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<PaisDTO>> getDetailByFilters(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Long continent,
			@RequestParam(required = false, defaultValue = "ASC") String order
	){
		List<PaisDTO> paises = this.paisService.getByFilters(name, continent, order);
		return ResponseEntity.ok(paises);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PaisDTO> update(@PathVariable Long id, @RequestBody PaisDTO paisDTO){
		PaisDTO result = this.paisService.update(id, paisDTO);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		this.paisService.delete(id);
		return ResponseEntity.ok("Deleted");
	}
}
