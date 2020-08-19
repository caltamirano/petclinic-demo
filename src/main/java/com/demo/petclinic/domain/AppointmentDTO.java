package com.demo.petclinic.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class AppointmentDTO {
	
	private Integer id;
	
	private VetDTO vet;
	
	private PetDTO pet;
	
	private LocalDate date;
	
	private LocalTime time;
	
	private String description;

	public Integer getId() {
		return id;
	}

	public AppointmentDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public VetDTO getVet() {
		return vet;
	}

	public AppointmentDTO setVet(VetDTO vet) {
		this.vet = vet;
		return this;
	}

	public PetDTO getPet() {
		return pet;
	}

	public AppointmentDTO setPet(PetDTO pet) {
		this.pet = pet;
		return this;
	}

	public LocalDate getDate() {
		return date;
	}

	public AppointmentDTO setDate(LocalDate date) {
		this.date = date;
		return this;
	}

	public LocalTime getTime() {
		return time;
	}

	public AppointmentDTO setTime(LocalTime time) {
		this.time = time;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public AppointmentDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, description, id, pet, time, vet);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentDTO other = (AppointmentDTO) obj;
		return Objects.equals(date, other.date) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(pet, other.pet) && Objects.equals(time, other.time)
				&& Objects.equals(vet, other.vet);
	}

	@Override
	public String toString() {
		return "AppointmentDTO [id=" + id + ", vet=" + vet + ", pet=" + pet + ", date=" + date + ", time=" + time
				+ ", description=" + description + "]";
	}
}
