package com.demo.petclinic.appointments;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.petclinic.entity.Appointment;
import com.demo.petclinic.entity.Pet;
import com.demo.petclinic.entity.Vet;
import com.demo.petclinic.repository.AppointmentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class AppointmentRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void whenFindById_thenReturnAppointment() {
    	//given
    	Appointment appointment = new Appointment()
    			.setPet(new Pet()
    					.setId(1))
    			.setVet(new Vet()
    					.setId(1))
    			.setDate(LocalDate.now())
    			.setTime(LocalTime.NOON);
    	
    	Appointment saved = entityManager.persistAndFlush(appointment);
        
        //when
    	Appointment found = appointmentRepository.findById(saved.getId()).orElse(null);
        
        //then
        assertThat(found).isEqualTo(appointment);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
    	Appointment found = appointmentRepository.findById(-11).orElse(null);
        assertThat(found).isNull();
    }
    
    @Test
    public void whenFindAll_thenReturnAllAppointments() {
    	
    	Appointment appointment = new Appointment()
    			.setPet(new Pet()
    					.setId(1))
    			.setVet(new Vet()
    					.setId(1))
    			.setDate(LocalDate.now())
    			.setTime(LocalTime.NOON);
    	
    	entityManager.persistAndFlush(appointment);
    	
    	List<Appointment> appointments = appointmentRepository.findAll();
    	
    	assertThat(appointments).size().isGreaterThan(0);
    }
}
