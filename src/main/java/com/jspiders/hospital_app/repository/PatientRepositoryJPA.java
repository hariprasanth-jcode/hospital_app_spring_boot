package com.jspiders.hospital_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.hospital_app.entity.Patient;

public interface PatientRepositoryJPA extends JpaRepository<Patient, Integer>{

	public List<Patient> findBypatientName(String name);
}
