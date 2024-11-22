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

import com.jspiders.hospital_app.entity.MedicalItems;
import com.jspiders.hospital_app.service.MedicalItemsService;
import com.jspiders.hospital_app.util.ResponseStructure;

@RestController
public class MedicalItemsController {

	@Autowired
	private MedicalItemsService medicalService;

	@PostMapping("/saveMedicalItems")
	public ResponseEntity<ResponseStructure<MedicalItems>> saveAppointement(@RequestParam int id,
			@RequestBody MedicalItems medicalItems) {
		return medicalService.saveMedicalItems(id, medicalItems);
	}

	@GetMapping("/getMedicalItems")
	public ResponseEntity<ResponseStructure<MedicalItems>> finMedicalItemsRecord(@RequestParam int id) {
		return medicalService.findyMedicalItemsById(id);
	}

	@PutMapping("/updateMedicalItems")
	public ResponseEntity<ResponseStructure<MedicalItems>> updateMedicalItems(@RequestParam int id,
			@RequestBody MedicalItems MedicalItems) {
		return medicalService.updateMedicalItemsById(id, MedicalItems);
	}

	@DeleteMapping("/deleteMedicalItems")
	public ResponseEntity<ResponseStructure<MedicalItems>> deleteMedicalItems(@RequestParam int id) {
		return medicalService.deleteMedicalItemsById(id);
	}
}
