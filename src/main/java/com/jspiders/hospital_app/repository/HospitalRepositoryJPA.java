package com.jspiders.hospital_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.hospital_app.entity.Hospital;

public interface HospitalRepositoryJPA extends JpaRepository<Hospital, Integer>{

}
