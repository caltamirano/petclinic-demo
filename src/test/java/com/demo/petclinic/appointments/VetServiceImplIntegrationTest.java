package com.demo.petclinic.appointments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.petclinic.domain.VetDTO;
import com.demo.petclinic.service.VetService;

@RunWith(SpringRunner.class)
@SpringBootTest
class VetServiceImplIntegrationTest {
 
    @Autowired
    private VetService vetService;

    @Test
    void whenFindById_thenReturnVet() {
    	//given
    	VetDTO vet = new VetDTO()
    			.setFirstName("John")
    			.setLastName("Doe");
    	
    	Integer vetId = vetService.saveVet(vet);
    	
    	//when        
        VetDTO found = vetService.getVet(vetId);
        
        //then
        assertNotNull(found.getId());
        assertThat(found.getFirstName()).isEqualTo(vet.getFirstName());
        assertThat(found.getLastName()).isEqualTo(vet.getLastName());
    }

}
