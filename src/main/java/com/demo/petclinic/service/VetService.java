package com.demo.petclinic.service;

import java.util.List;

import com.demo.petclinic.domain.VetDTO;
import com.demo.petclinic.exception.ResourceNotFoundException;

public interface VetService {

	List<VetDTO> getAllVets();
	
	VetDTO getVet(Integer vetId) throws ResourceNotFoundException;
	
	public Integer saveVet(VetDTO vetDTO);
	
	void deleteVet(Integer vetId);
}
