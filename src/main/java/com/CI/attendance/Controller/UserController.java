package com.CI.attendance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.CI.attendance.Service.UserCourseMapService;
import com.CI.attendance.jwt.JwtTokenFilter;


@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('User', 'HOD','FacultyAdvisor','Student')")
public class UserController {
	
	@Autowired
	UserCourseMapService ucmService;
	
	@Autowired
	JwtTokenFilter filter;
	
	// ----------------UserCourseMap CRUD Operations----------------
		@GetMapping("/viewAllUserCourse")  
		public List<UserCourseMap> getAllUserCourse()   
		{  
			return ucmService.getAllUserCourse(filter.getUser());  
		}  
		
		@GetMapping("/viewUserCourse/{userCourseId}")  
		public UserCourseMap getUserCourse(@PathVariable("userCourseId") int userCourseId)   
		{  
			return  ucmService.getUserCourse(userCourseId);
		} 

		@PostMapping("/addUserCourseMap")
		public UserCourseMap addUserCourseMap(@RequestBody UserCourseMap ucm)
		{
			System.out.println("code  "+ucm.getCourse_code());
			System.out.println("getRegulation  "+ucm.getRegulation());
			System.out.println("getProgramme  "+ucm.getProgramme());
			System.out.println("title  "+ucm.getTitle());
			System.out.println("getDept_of_student  "+ucm.getDept_of_student());
			System.out.println("branches  "+ucm.getBranches());
			
			return ucmService.addUserCourseMap(ucm, filter.getUser());
		}
		
		@DeleteMapping("/deleteUserCourseMap/{userCourseId}")  
		public void deleteUserCourseMap(@PathVariable("userCourseId") int userCourseId)   
		{  
			ucmService.deleteUserCourseMap(userCourseId);
		}
		
		@PutMapping("/updateUserCourseMap/{id}")
		public UserCourseMap updateUserCourseMap(@PathVariable int id, @RequestBody UserCourseMap ucm) {
			
			    return ucmService.updateUserCourseMap(id, ucm, filter.getUser());
		}
		
}
