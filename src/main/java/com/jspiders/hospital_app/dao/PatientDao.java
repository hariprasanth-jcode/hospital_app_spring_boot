package com.jspiders.hospital_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.hospital_app.entity.AppointmentRecord;
import com.jspiders.hospital_app.entity.Branch;
import com.jspiders.hospital_app.entity.Patient;
import com.jspiders.hospital_app.repository.PatientRepositoryJPA;

@Repository
public class PatientDao {

	@Autowired
	private PatientRepositoryJPA patientRepositoryJPA;

	public Patient savePatient(Patient Patient) {
		return patientRepositoryJPA.save(Patient);
	}

	public Patient findPatientById(int id) {
		Optional<Patient> opt = patientRepositoryJPA.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	public Patient updatePatient(int id, Patient patient) {
		Patient a = findPatientById(id);
		if (a != null) {
			patient.setPatient_id(a.getPatient_id());
			return patientRepositoryJPA.save(patient);
		} else
			return null;
	}

	public boolean deletePatient(int id) {
		Patient patient = findPatientById(id);
		if (patient != null) {
			patientRepositoryJPA.delete(patient);
			return true;
		} else
			return false;
	}

	// to get all branches
	public List<Branch> findAllBrachesByPatientId(int id) {
		Patient patient = findPatientById(id);
		return patient != null ? patient.getBranches() : null;
	}

	// to get all appointment for patient
	public List<AppointmentRecord> findAllAppointmentRecords(int id) {
		Patient patient = findPatientById(id);
		return patient != null ? patient.getRecord() : null;
	}

	public List<Patient> findAllPatients(String name) {
		List<Patient> patients = patientRepositoryJPA.findBypatientName(name);
		return patients;
	}

}
