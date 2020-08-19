package com.demo.petclinic.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.petclinic.domain.PetDTO;
import com.demo.petclinic.entity.Pet;
import com.demo.petclinic.exception.ResourceNotFoundException;
import com.demo.petclinic.repository.AppointmentRepository;
import com.demo.petclinic.repository.PetRepository;

@Service
@Transactional
public class PetServiceImpl implements PetService {
	
	@Autowired
	private ModelMapper modelMapper;
    
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public List<PetDTO> getAllPets() {
		return petRepository.findAll()
        		.stream()
        		.map(pet -> modelMapper.map(pet, PetDTO.class))
                .collect(toList());
	}

	@Override
	public PetDTO getPet(Integer petId) {
		return petRepository.findById(petId)
				.map(pet -> modelMapper.map(pet, PetDTO.class))
				.orElseThrow(() -> new ResourceNotFoundException(String.format("A pet with id %s was not found", petId)));
	}

	@Override
	public Integer savePet(PetDTO petDTO) {
		Pet pet = modelMapper.map(petDTO, Pet.class);
		Pet savedPet = petRepository.save(pet);
		return savedPet.getId();
	}

	@Override
	public void deletePet(Integer petId) {
		appointmentRepository.deleteByPetId(petId);
		petRepository.deleteById(petId);
	}
}
