package com.demo.petclinic.appointments;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.petclinic.entity.Vet;
import com.demo.petclinic.repository.VetRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class VetRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private VetRepository vetRepository;

    @Test
    void whenFindById_thenReturnVet() {
    	//given
    	Vet vet = new Vet()
    		.setFirstName("John")
    		.setLastName("Doe");    	
    	Vet saved = entityManager.persistAndFlush(vet);
        
        //when
        Vet found = vetRepository.findById(saved.getId()).orElse(null);
        
        //then
        assertThat(found).isEqualTo(vet);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Vet found = vetRepository.findById(-11).orElse(null);
        assertThat(found).isNull();
    }
    
    @Test
    public void givenSetOfVets_whenFindAll_thenReturnAllVets() {

    	Vet john = new Vet()
        		.setFirstName("John")
        		.setLastName("Doe");
    	Vet jane = new Vet()
    			.setFirstName("Jane")
    			.setLastName("Doe");
    	Vet richard = new Vet()
    			.setFirstName("Richard")
    			.setLastName("Roe");
    	
        entityManager.persist(john);
        entityManager.persist(jane);
        entityManager.persist(richard);
        entityManager.flush();

        List<Vet> allVets = vetRepository.findAll();

        assertThat(allVets).extracting(Vet::getFirstName).contains(john.getFirstName(), jane.getFirstName(), richard.getFirstName());
    }
}
