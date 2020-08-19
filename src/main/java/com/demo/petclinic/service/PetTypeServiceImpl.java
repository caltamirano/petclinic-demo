package com.demo.petclinic.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.petclinic.domain.PetTypeDTO;
import com.demo.petclinic.repository.PetTypeRepository;

@Service
@Transactional
public class PetTypeServiceImpl implements PetTypeService {
	
	@Autowired
	private ModelMapper modelMapper;
    
	@Autowired
	private PetTypeRepository petTypeRepository;

	@Override
	public List<PetTypeDTO> getAllPetTypes() {
		return petTypeRepository.findAll()
        		.stream()
        		.map(pet -> modelMapper.map(pet, PetTypeDTO.class))
                .collect(toList());
	}
}
