package com.jspiders.hospital_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.hospital_app.entity.Branch;
import com.jspiders.hospital_app.entity.Hospital;
import com.jspiders.hospital_app.repository.HospitalRepositoryJPA;

@Repository
public class HospitalDao {

	@Autowired
	private HospitalRepositoryJPA hospitalRepositoryJPA;

	public Hospital saveHospital(Hospital hospital) {
		return hospitalRepositoryJPA.save(hospital);
	}

	public Hospital findHospitalById(int id) {
		Optional<Hospital> opt = hospitalRepositoryJPA.findById(id);
		return opt.isPresent() ? opt.get() : null;

	}

	public Hospital updateHospital(int id, Hospital hospital) {
		Hospital h = findHospitalById(id);
		if (h != null) {
			hospital.setHospital_id(h.getHospital_id());
			return hospitalRepositoryJPA.save(hospital);
		} else
			return null;
	}

	
	public boolean deleteHospital(int id) {
		Hospital hospital = findHospitalById(id);
		if (hospital != null) {
			hospitalRepositoryJPA.delete(hospital);
			return true;
		} else
			return false;
	}

	public List<Branch> findAllBranches(int id) {
		Hospital hospital = findHospitalById(id);
		return (hospital!= null )? hospital.getBranchs() : null;
	}
	
	public List<Hospital> findAllHospital()
	{
		return hospitalRepositoryJPA.findAll();
	}
	
	
}
