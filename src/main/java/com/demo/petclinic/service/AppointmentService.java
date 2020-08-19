package com.demo.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import com.demo.petclinic.domain.AppointmentDTO;
import com.demo.petclinic.exception.ResourceNotFoundException;

public interface AppointmentService {

	List<AppointmentDTO> getAllAppointments();
	
	AppointmentDTO getAppointment(Integer appointmentId) throws ResourceNotFoundException;
	
	List<AppointmentDTO> getPetAppointments(Integer petId);
	
	List<AppointmentDTO> getVetAppointments(Integer vetId);
	
	List<String> getAppointmentSlots(Integer vetId, LocalDate date);
	
	public Integer saveAppointment(AppointmentDTO appointmentDTO);
	
	void deleteAppointment(Integer appointmentId);
}
