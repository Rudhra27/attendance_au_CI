package com.CI.attendance.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
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
import com.CI.attendance.Service.ContentDailyUpdateService;
import com.CI.attendance.Service.PdfReaderService;
import com.CI.attendance.Service.UserCourseMapService;
import com.CI.attendance.jwt.JwtTokenFilter;
import java.io.ByteArrayInputStream;


@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('User', 'HOD','FacultyAdvisor','Student')")
public class UserController {
	
	@Autowired
	UserCourseMapService ucmService;
	
	@Autowired
	ContentDailyUpdateService cduService;
	
	@Autowired
	JwtTokenFilter filter;
	
	@Autowired
	PdfReaderService pdfReaderService;
	
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
		
		
		// ----------------ContentDailyUpdate CRUD Operations----------------
		@GetMapping("/viewAllDailyContent")  
		public List<ContentDailyUpdate> getAllDailyContent()   
		{  
			return cduService.getAllDailyContent(filter.getUser());  
		} 
		
		@GetMapping("/viewDailyContent/{dailyContentId}")  
		public ContentDailyUpdate getDailyContent(@PathVariable("dailyContentId") int dailyContentId)   
		{  
			return  cduService.getDailyContent(dailyContentId);
		} 
		
		@PostMapping("/addDailyContent")
		public ContentDailyUpdate addDailyContent(@RequestBody ContentDailyUpdate cdu)
		{
			return cduService.addDailyContent(cdu, filter.getUser());
		}
		
		@DeleteMapping("/deleteDailyContent/{dailyContentId}")  
		public void deleteDailyContent(@PathVariable("dailyContentId") int dailyContentId)   
		{  
			cduService.deleteDailyContent(dailyContentId);
		}
		
		@PutMapping("/updateDailyContent/{id}")
		public ContentDailyUpdate updateDailyContent(@PathVariable int id, @RequestBody ContentDailyUpdate cdu) 
		{
			 return cduService.updateDailyContent(id, cdu, filter.getUser());
		}
		
		@GetMapping(value = "/pdf/{course_id}", produces = MediaType.APPLICATION_PDF_VALUE)
		public  ResponseEntity<InputStreamResource> downloadpdf(@PathVariable int course_id) throws IOException
		{
			ByteArrayInputStream contents = pdfReaderService.fillPdfForm(course_id,filter.getUser());

			/*
			 * HttpHeaders headers = new HttpHeaders();
			 * headers.setContentType(MediaType.APPLICATION_PDF); // Here you have to set
			 * the actual filename of your pdf String filename = "output.pdf";
			 * headers.setContentDispositionFormData(filename, filename);
			 * headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			 * ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers,
			 * HttpStatus.OK); return response;
			 */
			    
			    var headers = new HttpHeaders();
				/*
				 * ContentDisposition contentDisposition =
				 * ContentDisposition.builder("attachment").filename("book.pdf",
				 * StandardCharsets.UTF_8).build();
				 * //headers.setAccessControlExposeHeaders(ACCESS_CONTROL_EXPOSE_HEADERS);
				 * headers.setContentDisposition(contentDisposition); //
				 * headers.add("Content-Disposition", "inline; filename=book.pdf");
				 */
			   // headers.setContentDispositionFormData("book.pdf", "book.pdf");        
		       // headers.setContentType(MediaType.APPLICATION_PDF);
			    headers.add("Content-Disposition", "inline; filename=book.pdf");
			    
		        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(contents));
		}
}
