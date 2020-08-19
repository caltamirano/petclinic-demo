package com.demo.petclinic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.petclinic.domain.AppointmentDTO;
import com.demo.petclinic.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

	@Autowired
	AppointmentService appointmentService;

	@GetMapping
	public List<AppointmentDTO> findAllAppointments() {
		return appointmentService.getAllAppointments();
	}

	@GetMapping("/{appointmentId}")
	public AppointmentDTO getPet(@PathVariable Integer appointmentId) {
		return appointmentService.getAppointment(appointmentId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AppointmentDTO addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		Integer appointmentId = appointmentService.saveAppointment(appointmentDTO);
		return appointmentService.getAppointment(appointmentId);
	}

	@DeleteMapping("/{appointmentId}")
	public void deleteAppointment(@PathVariable Integer appointmentId) {
		appointmentService.deleteAppointment(appointmentId);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException e) {
		String errorMessage = "An appointment for the given date/time already exists. Please try again.";
		return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
	}
}
