package com.jspiders.hospital_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.hospital_app.entity.Prescription;

public interface PrescriptionRepositoryJPA extends JpaRepository<Prescription, Integer>{

}
