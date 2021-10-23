package com.alkemy.icoons.icons.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IconSimpleDTO implements Serializable{
	
	private Long id;
	private String imagen;
	private String denominacion;
	
}
