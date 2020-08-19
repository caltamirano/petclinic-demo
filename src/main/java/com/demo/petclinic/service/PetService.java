package com.demo.petclinic.service;

import java.util.List;

import com.demo.petclinic.domain.PetDTO;

public interface PetService {

	List<PetDTO> getAllPets();
	
	PetDTO getPet(Integer petId);
	
	Integer savePet(PetDTO pet);
	
	void deletePet(Integer petId);
}
