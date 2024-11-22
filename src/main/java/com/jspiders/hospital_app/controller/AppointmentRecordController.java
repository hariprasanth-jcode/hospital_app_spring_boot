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

import com.jspiders.hospital_app.entity.AppointmentRecord;
import com.jspiders.hospital_app.service.AppointmentService;
import com.jspiders.hospital_app.util.ResponseStructure;

@RestController
public class AppointmentRecordController {

	@Autowired
	private AppointmentService appointementService;

	@PostMapping("/saveAppointment")
	public ResponseEntity<ResponseStructure<AppointmentRecord>> saveAppointement(@RequestParam int id,
			@RequestBody AppointmentRecord appointmentRecord) {
		return appointementService.saveAppointement(id, appointmentRecord);
	}

	@GetMapping("/getAppointment")
	public ResponseEntity<ResponseStructure<AppointmentRecord>> finAppointmentRecord(@RequestParam int id) {
		return appointementService.findAppointementById(id);
	}

	@PutMapping("/updateAppointment")
	public ResponseEntity<ResponseStructure<AppointmentRecord>> updateAppointment(@RequestParam int id,
			@RequestBody AppointmentRecord appointment) {
		return appointementService.updateAppointmentById(id, appointment);
	}

	@DeleteMapping("/deleteMapping")
	public ResponseEntity<ResponseStructure<AppointmentRecord>> deleteAppointment(@RequestParam int id) {
		return appointementService.deleteAppointmentById(id);
	}
}
