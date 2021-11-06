package com.alkemy.icoons.icons.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IconDTO implements Serializable{
	
	private Long id;
    private String imagen;
    private String denominacion;
    private String fechaCreacion;
    private Long altura;
    private String historia;
	
    private List<PaisDTO> paises;
}
