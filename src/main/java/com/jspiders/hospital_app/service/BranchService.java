package com.jspiders.hospital_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspiders.hospital_app.dao.BranchDao;
import com.jspiders.hospital_app.dao.HospitalDao;
import com.jspiders.hospital_app.entity.Branch;
import com.jspiders.hospital_app.entity.Hospital;
import com.jspiders.hospital_app.entity.Patient;
import com.jspiders.hospital_app.util.ResponseStructure;

@Service
public class BranchService {

	@Autowired
	private HospitalDao dao;

	@Autowired
	private BranchDao bdao;


	// to save branch
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(int hospital_id, Branch branch) {
		Hospital hospital = dao.findHospitalById(hospital_id);
		if (hospital.getBranchs() == null) {
			ArrayList<Branch> al = new ArrayList<>();
			al.add(branch);
			hospital.setBranchs(al);
		} else {
			bdao.saveBranch(branch);
			hospital.getBranchs().add(branch);

		}
		dao.saveHospital(hospital);
		ResponseStructure<Branch> responseStructure = new ResponseStructure<>();
		if (branch != null) {
			responseStructure.setData(branch);
			responseStructure.setMessage("success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setData(null);
			responseStructure.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		}
	}

	
	// to find branch
	public ResponseEntity<ResponseStructure<Branch>> findBranchById(int id) {
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
		Branch branch = bdao.findBranchById(id);
		if (branch != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(branch);
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Branch details not found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		}
	}

	// to update branch
	public ResponseEntity<ResponseStructure<Branch>> updateBranchById(int id, Branch branch) {
		ResponseStructure<Branch> responseStructure = new ResponseStructure<>();
		Branch b = bdao.updateBrachById(id, branch);
		if (b != null) {
			branch.setBranch_id(b.getBranch_id());
			responseStructure.setData(branch);
			responseStructure.setMessage("success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage("Not modified");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		}
	}

	// to delete branch
	public ResponseEntity<ResponseStructure<Branch>> deleteBranchById(Integer id) {
		ResponseStructure<Branch> responseStructure = new ResponseStructure<>();

		List<Hospital> hospital = dao.findAllHospital();
		for (Hospital h : hospital) {
			if (h.getBranchs().removeIf(branch -> branch.getBranch_id().equals(id)));
		}

		boolean value = bdao.deleteBranchById(id);
		if (value) {
			responseStructure.setData(null);
			responseStructure.setMessage("success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_IMPLEMENTED.value());
			responseStructure.setMessage("Branch not deleted");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Branch>>(responseStructure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<List<Patient>>> getPatientsFromBranchId(int id) {
		ResponseStructure<List<Patient>> responseStructure = new ResponseStructure<>();
		List<Patient> patients = bdao.findAllPatientFromBranchId(id);
		if (patients != null) {
			responseStructure.setData(patients);
			responseStructure.setMessage("success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Patient>>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage("not found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<List<Patient>>>(responseStructure, HttpStatus.OK);
		}
	}

	
}
















