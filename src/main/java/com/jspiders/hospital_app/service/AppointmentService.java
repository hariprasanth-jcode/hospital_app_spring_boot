package com.jspiders.hospital_app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspiders.hospital_app.dao.AppointementDao;
import com.jspiders.hospital_app.dao.PatientDao;
import com.jspiders.hospital_app.entity.AppointmentRecord;
import com.jspiders.hospital_app.entity.Patient;
import com.jspiders.hospital_app.util.ResponseStructure;

@Service
public class AppointmentService {

	@Autowired
	private AppointementDao appointementDao;
	
	@Autowired
	private PatientDao patientDao;

	public ResponseEntity<ResponseStructure<AppointmentRecord>> findAppointementById(int id) {
		ResponseStructure<AppointmentRecord> responseStructure = new ResponseStructure<>();
		AppointmentRecord record = appointementDao.findByAppointmentById(id);
		if (record != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(record);
			return new ResponseEntity<ResponseStructure<AppointmentRecord>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}

	public ResponseEntity<ResponseStructure<AppointmentRecord>> saveAppointement(int id,AppointmentRecord record) {
		ResponseStructure<AppointmentRecord> responseStructure = new ResponseStructure<>();
		Patient patient=patientDao.findPatientById(id);
		if(patient.getRecord()==null)
		{
			ArrayList<AppointmentRecord> al=new ArrayList<>();
			al.add(record);
			patient.setRecord(al);
		}
		else
		{
			appointementDao.saveAppointement(record);
			patient.getRecord().add(record);
			
		}
		if (record != null) {
			patientDao.savePatient(patient);
			appointementDao.saveAppointement(record);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(record);
			return new ResponseEntity<ResponseStructure<AppointmentRecord>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}

	public ResponseEntity<ResponseStructure<AppointmentRecord>> updateAppointmentById(int id,
			AppointmentRecord record) {
		ResponseStructure<AppointmentRecord> responseStructure = new ResponseStructure<>();
		AppointmentRecord r = appointementDao.findByAppointmentById(id);
		System.out.println("hello");
		if (r != null) {
			record.setAppointment_id(r.getAppointment_id());
			appointementDao.saveAppointement(record);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(record);
			return new ResponseEntity<ResponseStructure<AppointmentRecord>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}

	public ResponseEntity<ResponseStructure<AppointmentRecord>> deleteAppointmentById(int id) {
		ResponseStructure<AppointmentRecord> responseStructure = new ResponseStructure<>();
		AppointmentRecord record = appointementDao.findByAppointmentById(id);
		if (record != null) {
			appointementDao.deleteAppointment(id);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<AppointmentRecord>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}

}
