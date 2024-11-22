package com.jspiders.hospital_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspiders.hospital_app.entity.Address;

public interface AddressRepositoryJPA extends JpaRepository<Address, Integer>{

}
