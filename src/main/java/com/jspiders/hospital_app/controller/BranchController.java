package com.jspiders.hospital_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.hospital_app.entity.Address;
import com.jspiders.hospital_app.entity.Branch;
import com.jspiders.hospital_app.entity.Patient;
import com.jspiders.hospital_app.service.AddressService;
import com.jspiders.hospital_app.service.BranchService;
import com.jspiders.hospital_app.util.ResponseStructure;

@RestController
public class BranchController {

	@Autowired
	private BranchService branchService;

	@Autowired
	private AddressService addressService;

	@PostMapping("/saveBranch/{id}")
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@PathVariable int id, @RequestBody Branch branch) {
		return branchService.saveBranch(id, branch);
	}

	@GetMapping("/getBranch")
	public ResponseEntity<ResponseStructure<Branch>> findBranchById(@RequestParam int id) {
		return branchService.findBranchById(id);
	}

	@PutMapping("/updateBranch")
	public ResponseEntity<ResponseStructure<Branch>> updateBranchById(@RequestParam int id,
			@RequestBody Branch branch) {
		return branchService.updateBranchById(id, branch);
	}

	@DeleteMapping("/deleteBranch")
	public ResponseEntity<ResponseStructure<Branch>> deleteBranchById(@RequestParam int id) {
		return branchService.deleteBranchById(id);
	}

	@GetMapping("/getAddressById")
	public ResponseEntity<ResponseStructure<Address>> findByAddress(@RequestParam int id) {
		return addressService.findAddressById(id);
	}

	@GetMapping("/getPatientsFromBranchId")
	public ResponseEntity<ResponseStructure<List<Patient>>> findAllPatients(@RequestParam int id) {
		return branchService.getPatientsFromBranchId(id);
	}

}
