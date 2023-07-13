package com.CI.attendance.Service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CI.attendance.Model.MetaProgrammeOutcome;
import com.CI.attendance.Repository.ProgrammeOutcomeRepository;

@Service
public class ProgrammeOutcomeService {
	
	@Autowired
	ProgrammeOutcomeRepository proRepository;

	public List<MetaProgrammeOutcome> getAllProgrammeOutcomes() {
		List<MetaProgrammeOutcome> programmeOutcomes = new ArrayList<MetaProgrammeOutcome>();  
		proRepository.findAll().forEach(programmeOutcome -> programmeOutcomes.add(programmeOutcome));  
		return programmeOutcomes;
	}

	public MetaProgrammeOutcome getProgrammeOutcome(int programmeOutcomeId) {
		return proRepository.findById(programmeOutcomeId).get();
	}

	public void deleteProgrammeOutcome(int programmeOutcomeId) {
		proRepository.deleteById(programmeOutcomeId);
		
	}

	public MetaProgrammeOutcome addProgrammeOutcome(@Valid MetaProgrammeOutcome programmeOutcome) {
		return proRepository.save(programmeOutcome);
	}

	public MetaProgrammeOutcome updateProgrammeOutcome(int id, MetaProgrammeOutcome programmeOutcome) {
		MetaProgrammeOutcome updatepro = proRepository.findById(id).get();
		updatepro.setProgrammeCode(programmeOutcome.getProgrammeCode());
		updatepro.setProgrammeOutcome(programmeOutcome.getProgrammeOutcome());
		return proRepository.save(updatepro);
	}

}
