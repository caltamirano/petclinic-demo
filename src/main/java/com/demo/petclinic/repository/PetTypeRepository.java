package com.demo.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.petclinic.entity.PetType;

public interface PetTypeRepository extends JpaRepository<PetType, Integer>{
}
