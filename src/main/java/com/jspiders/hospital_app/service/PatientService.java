package com.jspiders.hospital_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspiders.hospital_app.dao.BranchDao;
import com.jspiders.hospital_app.dao.PatientDao;
import com.jspiders.hospital_app.entity.Branch;
import com.jspiders.hospital_app.entity.Patient;
import com.jspiders.hospital_app.util.ResponseStructure;

@Service
public class PatientService {

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private BranchDao branchDao;

	//to save patient
	public ResponseEntity<ResponseStructure<Patient>> savePatient(int id, Patient patient) {
		Branch branch = branchDao.findBranchById(id);

		if (branch.getPatient() == null) {
			ArrayList<Patient> al = new ArrayList<>();
			al.add(patient);
			branch.setPatient(al);
		} else {
			patientDao.savePatient(patient);
			branch.getPatient().add(patient);
		}
		ResponseStructure<Patient> responseStructure = new ResponseStructure<>();
		branchDao.saveBranch(branch);
		responseStructure.setData(patient);
		responseStructure.setMessage("success");
		responseStructure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Patient>>(responseStructure, HttpStatus.OK);
	}

	//to find patient
	public ResponseEntity<ResponseStructure<Patient>> findPatientById(int id) {
		ResponseStructure<Patient> responseStructure = new ResponseStructure<>();
		Patient patient = patientDao.findPatientById(id);
		if (patient != null) {
			responseStructure.setData(patient);
			responseStructure.setMessage("success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Patient>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}

	//to update patient
	public ResponseEntity<ResponseStructure<Patient>> updatePatientById(int id, Patient patient) {
		ResponseStructure<Patient> responseStructure = new ResponseStructure<>();
		Patient p = patientDao.findPatientById(id);
		if (p != null) {
			patient.setPatient_id(p.getPatient_id());
			patientDao.savePatient(patient);
			responseStructure.setData(patient);
			responseStructure.setMessage("success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Patient>>(responseStructure, HttpStatus.OK );
		} else
			throw new NullPointerException();
	}
	
	//to delete patient

	public ResponseEntity<ResponseStructure<Patient>> deletePatientById(int id) {
		ResponseStructure<Patient> responseStructure = new ResponseStructure<>();
		Patient p = patientDao.findPatientById(id);
		if (p != null) {
			patientDao.deletePatient(id);
			responseStructure.setData(null);
			responseStructure.setMessage("success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Patient>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}
	
	public ResponseEntity<ResponseStructure<List<Patient>>> findPatientByName(String name)
	{
		ResponseStructure<List<Patient>> responseStructure=new ResponseStructure<>();
		List<Patient> patients=patientDao.findAllPatients(name);
		if(patients!=null)
		{
			responseStructure.setData(patients);
			responseStructure.setMessage("success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Patient>>>(responseStructure,HttpStatus.OK);
		}
		else
		{
			responseStructure.setData(null);
			responseStructure.setMessage("no data found for given name"+name);
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Patient>>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}

}
