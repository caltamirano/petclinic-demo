package com.demo.petclinic;

import javax.persistence.EntityManagerFactory;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PetclinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetclinicApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper(EntityManagerFactory emFactory) {
        ModelMapper mapper = new ModelMapper();
        
        //Add a global configuration to fetch handle the lazily loaded fields.
        mapper.getConfiguration().setPropertyCondition(context -> emFactory.getPersistenceUnitUtil().isLoaded(context.getSource()));
        
        return mapper;
    }
}
