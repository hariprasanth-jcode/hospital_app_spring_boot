package com.jspiders.hospital_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspiders.hospital_app.entity.AppointmentRecord;
import com.jspiders.hospital_app.entity.Prescription;
import com.jspiders.hospital_app.repository.AppointementRepositoryJPA;

@Repository
public class AppointementDao {

	@Autowired
	private AppointementRepositoryJPA appointementRepositoryJPA;
	
	public AppointmentRecord saveAppointement(AppointmentRecord appointement)
	{
		return appointementRepositoryJPA.save(appointement);
	}
	
	public AppointmentRecord findByAppointmentById(int id)
	{
		Optional<AppointmentRecord> opt=appointementRepositoryJPA.findById(id);
		return opt.isPresent()?opt.get():null;
	}
	
	public AppointmentRecord updateAppointementRecord(int id,AppointmentRecord record)
	{
		AppointmentRecord app=findByAppointmentById(id);
		if(app!=null)
		{
			record.setAppointment_id(app.getAppointment_id());
			return appointementRepositoryJPA.save(record);
		}
		else
			return  null;
	}
	
	public boolean deleteAppointment(int id)
	{
		AppointmentRecord app=findByAppointmentById(id);
		if(app!=null)
		{
			appointementRepositoryJPA.delete(app);
			return true;
		}
		return false;
	}
	
	//getPrescrption list by appointment id
	public Prescription findPrescriptionByAppointmentId(int id)
	{
		AppointmentRecord appointment=findByAppointmentById(id);
		return appointment!=null?appointment.getPrescription():null;
	}
}
