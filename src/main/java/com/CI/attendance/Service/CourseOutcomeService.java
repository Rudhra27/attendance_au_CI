package com.CI.attendance.Service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CI.attendance.Model.MetaCourseOutcome;
import com.CI.attendance.Repository.*;

@Service
public class CourseOutcomeService {

	@Autowired
	CourseOutcomeRepository coutcomeRepository;
	
	@Autowired
	CourseOutcomeMapRepository cmapRepository;
	public List<MetaCourseOutcome> getAllCourseOutcomes() {

		List<MetaCourseOutcome> courseOutcomes = new ArrayList<MetaCourseOutcome>();  
		coutcomeRepository.findAll().forEach(courseOutcome -> courseOutcomes.add(courseOutcome));  
		return courseOutcomes; 
	}
	public MetaCourseOutcome getCourseOutcome(int courseOutcomeId) {
		return coutcomeRepository.findById(courseOutcomeId).get();
	}
	public void deleteCourseOutcome(int courseOutcomeId) {
		coutcomeRepository.deleteById(courseOutcomeId);
		
	}
	public MetaCourseOutcome addCourseOutcome(@Valid MetaCourseOutcome courseOutcome) {
		return coutcomeRepository.save(courseOutcome);
	}
	public MetaCourseOutcome updateCourseOutcome(int id, MetaCourseOutcome courseOutcome) {
		MetaCourseOutcome updateOutcome = coutcomeRepository.findById(id).get();
		updateOutcome.setCourseOutcomeCode(courseOutcome.getCourseOutcomeCode());
		updateOutcome.setCourseOutcome(courseOutcome.getCourseOutcome());
		return coutcomeRepository.save(updateOutcome);
	}
	
	

}
