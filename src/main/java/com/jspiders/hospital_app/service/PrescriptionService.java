package com.jspiders.hospital_app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspiders.hospital_app.dao.AppointementDao;
import com.jspiders.hospital_app.dao.PrescriptionDao;
import com.jspiders.hospital_app.entity.AppointmentRecord;
import com.jspiders.hospital_app.entity.Prescription;
import com.jspiders.hospital_app.util.ResponseStructure;

@Service
public class PrescriptionService {

	@Autowired
	private PrescriptionDao prescriptionDao;
	
	@Autowired
	private AppointementDao appointementDao;
	
	public ResponseEntity<ResponseStructure<Prescription>> savePrescription(int id,Prescription prescription) {
		ResponseStructure<Prescription> responseStructure = new ResponseStructure<>();
		AppointmentRecord appointmentRecord=appointementDao.findByAppointmentById(id);
		System.out.println("hello");
		if (prescription != null) {
			prescriptionDao.savePrescription(prescription);	
			appointmentRecord.setPrescription(prescription);
			appointementDao.saveAppointement(appointmentRecord);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(prescription);
			
			return new ResponseEntity<ResponseStructure<Prescription>>(responseStructure, HttpStatus.OK);
		} else
		{
			
			throw new NullPointerException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Prescription>> findPrescriptionById(int id) {
		ResponseStructure<Prescription> responseStructure = new ResponseStructure<>();
		Prescription prescription=prescriptionDao.findPrescriptionById(id);
		if ( prescription != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(prescription);
			return new ResponseEntity<ResponseStructure<Prescription>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}
	
	public ResponseEntity<ResponseStructure<Prescription>> updatePrescriptionById(int id,Prescription prescription) {
		ResponseStructure<Prescription> responseStructure = new ResponseStructure<>();
		Prescription p=prescriptionDao.findPrescriptionById(id);
		if ( p != null) {
			prescription.setPrescrpiton_id(p.getPrescrpiton_id());
			prescriptionDao.savePrescription(prescription);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(prescription);
			return new ResponseEntity<ResponseStructure<Prescription>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}
	
	public ResponseEntity<ResponseStructure<Prescription>> deletePrescriptionById(int id) {
		ResponseStructure<Prescription> responseStructure = new ResponseStructure<>();
		Prescription prescription=prescriptionDao.findPrescriptionById(id);
		if ( prescription != null) {
			prescriptionDao.deletePrescription(id);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(prescription);
			return new ResponseEntity<ResponseStructure<Prescription>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}
	
}
