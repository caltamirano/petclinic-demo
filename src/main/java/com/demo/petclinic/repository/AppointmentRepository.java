package com.demo.petclinic.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.petclinic.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	@EntityGraph(attributePaths = {"vet", "pet", "pet.type"})
	List<Appointment> findAll();
	
	@EntityGraph(attributePaths = {"vet", "pet", "pet.type"})
	Optional<Appointment> findById(Integer appointmentId);
	
	@EntityGraph(attributePaths = {"vet", "pet", "pet.type"})
	List<Appointment> findByPetId(Integer petId);
	
	@EntityGraph(attributePaths = {"vet", "pet", "pet.type"})
	List<Appointment> findByVetId(Integer vetId);
	
	@EntityGraph(attributePaths = {"vet", "pet", "pet.type"})
	List<Appointment> findByVetIdAndDate(Integer vetId, LocalDate date);
	
	void deleteByPetId(Integer petId);
	
	void deleteByVetId(Integer vetId);
}
