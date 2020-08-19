package com.demo.petclinic.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import com.demo.petclinic.domain.VetDTO;
import com.demo.petclinic.exception.ResourceNotFoundException;
import com.demo.petclinic.service.AppointmentService;
import com.demo.petclinic.service.VetService;

@RestController
@RequestMapping("/vets")
public class VetController {
	
	@Autowired
	VetService vetService;
	
	@Autowired
	AppointmentService appointmentService;

	@GetMapping
	public List<VetDTO> findAllVets() {
		return vetService.getAllVets();
	}
	
	@GetMapping("/{vetId}")
	public VetDTO getPet(@PathVariable Integer vetId) throws ResourceNotFoundException {
		return vetService.getVet(vetId);
	}
	
	@GetMapping("{vetId}/appointments")
	public List<AppointmentDTO> getAppointments(@PathVariable Integer vetId) {
		return appointmentService.getVetAppointments(vetId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VetDTO addVet(@RequestBody VetDTO vetDTO) {
		Integer vetId = vetService.saveVet(vetDTO);
		return vetService.getVet(vetId);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VetDTO updateVet(@RequestBody VetDTO vetDTO) {
		Integer vetId = vetService.saveVet(vetDTO);
		return vetService.getVet(vetId);
	}
	
	@DeleteMapping("/{vetId}")
	public void deletePet(@PathVariable Integer vetId) {
		vetService.deleteVet(vetId);
	}
	
	@GetMapping("{vetId}/slots/{date}")
	public List<String> getAppointmentSlots(@PathVariable Integer vetId, @PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
		return appointmentService.getAppointmentSlots(vetId, date);
	}
}
