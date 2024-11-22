package com.jspiders.hospital_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.hospital_app.entity.Branch;
import com.jspiders.hospital_app.entity.Hospital;
import com.jspiders.hospital_app.service.HospitalService;
import com.jspiders.hospital_app.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;
	
	


	@PostMapping("/saveHospital")
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(@RequestBody @Valid Hospital hospital) {
		return hospitalService.saveHospital(hospital);
	}

	@GetMapping("/getHospital")
	public ResponseEntity<ResponseStructure<Hospital>> findHospitalById(@RequestParam int id) {
		return hospitalService.getHospitalById(id);
	}

	@PutMapping("/updateHospital")
	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(@RequestParam int id,
			@RequestBody Hospital hospital) {
		return hospitalService.updateHospitalById(id, hospital);
	}

	@DeleteMapping("/deleteHospital")
	public ResponseEntity<ResponseStructure<Hospital>> deleteHospital(@RequestParam int id) {
		return hospitalService.deleteHospitalById(id);
	}

	@GetMapping("/getbranches")
	public ResponseEntity<ResponseStructure<List<Branch>>> getBranch(@RequestParam int id) {
		return hospitalService.getBrachesHospitalById(id);
	}
	
}
