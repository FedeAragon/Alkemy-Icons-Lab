package com.alkemy.icoons.icons.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.icoons.icons.entity.PaisEntity;

@Repository
public interface PaisRepository extends JpaRepository<PaisEntity, Long>{
	
	public List<PaisEntity> findAll(Specification<PaisEntity> spec);
}
