package com.jspiders.hospital_app.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MedicalItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double cost;
	
	@CreationTimestamp
	private LocalDateTime manufacturingDate;
	
	@UpdateTimestamp
	private LocalDateTime expiryDate;
	
	private boolean morning;
	private boolean afternoon;
	private boolean night;
	
}
