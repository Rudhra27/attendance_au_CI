package com.CI.attendance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.CI.attendance.Model.*;
import com.CI.attendance.Repository.RegulationRepository;
import com.CI.attendance.Repository.UserRepository;
import com.CI.attendance.Service.*;
import com.CI.attendance.jwt.JwtTokenFilter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.jsonwebtoken.Claims;

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
	
	@Autowired
	ChapterService chapterService;
	
	@Autowired
	JwtTokenFilter filter;
	
	@Autowired private UserService service;
	
	@Autowired private UserRepository userRepository;
	
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
	
	
	//----------------CHAPTER CRUD Operations----------------
	@PostMapping(value= "/addChapter", consumes = {"application/json"})
    public MetaChapter addChapter(@RequestBody @Valid MetaChapter chapter) {
		System.out.println("course.getRegulation() "+chapter.getCourse().getCourseCode());
		//System.out.println("course.getCourse_outcome_map() "+course.getCourse_outcome_map().getCourseOutcomeIds().toString());
        return chapterService.addChapter(chapter);
    }
	
	@GetMapping("/viewAllChapters")  
	public List<MetaChapter> getAllChapters()   
	{  
		return chapterService.getAllChapters();  
	} 
	
	@GetMapping("/viewChapter/{chapterId}")  
	public MetaChapter getChapter(@PathVariable("chapterId") int chapterId)   
	{  
		return  chapterService.getChapter(chapterId);
	} 
	
	@DeleteMapping("/deleteChapter/{chapterId}")  
	public void deleteChapter(@PathVariable("chapterId") int chapterId)   
	{  
		chapterService.deleteChapter(chapterId);
	}
	
	@PutMapping("/updateChapter/{id}")
	public MetaChapter updateChapter(@PathVariable int id, @RequestBody MetaChapter chapter) {
		
		    return chapterService.updateChapter(id, chapter);
	}
	//------------User CRUD Operations--------------------------
	@GetMapping("/viewAllUsers")  
	public List<User> getAllUsers()   
	{  
		return service.getAllUsers(); //returns all users except admin 
	}
	
	@GetMapping("/viewUser/{userId}")  
	public User getUser(@PathVariable("userId") int userId)   
	{  
		return  service.getUser(userId);
	} 
	
	@DeleteMapping("/deleteUser/{userId}")  
	public void deleteUserr(@PathVariable("userId") int userId)   
	{  
		service.deleteUser(userId);
	}
	
	@PutMapping("/updateUser/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		
		    return service.updateUser(id, user);
	}
	//------------Admin Profile Operations--------------------------
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody ObjectNode objectNode)
	{
		String password =  objectNode.get("password").asText();
		String oldPassword =  objectNode.get("oldPassword").asText();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		int id =userPrincipal.getId();
		 User user =userRepository.findById(id).orElse(null);
		 System.out.println("password  "+password);
		 System.out.println("oldPassword  "+oldPassword);
		 if (user!=null)
		 {
			 String db_password = user.getPassword(); 
			 BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

			 if (bcrypt.matches(oldPassword, db_password))
			 { 
				 System.out.println("if two matches");
				 user.setPassword(password);
				 return ResponseEntity.ok().body(service.save(user)); 
			 }
			 else
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( "Old Password Mismatch!"); 
		 }
		 else
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( "User does not exist");
	  
	 
		
	}
	
	
	
}
