package com.alkemy.icoons.icons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.icoons.icons.entity.ContinenteEntity;

@Repository
public interface ContinenteRepository extends JpaRepository<ContinenteEntity, Long>{

}
