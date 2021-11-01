package com.alkemy.icoons.icons.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaisDTO implements Serializable{
	private Long id;
    private String imagen;
    private String denominacion;
    private Long cantidadHabitantes;
    private Long superficie;
    private Long continenteId;
	private List<IconDTO> icons;
}
