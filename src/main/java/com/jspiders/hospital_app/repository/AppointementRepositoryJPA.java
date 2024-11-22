package com.jspiders.hospital_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.hospital_app.entity.AppointmentRecord;

public interface AppointementRepositoryJPA extends JpaRepository<AppointmentRecord, Integer>{

}
