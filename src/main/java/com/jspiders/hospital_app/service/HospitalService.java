package com.jspiders.hospital_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jspiders.hospital_app.dao.HospitalDao;
import com.jspiders.hospital_app.entity.Branch;
import com.jspiders.hospital_app.entity.Hospital;
import com.jspiders.hospital_app.util.ResponseStructure;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao hospitalDao;

	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(@RequestBody Hospital hospital) {
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<>();
		Hospital hospital2 = hospitalDao.saveHospital(hospital);

		if (hospital2 != null) {
			responseStructure.setData(hospital);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			return new ResponseEntity<ResponseStructure<Hospital>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setData(null);
			responseStructure.setMessage("Data not saved");
			responseStructure.setStatusCode(HttpStatus.NOT_MODIFIED.value());
			return new ResponseEntity<ResponseStructure<Hospital>>(responseStructure, HttpStatus.NOT_FOUND);

		}
	}

	public ResponseEntity<ResponseStructure<Hospital>> getHospitalById(int id) {
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<>();
		Hospital hospital = hospitalDao.findHospitalById(id);
		if (hospital != null) {
			responseStructure.setData(hospital);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			return new ResponseEntity<ResponseStructure<Hospital>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setData(null);
			responseStructure.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Hospital>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Hospital>> updateHospitalById(int id, Hospital hospital) {
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<>();
		Hospital h = hospitalDao.findHospitalById(id);
		if (h != null) {
			hospital.setHospital_id(h.getHospital_id());
			hospitalDao.saveHospital(hospital);
			responseStructure.setData(hospital);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			return new ResponseEntity<ResponseStructure<Hospital>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setData(null);
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
			return new ResponseEntity<ResponseStructure<Hospital>>(responseStructure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Hospital>> deleteHospitalById(int id) {

		Hospital hospital = hospitalDao.findHospitalById(id);
		if (hospital != null) {
			ResponseStructure<Hospital> responseStructure = new ResponseStructure<>();
			hospitalDao.deleteHospital(id);
			responseStructure.setData(null);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			return new ResponseEntity<ResponseStructure<Hospital>>(responseStructure, HttpStatus.OK);

		} else {
			throw new NullPointerException();
		}
	}

	public ResponseEntity<ResponseStructure<List<Branch>>> getBrachesHospitalById(int id) {
		ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<>();

		Hospital hospital = hospitalDao.findHospitalById(id);
		if (hospital != null) {
			List<Branch> branches = hospital.getBranchs();

			responseStructure.setData(branches);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			return new ResponseEntity<ResponseStructure<List<Branch>>>(responseStructure, HttpStatus.OK);

		} else {
			responseStructure.setData(null);
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
			return new ResponseEntity<ResponseStructure<List<Branch>>>(responseStructure, HttpStatus.OK);
		}
	}

}
