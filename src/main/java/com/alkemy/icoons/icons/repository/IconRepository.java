package com.alkemy.icoons.icons.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.icoons.icons.entity.IconEntity;

@Repository
public interface IconRepository extends JpaRepository<IconEntity, Long>{
	
	public List<IconEntity> findAll(Specification<IconEntity> spec);
}
