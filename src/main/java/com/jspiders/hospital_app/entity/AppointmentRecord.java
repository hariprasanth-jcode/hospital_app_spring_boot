package com.jspiders.hospital_app.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppointmentRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointment_id;
	
	private String doctorName;
	private int consultation_fee;
	
	@UpdateTimestamp
	private LocalDateTime creationTime;
	
	@UpdateTimestamp
	private LocalDateTime endTime;
	
	@OneToOne
	private Prescription prescription;
}
