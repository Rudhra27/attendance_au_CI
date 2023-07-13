package com.CI.attendance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CI.attendance.Model.*;
import com.CI.attendance.Repository.RegulationRepository;
import com.CI.attendance.Service.*;

import java.util.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('Admin')")
public class AdminController {
	
	
	@Autowired
	RegulationService regService;
	
	@Autowired
	ProgrammeOutcomeService proService;
	
	@Autowired
	CourseOutcomeService coutcomeService;
	
	@Autowired
	CourseService courseService;
	
	// ----------------Regulation CRUD Operations----------------
	@GetMapping("/viewAllRegulations")  
	public List<MetaRegulation> getAllRegulation()   
	{  
		return regService.getAllRegulations();  
	}  
	
	@GetMapping("/viewRegulation/{regulationid}")  
	public MetaRegulation getRegulation(@PathVariable("regulationid") int regulationid)   
	{  
	return  regService.getRegulation(regulationid);
	}  
	 
	@DeleteMapping("/deleteRegulation/{regulationid}")  
	public void deleteRegulation(@PathVariable("regulationid") int regulationid)   
	{  
		regService.deleteRegulation(regulationid);
	}  
	
	@PostMapping("/addRegulation")
    public MetaRegulation addRegulation(@RequestBody @Valid MetaRegulation regulation) {
		
        return regService.addRegulation(regulation);
    }
	
	@PutMapping("/updateRegulation/{id}")
	public MetaRegulation updateRegulation(@PathVariable int id, @RequestBody MetaRegulation regulation) {
		
		    return regService.updateRegulation(id, regulation);
	}
	
	// ----------------ProgrammeOutcome CRUD Operations----------------
		
	@GetMapping("/viewAllProgrammeOutcomes")  
	public List<MetaProgrammeOutcome> getAllProgrammeOutcomes()   
	{  
		return proService.getAllProgrammeOutcomes();  
	}  
	

	@GetMapping("/viewProgrammeOutcome/{programmeOutcomeId}")  
	public MetaProgrammeOutcome getProgrammeOutcome(@PathVariable("programmeOutcomeId") int programmeOutcomeId)   
	{  
	return  proService.getProgrammeOutcome(programmeOutcomeId);
	}  
	
	@DeleteMapping("/deleteProgrammeOutcome/{programmeOutcomeId}")  
	public void deleteProgrammeOutcome(@PathVariable("programmeOutcomeId") int programmeOutcomeId)   
	{  
		proService.deleteProgrammeOutcome(programmeOutcomeId);
	} 
	
	@PostMapping("/addProgrammeOutcome")
    public MetaProgrammeOutcome addProgrammeOutcome(@RequestBody @Valid MetaProgrammeOutcome programmeOutcome) {
		
        return proService.addProgrammeOutcome(programmeOutcome);
    }
	
	@PutMapping("/updateProgrammeOutcome/{id}")
	public MetaProgrammeOutcome updateProgrammeOutcome(@PathVariable int id, @RequestBody MetaProgrammeOutcome programmeOutcome) {
		
		    return proService.updateProgrammeOutcome(id, programmeOutcome);
	}
	
	
	// ----------------CourseOutcome CRUD Operations----------------
	
	@GetMapping("/viewAllCourseOutcomes")  
	public List<MetaCourseOutcome> getAllCourseOutcomes()   
	{  
		return coutcomeService.getAllCourseOutcomes();  
	} 
	
	@GetMapping("/viewCourseOutcome/{courseOutcomeId}")  
	public MetaCourseOutcome getCourseOutcome(@PathVariable("courseOutcomeId") int courseOutcomeId)   
	{  
	return  coutcomeService.getCourseOutcome(courseOutcomeId);
	} 
	
	@DeleteMapping("/deleteCourseOutcome/{courseOutcomeId}")  
	public void deleteCourseOutcome(@PathVariable("courseOutcomeId") int courseOutcomeId)   
	{  
		coutcomeService.deleteCourseOutcome(courseOutcomeId);
	} 
	
	@PostMapping("/addCourseOutcome")
    public MetaCourseOutcome addCourseOutcome(@RequestBody @Valid MetaCourseOutcome courseOutcome) {
		
        return coutcomeService.addCourseOutcome(courseOutcome);
    }
	
	@PutMapping("/updateCourseOutcome/{id}")
	public MetaCourseOutcome updateCourseOutcome(@PathVariable int id, @RequestBody MetaCourseOutcome courseOutcome) {
		
		    return coutcomeService.updateCourseOutcome(id, courseOutcome);
	}
	
	//----------------COURSE CRUD Operations----------------
	
	@GetMapping("/viewAllCourses")  
	public List<MetaCourse> getAllCourses()   
	{  
		return courseService.getAllCourses();  
	} 
	
	@GetMapping("/viewCourse/{courseId}")  
	public MetaCourse getCourse(@PathVariable("courseId") int courseId)   
	{  
		return  courseService.getCourse(courseId);
	} 
	
	
	@PostMapping(value= "/addCourse", consumes = {"application/json"})
    public MetaCourse addCourse(@RequestBody @Valid MetaCourse course) {
		System.out.println("course.getRegulation() "+course.getRegulation());
		//System.out.println("course.getCourse_outcome_map() "+course.getCourse_outcome_map().getCourseOutcomeIds().toString());
        return courseService.addCourse(course);
    }
	
	@DeleteMapping("/deleteCourse/{courseId}")  
	public void deleteCourse(@PathVariable("courseId") int courseId)   
	{  
		courseService.deleteCourse(courseId);
	} 
	
	@PutMapping("/updateCourse/{id}")
	public MetaCourse updateCourse(@PathVariable int id, @RequestBody MetaCourse course) {
		
		    return courseService.updateCourse(id, course);
	}
	
	
}
