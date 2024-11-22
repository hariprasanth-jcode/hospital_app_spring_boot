package com.jspiders.hospital_app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer branch_id;
	private String branch_name;
	private String branch_state;
	private int branch_pincode;
	private long branch_phone;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "branch_id"), 
	inverseJoinColumns = @JoinColumn(name = "patient_id"))
	@JsonIgnore
	private List<Patient> patient;

	@OneToOne
	private Address address;

}
