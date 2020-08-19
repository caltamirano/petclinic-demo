package com.demo.petclinic.domain;

import java.util.Objects;

public class PetTypeDTO {

	Integer id;
	
	String name;

	public Integer getId() {
		return id;
	}

	public PetTypeDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public PetTypeDTO setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PetTypeDTO other = (PetTypeDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "PetTypeDTO [id=" + id + ", name=" + name + "]";
	}
}
