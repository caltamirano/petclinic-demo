package com.demo.petclinic.appointments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.petclinic.domain.AppointmentDTO;
import com.demo.petclinic.domain.PetDTO;
import com.demo.petclinic.domain.VetDTO;
import com.demo.petclinic.service.AppointmentService;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppointmentServiceImplIntegrationTest {
 
    @Autowired
    private AppointmentService appointmentService;

    @Test
    void whenFindById_thenReturnAppointment() {
    	//given
    	AppointmentDTO appointment = new AppointmentDTO()
    			.setPet(new PetDTO()
    					.setId(1))
    			.setVet(new VetDTO()
    					.setId(1))
    			.setDate(LocalDate.now())
    			.setTime(LocalTime.NOON);
    	
    	Integer appointmentId = appointmentService.saveAppointment(appointment);
    	
    	//when        
    	AppointmentDTO found = appointmentService.getAppointment(appointmentId);
        
        //then
        assertNotNull(found.getId());
        assertThat(found.getVet().getId()).isEqualTo(appointment.getVet().getId());
        assertThat(found.getPet().getId()).isEqualTo(appointment.getPet().getId());
    }

    @Test
    void whenAppointmentSlotIsTaken_thenThrowException() {
    	//given
    	AppointmentDTO firstAppointment = new AppointmentDTO()
    			.setPet(new PetDTO()
    					.setId(5))
    			.setVet(new VetDTO()
    					.setId(5))
    			.setDate(LocalDate.now())
    			.setTime(LocalTime.NOON);
    	
    	AppointmentDTO secondAppointment = new AppointmentDTO()
    			.setPet(new PetDTO()
    					.setId(6))
    			.setVet(new VetDTO()
    					.setId(5))
    			.setDate(LocalDate.now())
    			.setTime(LocalTime.NOON);
    	
    	Integer appointmentId = appointmentService.saveAppointment(firstAppointment);
    	
    	AppointmentDTO found = appointmentService.getAppointment(appointmentId);
    	
    	assertNotNull(appointmentId);
    	assertThat(found.getPet().getId()).isEqualTo(firstAppointment.getPet().getId());    	    	
    	assertThrows(DataIntegrityViolationException.class, () -> {
    		appointmentService.saveAppointment(secondAppointment);
        });
    }
}
