package com.alkemy.icoons.icons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.icoons.icons.entity.PaisEntity;

@Repository
public interface PaisRepository extends JpaRepository<PaisEntity, Long>{
}
