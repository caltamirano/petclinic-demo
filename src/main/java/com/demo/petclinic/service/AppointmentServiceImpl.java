package com.demo.petclinic.service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.petclinic.domain.AppointmentDTO;
import com.demo.petclinic.entity.Appointment;
import com.demo.petclinic.exception.ResourceNotFoundException;
import com.demo.petclinic.repository.AppointmentRepository;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	
	private static final String[] SLOTS = new String[] {"08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00"};
	
	private ModelMapper modelMapper;
    
	private AppointmentRepository appointmentRepository;

	@Autowired
    public AppointmentServiceImpl(ModelMapper modelMapper, AppointmentRepository repository) {
        this.modelMapper = modelMapper;
        this.appointmentRepository = repository;
    }

	@Override
	public List<AppointmentDTO> getAllAppointments() {
		return appointmentRepository.findAll()
        		.stream()
        		.map(pet -> modelMapper.map(pet, AppointmentDTO.class))
                .collect(toList());
	}

	@Override
	public AppointmentDTO getAppointment(Integer appointmentId) throws ResourceNotFoundException {
		return appointmentRepository.findById(appointmentId)				
				.map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
				.orElseThrow(() -> new ResourceNotFoundException(String.format("An appointment with id %s was not found", appointmentId)));
	}

	@Override
	public List<AppointmentDTO> getPetAppointments(Integer petId) {
		return appointmentRepository.findByPetId(petId)
				.stream()
				.map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
				.collect(toList());
	}

	@Override
	public List<AppointmentDTO> getVetAppointments(Integer vetId) {
		return  appointmentRepository.findByVetId(vetId)
				.stream()
				.map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
				.collect(toList());
	}
	
	@Override
	public List<String> getAppointmentSlots(Integer vetId, LocalDate date) {
		Set<LocalTime> busySlots = appointmentRepository.findByVetIdAndDate(vetId, date)
				.stream()
				.map(appointment -> appointment.getTime())
				.collect(toSet());
		
		return Stream.of(SLOTS).filter(slot -> !busySlots.contains(LocalTime.parse(slot))).collect(toList());
	}

	@Override
	public Integer saveAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);
		Appointment newAppointment = appointmentRepository.save(appointment);
		return newAppointment.getId();
	}

	@Override
	public void deleteAppointment(Integer appointmentId) {
		appointmentRepository.deleteById(appointmentId);
	}
}
