package com.jspiders.hospital_app.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hospital_id;
	private String hostpital_name;
	private String main_address;

	@Min(6000000)
	@Max(999999999)
//	@Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
	private long hospital_phone;

	
	private String hostimal_email;

	@UpdateTimestamp
	private LocalDateTime creationTime;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Branch> branchs;

}
