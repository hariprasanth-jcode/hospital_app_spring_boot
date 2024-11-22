package com.jspiders.hospital_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspiders.hospital_app.dao.MedicalItemsDao;
import com.jspiders.hospital_app.dao.PrescriptionDao;
import com.jspiders.hospital_app.entity.MedicalItems;
import com.jspiders.hospital_app.entity.Prescription;
import com.jspiders.hospital_app.util.ResponseStructure;

@Service
public class MedicalItemsService {
	
	@Autowired
	private MedicalItemsDao medicalItemsDao;

	@Autowired
	private  PrescriptionDao prescriptionDao;
	
	//to save
	public ResponseEntity<ResponseStructure<MedicalItems>> saveMedicalItems(int id,MedicalItems medicalItems) {
		ResponseStructure<MedicalItems> responseStructure = new ResponseStructure<>();
		Prescription  prescription=prescriptionDao.findPrescriptionById(id);
		if (medicalItems != null) {
			medicalItemsDao.saveMedicalItems(medicalItems);
			if(prescription!=null)
			{
				if(prescription.getItems()==null)
				{
				ArrayList<MedicalItems> al=new ArrayList<>();
				al.add(medicalItems);
				prescription.setItems(al);
				prescriptionDao.savePrescription(prescription);
				}
				else
				{
					prescription.getItems().add(medicalItems);
					prescriptionDao.savePrescription(prescription);
				}
			}
			responseStructure.setData(medicalItems);
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<MedicalItems>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}
	
	
	//to find
	public ResponseEntity<ResponseStructure<MedicalItems>> findyMedicalItemsById(int id) {
		ResponseStructure<MedicalItems> responseStructure = new ResponseStructure<>();
		MedicalItems medicalItems=medicalItemsDao.findMedicalItemsById(id);
		
		if (medicalItems!= null) {
			responseStructure.setData(medicalItems);
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<MedicalItems>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}
	
	//to update
	public ResponseEntity<ResponseStructure<MedicalItems>> updateMedicalItemsById(int id,MedicalItems medicalItems) {
		ResponseStructure<MedicalItems> responseStructure = new ResponseStructure<>();
		MedicalItems m=medicalItemsDao.findMedicalItemsById(id);
		
		if (m!= null) {
			medicalItems.setId(m.getId());
			medicalItemsDao.saveMedicalItems(medicalItems);
			responseStructure.setData(medicalItems);
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<MedicalItems>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}
	
	public ResponseEntity<ResponseStructure<MedicalItems>> deleteMedicalItemsById(int id) {
		ResponseStructure<MedicalItems> responseStructure = new ResponseStructure<>();
		MedicalItems medicalItems=medicalItemsDao.findMedicalItemsById(id);
		if (medicalItems!= null) {
			delete(id);
			medicalItemsDao.deleteMedicalItems(id);
			responseStructure.setData(medicalItems);
			responseStructure.setMessage("Success");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<MedicalItems>>(responseStructure, HttpStatus.OK);
		} else
			throw new NullPointerException();
	}
	
	
	public void delete(int id)
	{
		List<Prescription> prescription=prescriptionDao.findAllPrescription();
		for(Prescription p:prescription) {
			List<MedicalItems> items=p.getItems();
			for(MedicalItems m:items)
			{
				if(m.getId()==id)
				{
					items.remove(m);
				}
					
			}
			
		}
	}
}

