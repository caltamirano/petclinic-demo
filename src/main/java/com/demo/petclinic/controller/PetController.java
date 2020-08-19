package com.demo.petclinic.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.petclinic.domain.AppointmentDTO;
import com.demo.petclinic.domain.PetDTO;
import com.demo.petclinic.service.AppointmentService;
import com.demo.petclinic.service.PetService;

@RestController
@RequestMapping("/pets")
public class PetController {
	
	PetService petService;
	
	AppointmentService appointmentService;
	
	public PetController(PetService petService, AppointmentService appointmentService) {
		this.petService = petService;
		this.appointmentService = appointmentService;
	}

	@GetMapping
	public List<PetDTO> getAllPets() {
		return petService.getAllPets();
	}

	@GetMapping("/{petId}")
	public PetDTO getPet(@PathVariable Integer petId) {
		return petService.getPet(petId);
	}
	
	@GetMapping("{petId}/appointments")
	public List<AppointmentDTO> getAppointments(@PathVariable Integer petId) {
		return appointmentService.getPetAppointments(petId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PetDTO addPet(@RequestBody PetDTO petDTO) {
		Integer petId = petService.savePet(petDTO);
		return petService.getPet(petId);
	}
	
	@PutMapping
	public PetDTO updatePet(@RequestBody PetDTO petDTO) {
		Integer petId = petService.savePet(petDTO);
		return petService.getPet(petId);
	}
	
	@DeleteMapping("/{petId}")
	public void deletePet(@PathVariable Integer petId) {
		petService.deletePet(petId);
	}
}
