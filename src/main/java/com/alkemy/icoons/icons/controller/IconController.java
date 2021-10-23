package com.alkemy.icoons.icons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
