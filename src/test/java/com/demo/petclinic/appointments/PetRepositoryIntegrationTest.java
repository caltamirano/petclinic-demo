package com.demo.petclinic.appointments;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.petclinic.entity.Pet;
import com.demo.petclinic.entity.PetType;
import com.demo.petclinic.repository.PetRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class PetRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private PetRepository petRepository;

    @Test
    void whenFindById_thenReturnPet() {
    	//given
    	Pet pet = new Pet()
    		.setName("fido")
    		.setBirthDate(LocalDate.parse("2020-01-01"))
    		.setType(new PetType()
    				.setId(1));    	
    	Pet saved = entityManager.persistAndFlush(pet);
        
        //when
        Pet found = petRepository.findById(saved.getId()).orElse(null);
        
        //then
        assertThat(found).isEqualTo(pet);
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Pet found = petRepository.findById(-11).orElse(null);
        assertThat(found).isNull();
    }
    
    @Test
    public void givenSetOfPets_whenFindAll_thenReturnAllPets() {
    	Pet fido = new Pet()
        		.setName("fido")
        		.setBirthDate(LocalDate.now())
        		.setType(new PetType()
        				.setId(1)); 
    	
    	Pet lala = new Pet()
        		.setName("lala")
        		.setBirthDate(LocalDate.now())
        		.setType(new PetType()
        				.setId(2)); 
    	
    	Pet max = new Pet()
        		.setName("max")
        		.setBirthDate(LocalDate.now())
        		.setType(new PetType()
        				.setId(3)); 

        entityManager.persist(fido);
        entityManager.persist(lala);
        entityManager.persist(max);
        entityManager.flush();

        List<Pet> allPets = petRepository.findAll();

        assertThat(allPets).extracting(Pet::getName).contains(fido.getName(), lala.getName(), max.getName());
    }
}
