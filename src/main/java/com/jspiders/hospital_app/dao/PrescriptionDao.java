package com.jspiders.hospital_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.hospital_app.entity.MedicalItems;
import com.jspiders.hospital_app.entity.Prescription;
import com.jspiders.hospital_app.repository.PrescriptionRepositoryJPA;

@Repository
public class PrescriptionDao {

	@Autowired
	private PrescriptionRepositoryJPA prescriptionRepositoryJPA;

	public Prescription savePrescription(Prescription prescrption) {
		return prescriptionRepositoryJPA.save(prescrption);
	}

	public Prescription findPrescriptionById(int id) {
		Optional<Prescription> prescription = prescriptionRepositoryJPA.findById(id);
		return prescription.isPresent() ? prescription.get() : null;
	}

	public Prescription updatePrescription(int id, Prescription prescription) {
		Prescription p = findPrescriptionById(id);
		if (p != null) {
			prescription.setPrescrpiton_id(p.getPrescrpiton_id());
			return prescriptionRepositoryJPA.save(prescription);
		}
		return null;
	}

	public boolean deletePrescription(int id) {
		Prescription prescription = findPrescriptionById(id);
		if (prescription != null) {
			prescriptionRepositoryJPA.delete(prescription);
			return true;
		} else
			return false;
	}

	public List<MedicalItems> findMedicalItemsByPrescriptionId(int id) {
		Prescription prescription = findPrescriptionById(id);
		return prescription != null ? prescription.getItems() : null;
	}
	
	public List<Prescription> findAllPrescription()
	{
		return prescriptionRepositoryJPA.findAll();
	}
}
