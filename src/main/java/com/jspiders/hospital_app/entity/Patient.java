package com.jspiders.hospital_app.entity;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patient_id;
	private String patientName;
	private int patient_age;
	private long patient_phone;
	private String patient_email;

	@OneToMany
	private List<AppointmentRecord> record;

	@ManyToMany(mappedBy = "patient",fetch = FetchType.LAZY)
	private List<Branch> branches;
	
}
