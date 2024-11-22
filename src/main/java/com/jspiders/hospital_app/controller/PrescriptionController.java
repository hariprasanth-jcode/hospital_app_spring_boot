package com.jspiders.hospital_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.hospital_app.entity.Prescription;
import com.jspiders.hospital_app.service.PrescriptionService;
import com.jspiders.hospital_app.util.ResponseStructure;

@RestController
public class PrescriptionController {

	@Autowired
	private PrescriptionService prescriptionService;

	@PostMapping("/savePrescription")
	public ResponseEntity<ResponseStructure<Prescription>> saveAppointement(@RequestParam int id,
			@RequestBody Prescription prescription) {
		return prescriptionService.savePrescription(id, prescription);
	}

	@GetMapping("/getPrescription")
	public ResponseEntity<ResponseStructure<Prescription>> finPrescriptionRecord(@RequestParam int id) {
		return prescriptionService.findPrescriptionById(id);
	}

	@PutMapping("/updatePrescription")
	public ResponseEntity<ResponseStructure<Prescription>> updatePrescription(@RequestParam int id,
			@RequestBody Prescription Prescription) {
		return prescriptionService.updatePrescriptionById(id, Prescription);
	}

	@DeleteMapping("/deletePrescription")
	public ResponseEntity<ResponseStructure<Prescription>> deletePrescription(@RequestParam int id) {
		return prescriptionService.deletePrescriptionById(id);
	}
}
