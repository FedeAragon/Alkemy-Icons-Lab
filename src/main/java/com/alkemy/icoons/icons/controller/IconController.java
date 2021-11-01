package com.alkemy.icoons.icons.controller;

import java.util.List;
import java.util.Set;

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

import com.alkemy.icoons.icons.dto.IconDTO;
import com.alkemy.icoons.icons.dto.IconSimpleDTO;
import com.alkemy.icoons.icons.service.IconService;

@RestController
@RequestMapping("icon")
@CrossOrigin("*")
public class IconController {
	
	private IconService iconService;
	
	@Autowired
	public IconController(IconService iconService) {
		this.iconService = iconService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<IconSimpleDTO>> getAll() {
		List<IconSimpleDTO> iconEntities = this.iconService.getAll();
		return ResponseEntity.ok(iconEntities);
		
	}
	
	@PostMapping
    public ResponseEntity<IconDTO> save(@RequestBody IconDTO icon) {
		IconDTO result = this.iconService.save(icon);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<IconDTO> getDetailsById(@PathVariable Long id){
		IconDTO icon = this.iconService.getDetailsById(id);
		return ResponseEntity.ok(icon);
	}
	
	@GetMapping
	public ResponseEntity<List<IconDTO>> getDetailByFilteers(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String date,
			@RequestParam(required = false) Set<Long> cities,
			@RequestParam(required = false, defaultValue = "ASC") String order
	){
		List<IconDTO> icons = this.iconService.getByFilters(name, date, cities, order);
		return ResponseEntity.ok(icons);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<IconDTO> update(@PathVariable Long id, @RequestBody IconDTO iconDTO){
		IconDTO result = this.iconService.update(id, iconDTO);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		this.iconService.delete(id);
		return ResponseEntity.ok("Deleted");
	}
	
	@PostMapping("/{id}/pais/{idPais}")
	public ResponseEntity<String> addPais(@PathVariable Long id, @PathVariable Long idPais) {
		this.iconService.addPais(id, idPais);
		return ResponseEntity.ok("Deleted");
	}
	
	@DeleteMapping("/{id}/pais/{idPais}")
	public ResponseEntity<String> removePais(@PathVariable Long id, @PathVariable Long idPais) {
		this.iconService.removePais(id, idPais);
		return ResponseEntity.ok("Deleted");
	}
}
