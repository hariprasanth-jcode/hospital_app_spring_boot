package com.jspiders.hospital_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.hospital_app.entity.Branch;

public interface BranchRepositoryJPA extends JpaRepository<Branch, Integer>{
	
//	List<Patient> findByPatient(int id);

}
