package com.demo.petclinic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.petclinic.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{
	
	@Query("SELECT p FROM Pet p LEFT JOIN FETCH p.type")  
	List<Pet> findWithoutNPlusOne();
	
	@EntityGraph(attributePaths = {"type"})
    List<Pet> findAll();
	
	@EntityGraph(attributePaths = {"type"})
	Optional<Pet> findById(Integer petId);
}
