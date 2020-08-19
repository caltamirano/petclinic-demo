package com.demo.petclinic.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 3527240284716731794L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name = "appt_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    
    @Column(name = "appt_time")
    private LocalTime time;
    
    @Column
    private String description;
    
    public Integer getId() {
        return id;
    }

    public Appointment setId(Integer id) {
        this.id = id;
        return this;
    }

    public Vet getVet() {
        return vet;
    }

    public Appointment setVet(Vet vet) {
        this.vet = vet;
        return this;
    }

    public Pet getPet() {
        return pet;
    }

    public Appointment setPet(Pet pet) {
        this.pet = pet;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Appointment setDate(LocalDate date) {
        this.date = date;
        return this;
    }

	public LocalTime getTime() {
		return time;
	}

	public Appointment setTime(LocalTime time) {
		this.time = time;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Appointment setDescription(String description) {
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
		Appointment other = (Appointment) obj;
		return Objects.equals(date, other.date) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(pet, other.pet) && Objects.equals(time, other.time)
				&& Objects.equals(vet, other.vet);
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", vet=" + vet + ", pet=" + pet + ", date=" + date + ", time=" + time
				+ ", description=" + description + "]";
	}
}
