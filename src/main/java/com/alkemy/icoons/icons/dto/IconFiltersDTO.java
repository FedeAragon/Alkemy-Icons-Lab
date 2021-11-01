package com.alkemy.icoons.icons.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IconFiltersDTO {
	private String name;
	private String date;
	private Set<Long> cities;
	private String order;
	
	public boolean isASC() {
		return this.order.compareToIgnoreCase("ASC") == 0;
	}
	
	public boolean isDESC() {
		return this.order.compareToIgnoreCase("DESC") == 0;
	}
	
}
