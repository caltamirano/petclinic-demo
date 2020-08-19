package com.demo.petclinic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.petclinic.domain.PetTypeDTO;
import com.demo.petclinic.service.PetTypeService;

@RestController
@RequestMapping("/pet-types")
public class PetTypesController {

	PetTypeService petTypeService;
	
	public PetTypesController(PetTypeService petTypeService) {
		this.petTypeService = petTypeService;
	}

	@GetMapping
	public List<PetTypeDTO> getAllPetTypes() {
		return petTypeService.getAllPetTypes();
	}
}
