package com.jspiders.hospital_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.hospital_app.entity.MedicalItems;
import com.jspiders.hospital_app.repository.MedicalItemsRepositoryJPA;

@Repository
public class MedicalItemsDao {

	@Autowired
	private MedicalItemsRepositoryJPA jpa;
	
	public MedicalItems saveMedicalItems(MedicalItems items)
	{
		return jpa.save(items);
	}
	
	public MedicalItems findMedicalItemsById(int id)
	{
		Optional<MedicalItems> item=jpa.findById(id);
		return item.isPresent()?item.get():null;
	}
	
	public MedicalItems updateMedicalItems(int id,MedicalItems medicalItems)
	{
		MedicalItems items=findMedicalItemsById(id);
		if(items!=null)
		{
			medicalItems.setId(items.getId());
			return jpa.save(medicalItems);
		}
		return null;
	}
	
	public boolean deleteMedicalItems(int id)
	{
		MedicalItems item=findMedicalItemsById(id);
		if(item!=null)
		{
			jpa.delete(item);
			return true;
		}
		return false;
	}
	
}
