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

import com.jspiders.hospital_app.entity.Address;
import com.jspiders.hospital_app.service.AddressService;
import com.jspiders.hospital_app.util.ResponseStructure;

@RestController
public class AddressController {


	@Autowired
	private AddressService addressService;
	
	@PostMapping("/saveAddress")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestParam int id,@RequestBody Address address) {
		return addressService.saveAddress(id,address);
	}

	@GetMapping("/getAddress")
	public ResponseEntity<ResponseStructure<Address>> findAddressById(@RequestParam int id) {
		return addressService.findAddressById(id);
	}

	@PutMapping("/updateAddress")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestParam int id,
			@RequestBody Address address) {
		return addressService.updateAddressById(id, address);
	}

	@DeleteMapping("/deleteAddress")
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(@RequestParam int id) {
		return addressService.deletAddressById(id);
	}
}
