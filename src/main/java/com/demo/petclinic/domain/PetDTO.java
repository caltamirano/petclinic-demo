package com.demo.petclinic.domain;

import java.time.LocalDate;
import java.util.Objects;

public class PetDTO {
	
	Integer id;
	
	String name;
	
	LocalDate birthDate;
	
	PetTypeDTO type;

	public Integer getId() {
		return id;
	}

	public PetDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public PetDTO setName(String name) {
		this.name = name;
		return this;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public PetDTO setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
		return this;
	}

	public PetTypeDTO getType() {
		return type;
	}

	public PetDTO setType(PetTypeDTO type) {
		this.type = type;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, id, name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PetDTO other = (PetDTO) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "PetDTO [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", type=" + type + "]";
	}

}
