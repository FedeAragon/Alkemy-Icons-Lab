package com.alkemy.icoons.icons.repository.specifications;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alkemy.icoons.icons.dto.IconFiltersDTO;
import com.alkemy.icoons.icons.entity.IconEntity;
import com.alkemy.icoons.icons.entity.PaisEntity;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Expression;

@Component
public class IconSpecification {
	
	public Specification<IconEntity> getByFilters(IconFiltersDTO filtersDTO){
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
			
			if(StringUtils.hasLength(filtersDTO.getDate())) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(filtersDTO.getDate(), formatter);
				
				predicates.add(
						criteriaBuildder.equal(root.<LocalDate>get("fechaCreacion"), date)
				);
			}
			
			if(!CollectionUtils.isEmpty(filtersDTO.getCities())) {
				Join<PaisEntity, IconEntity> join = root.join("paises", JoinType.INNER);
				Expression<String> citiesId = join.get("id");
				predicates.add(citiesId.in(filtersDTO.getCities()));
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
