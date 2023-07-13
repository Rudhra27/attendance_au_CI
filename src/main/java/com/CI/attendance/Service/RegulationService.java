package com.CI.attendance.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CI.attendance.Model.MetaRegulation;
import com.CI.attendance.Repository.RegulationRepository;

@Service
public class RegulationService {
	
	@Autowired
	RegulationRepository regulationRepo;
	
	public List<MetaRegulation> getAllRegulations()   
	{  
		List<MetaRegulation> regulations = new ArrayList<MetaRegulation>();  
		regulationRepo.findAll().forEach(regulation -> regulations.add(regulation));  
		return regulations;   
	}  
	
	public MetaRegulation getRegulation(int regulationid)
	{
		return regulationRepo.findById(regulationid).get();
	}
	
	public void deleteRegulation(int regulationid)
	{
		regulationRepo.deleteById(regulationid);
	}
	
	public MetaRegulation addRegulation(MetaRegulation regulation)
	{
		return regulationRepo.save(regulation);
	}
	
	public MetaRegulation updateRegulation(int regulationid, MetaRegulation regulation)
	{
		MetaRegulation updatereg = regulationRepo.findById(regulationid).get();
		updatereg.setRegulation(regulation.getRegulation());
		return regulationRepo.save(updatereg);
	}

}
