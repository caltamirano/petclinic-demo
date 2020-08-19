package com.demo.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.petclinic.entity.Vet;

public interface VetRepository extends JpaRepository<Vet, Integer>{

}
