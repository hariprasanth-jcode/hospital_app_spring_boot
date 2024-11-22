package com.jspiders.hospital_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.hospital_app.entity.MedicalItems;

public interface MedicalItemsRepositoryJPA extends JpaRepository<MedicalItems, Integer>{

}
