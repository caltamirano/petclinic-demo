package com.demo.petclinic.appointments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.petclinic.domain.PetDTO;
import com.demo.petclinic.domain.PetTypeDTO;
import com.demo.petclinic.service.PetService;

@RunWith(SpringRunner.class)
@SpringBootTest
class PetServiceImplIntegrationTest {
 
    @Autowired
    private PetService petService;

    @Before
	public void setUp() {
	}

    @Test
    void whenFindById_thenReturnPet() {
    	//given
    	PetDTO pet = new PetDTO()
    			.setName("Fido")
    			.setBirthDate(LocalDate.parse("2018-03-15"))
    			.setType(new PetTypeDTO()
    					.setId(2));
    	Integer petId = petService.savePet(pet);
    	
    	//when        
        PetDTO found = petService.getPet(petId);
        
        //then
        assertNotNull(found.getId());
        assertThat(found.getName()).isEqualTo(pet.getName());
    }

}
