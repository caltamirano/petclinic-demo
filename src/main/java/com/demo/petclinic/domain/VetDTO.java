package com.demo.petclinic.domain;

import java.util.Objects;

public class VetDTO {
	
	Integer id;
	
	String firstName;
	
	String lastName;

	public Integer getId() {
		return id;
	}

	public VetDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public VetDTO setFirstName(String fistName) {
		this.firstName = fistName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public VetDTO setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VetDTO other = (VetDTO) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "VetDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
