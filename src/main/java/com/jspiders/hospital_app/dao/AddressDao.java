package com.jspiders.hospital_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.hospital_app.entity.Address;
import com.jspiders.hospital_app.repository.AddressRepositoryJPA;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepositoryJPA addressRepositoryJPA;

	public Address saveAddress(Address address) {
		return addressRepositoryJPA.save(address);
	}

	public Address findAddressById(int id) {
		Optional<Address> address = addressRepositoryJPA.findById(id);
		return address.isPresent() ? address.get() : null;
	}

	public Address updateAddress(int id, Address address) {
		Address a = findAddressById(id);
		if (a != null) {
			address.setId(a.getId());
			return addressRepositoryJPA.save(address);
		} else
			return null;
	}

	public boolean deleteAddress(int id) {
		Address address = findAddressById(id);
		if (address != null) {
			addressRepositoryJPA.delete(address);
			return true;
		} else
			return false;
	}
}
