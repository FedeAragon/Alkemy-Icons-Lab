package com.alkemy.icoons.icons.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaisFiltersDTO {
	private String name;
	private Long continentId;
	private String order;
	
	public boolean isASC() {
		return this.order.compareToIgnoreCase("ASC") == 0;
	}
	
	public boolean isDESC() {
		return this.order.compareToIgnoreCase("DESC") == 0;
	}
}
