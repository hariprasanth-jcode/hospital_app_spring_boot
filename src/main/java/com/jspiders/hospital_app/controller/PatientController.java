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

import com.jspiders.hospital_app.dao.PatientDao;
import com.jspiders.hospital_app.entity.AppointmentRecord;
import com.jspiders.hospital_app.entity.Branch;
import com.jspiders.hospital_app.entity.Patient;
import com.jspiders.hospital_app.service.PatientService;
import com.jspiders.hospital_app.util.ResponseStructure;

@RestController
public class PatientController {

	@Autowired
	private PatientDao patientDao;
	
	@Autowired
	private PatientService patientService;

	@PostMapping("savePatient")
	public ResponseEntity<ResponseStructure<Patient>> savePatinet(@RequestParam int id, @RequestBody Patient patient) {
		return patientService.savePatient(id,patient);
	}

	@GetMapping("/getPatient")
	public ResponseEntity<ResponseStructure<Patient>> findPatientById(@RequestParam int id) {
		return patientService.findPatientById(id);
	}

	@PutMapping("/updatePatient")
	public ResponseEntity<ResponseStructure<Patient>> updatePatient(@RequestParam int id, @RequestBody Patient patinet) {
		return patientService.updatePatientById(id, patinet);
	}

	@DeleteMapping("/deletePatient")
	public ResponseEntity<ResponseStructure<Patient>> deletePatient(@RequestParam int id) {
		return patientService.deletePatientById(id);
	}

	@GetMapping("/getBranchesfrompatient")
	public List<Branch> getBranches(@RequestParam int id) {
		return patientDao.findAllBrachesByPatientId(id);
	}

	@GetMapping("/getAppointments")
	public List<AppointmentRecord> getAppointement(@RequestParam int id) {
		return patientDao.findAllAppointmentRecords(id);
	}
	
	@GetMapping("/getPatientByName")
	public ResponseEntity<ResponseStructure<List<Patient>>> getPatients(@RequestParam String name)
	{
		return patientService.findPatientByName(name);
	}

}
