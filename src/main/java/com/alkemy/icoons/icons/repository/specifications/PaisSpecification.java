package com.alkemy.icoons.icons.repository.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alkemy.icoons.icons.dto.PaisFiltersDTO;
import com.alkemy.icoons.icons.entity.PaisEntity;

@Component
public class PaisSpecification {
	public Specification<PaisEntity> getByFilters(PaisFiltersDTO filtersDTO){
		return (root, query, criteriaBuildder) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			
			if(StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						criteriaBuildder.like(
								 criteriaBuildder.lower(root.get("denominacion")),
								 "%" + filtersDTO.getName().toLowerCase() + "%"
						)
				);
			}
			
			if(filtersDTO.getContinentId()>0) {
				
				predicates.add(
						criteriaBuildder.equal(root.get("continentId"), filtersDTO.getContinentId())
				);
			}
			
			//duplicates
			query.distinct(true);
			
			//order resolver
			String orderByField = "denominacion";
			query.orderBy(
					filtersDTO.isASC() ? 
							criteriaBuildder.asc(root.get(orderByField)) :
							criteriaBuildder.desc(root.get(orderByField))
			);
			
			return criteriaBuildder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
