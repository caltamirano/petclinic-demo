package com.demo.petclinic.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.petclinic.domain.VetDTO;
import com.demo.petclinic.entity.Vet;
import com.demo.petclinic.exception.ResourceNotFoundException;
import com.demo.petclinic.repository.AppointmentRepository;
import com.demo.petclinic.repository.VetRepository;

@Service
@Transactional
public class VetServiceImpl implements VetService {
	
	@Autowired
	private ModelMapper modelMapper;
    
	@Autowired
	private VetRepository vetRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public List<VetDTO> getAllVets() {
		return vetRepository.findAll()
        		.stream()
        		.map(vet -> modelMapper.map(vet, VetDTO.class))
                .collect(toList());
	}

	@Override
	public VetDTO getVet(Integer vetId) throws ResourceNotFoundException {
		return vetRepository.findById(vetId)
				.map(vet -> modelMapper.map(vet, VetDTO.class))
				.orElseThrow(() -> new ResourceNotFoundException(String.format("A vet with id %s was not found", vetId)));
	}
	
	@Override
	public Integer saveVet(VetDTO vetDTO) {
		Vet vet = modelMapper.map(vetDTO, Vet.class);
		Vet newVet = vetRepository.save(vet);
		return newVet.getId();
	}

	@Override
	public void deleteVet(Integer vetId) {
		appointmentRepository.deleteByVetId(vetId);
		vetRepository.deleteById(vetId);
	}
}
