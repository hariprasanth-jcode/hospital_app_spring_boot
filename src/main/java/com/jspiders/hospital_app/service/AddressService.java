package com.jspiders.hospital_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspiders.hospital_app.dao.AddressDao;
import com.jspiders.hospital_app.dao.BranchDao;
import com.jspiders.hospital_app.entity.Address;
import com.jspiders.hospital_app.entity.Branch;
import com.jspiders.hospital_app.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private BranchDao branchDao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(int id, Address address) {
		Branch branch = branchDao.findBranchById(id);
		if (branch != null) {
			branch.setAddress(address);
		} else {
			throw new NullPointerException();
		}

		ResponseStructure<Address> responseStructure = new ResponseStructure<>();

		if (address != null) {
			addressDao.saveAddress(address);
			responseStructure.setData(address);
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("not found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Address>> findAddressById(int id) {
		ResponseStructure<Address> responseStructure = new ResponseStructure<>();
		Address address = addressDao.findAddressById(id);
		if (address != null) {
			responseStructure.setData(address);
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.OK);
		} else {
			System.out.println("sdghjk");
			throw new NullPointerException();
		}
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddressById(int id, Address address) {
		ResponseStructure<Address> responseStructure = new ResponseStructure<>();
		Address a = addressDao.findAddressById(id);
		if (a != null) {
			address.setId(a.getId());
			addressDao.saveAddress(address);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(address);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.OK);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not found");
			responseStructure.setData(null);
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.OK);
		}
	}

	public ResponseEntity<ResponseStructure<Address>> deletAddressById(int id) {
		ResponseStructure<Address> responseStructure = new ResponseStructure<>();
		Address address = addressDao.findAddressById(id);
		if (address != null) {
			addressDao.deleteAddress(id);
			responseStructure.setData(null);
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}
}
