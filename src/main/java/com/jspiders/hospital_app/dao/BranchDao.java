package com.jspiders.hospital_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.hospital_app.entity.Address;
import com.jspiders.hospital_app.entity.Branch;
import com.jspiders.hospital_app.entity.Patient;
import com.jspiders.hospital_app.repository.BranchRepositoryJPA;

@Repository
public class BranchDao {

	@Autowired
	private BranchRepositoryJPA branchRepositoryJPA;

	public Branch saveBranch(Branch branch) {
		return branchRepositoryJPA.save(branch);
	}

	public Branch findBranchById(int id) {
		Optional<Branch> opt = branchRepositoryJPA.findById(id);
		System.out.println("asdfa");
		return opt.isPresent() ? opt.get() : null;
	}

	public Branch updateBrachById(int id, Branch branch) {
		Branch b = findBranchById(id);
		if (b != null) {
			branch.setBranch_id(b.getBranch_id());
			return branchRepositoryJPA.save(branch);
		}
		return null;
	}

	// delete Branch By Id
	public Boolean deleteBranchById(int id) {
		Branch branch = findBranchById(id);
		if (branch != null) {
			branchRepositoryJPA.delete(branch);
			return true;
		} else
			return false;
	}

	// to return the address
	public Address findByAddress(int id) {
		Branch branch = findBranchById(id);
		return branch != null ? branch.getAddress() : null;
	}

	// to return the list of patients
	public List<Patient> findAllPatientFromBranchId(int id) {
		Branch branch = findBranchById(id);
		return branch != null ? branch.getPatient() : null;
	}
}
