package com.CI.attendance.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
//import org.apache.xmlbeans.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.CI.attendance.Model.*;
import com.CI.attendance.Model.MetaCourse;
import com.CI.attendance.Model.MetaProgrammeOutcome;
import com.CI.attendance.Model.User;
import com.CI.attendance.Repository.*;

@Service
public class PdfReaderService {

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	ContentDailyUpdateRepository cduRepo;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	CourseOutcomeMapRepository courseOutcomeMapRepo;
	
	@Autowired
	ProgrammeOutcomeRepository proRepository;
	
	@Autowired
	CourseOutcomeRepository coutcomeRepository;
	
	@Autowired
	UserCourseMapRepository ucmRepository;
	
	 public ByteArrayInputStream fillPdfForm(int course_id, User users) throws IOException {

	        InputStream input = null;
	        Resource resource = resourceLoader.getResource("classpath:document.pdf");
	    	//File file = resource.getFile());
	        try {
	            input = new FileInputStream( resource.getFile());
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        }

	        //Load editable pdf file
	        try (PDDocument pdfDoc = PDDocument.load(input);) {
	        	 
	            PDDocumentCatalog docCatalog = pdfDoc.getDocumentCatalog();
	            PDAcroForm acroForm = docCatalog.getAcroForm();
	            ByteArrayOutputStream out = new ByteArrayOutputStream();
	           
	            
	           
	            
	            // Start a new content stream which will "hold" the to be created content  
	            
	             
	            List<ContentDailyUpdate> cdu = cduRepo.getUserByUsersAndCourse(users,course_id); 
	            if (cdu.isEmpty()) {
	            	throw new NullPointerException("No Daily Report found for this course!");
	            };
	            MetaCourse course = courseRepository.findById(course_id).get();
	            if (course.getCourse_outcome_map() == null) {
	            	throw new NullPointerException("No Course Outcome found for this course!");
	            };
	            
	            UserCourseMap userCourseMap = ucmRepository.getByUsersAndCourse(users, course_id);
            	int [] courseOutcomeIds =course.getCourse_outcome_map().getCourseOutcomeIds();
 	            int[] programmeOutcomeIds = course.getCourse_outcome_map().getProgrammeOutcomeIds();
 	            
 	           List<MetaProgrammeOutcome> meta_programme=new ArrayList<MetaProgrammeOutcome>();
 	            for(int i=0;i<programmeOutcomeIds.length;i++)
 	            {
 	            	meta_programme.add(proRepository.findById(programmeOutcomeIds[i]).get());
 	            }
 	          
 	           List<MetaCourseOutcome> meta_course=new ArrayList<MetaCourseOutcome>();
 	          for(int i=0;i<courseOutcomeIds.length;i++)
	            {
 	        	 meta_course.add(coutcomeRepository.findById(courseOutcomeIds[i]).get());
	            }
 	          
 	         PDField name_and_designation = acroForm.getField("Name and Designation of the Staff");
 	        name_and_designation.setValue(""+users.getName()+" "+users.getDesignation());
	            
	            PDField department_staff = acroForm.getField("Department of the Staff");
	            department_staff.setValue(""+users.getDepartment());

	            PDField department_student = acroForm.getField("Department of the Student");
	            department_student.setValue(""+userCourseMap.getDept_of_student());

	            PDField Semester = acroForm.getField("Semester");
	            Semester.setValue(""+course.getSemester());

	            PDField Regulation = acroForm.getField("Regulation");
	            Regulation.setValue(""+course.getRegulation().getRegulation());
	            
	            System.out.println("null "+course.getCredits());
	            
	            PDField credits = acroForm.getField("Credits");
	            credits.setValue(""+course.getCredits());
	            

	            PDField semStartDate = acroForm.getField("From");
	            semStartDate.setValue(""+new SimpleDateFormat("dd-MM-yyyy").format(userCourseMap.getSem_start_date()));
	            
	            PDField semEndDate = acroForm.getField("to");
	            semEndDate.setValue(""+new SimpleDateFormat("dd-MM-yyyy").format(userCourseMap.getSem_end_date()));
	            
	           String [] branches = course.getBranches();
	           int i=1;
	           for (String branch : branches) {
	        	   PDField pb = acroForm.getField("Programme Branch "+i);
	        	  
	        	   if(branch.equals("CS"))
	        		   pb.setValue(userCourseMap.getProgramme()+" "+branch);
	        	   if(branch.equals("IT"))
	        		   pb.setValue(userCourseMap.getProgramme()+" "+branch);
	        	    i++;
	        	}
	           PDField cct = acroForm.getField("Course Code Title "+1);
	           cct.setValue(course.getCourseCode());
	           
	           PDField cct2 = acroForm.getField("Course Code Title "+2);
	           cct2.setValue(course.getCourseTitle());
	           
	           for(int j=0;j<meta_programme.size();j++)
	           {
	        	   String code= meta_programme.get(j).getProgrammeCode();
	        	   String name= code.startsWith("PS")? "PS" : "PO";
	        	   PDField po = acroForm.getField(name + code.substring(2, code.length()));
	        	   po.setValue(meta_programme.get(j).getProgrammeOutcome());
	           }
	           
	           for(int j=0;j<meta_course.size();j++)
	           {
	        	   String code= meta_course.get(j).getCourseOutcomeCode();
	        	   PDField co = acroForm.getField("CO" + code.substring(2,code.length()));
	        	   co.setValue(meta_course.get(j).getCourseOutcome());
	           }
	           i=1;
	           for ( ContentDailyUpdate report : cdu) {
	        	   PDField sl_row = acroForm.getField("Sl NoRow"+i);
	        	   sl_row.setValue(""+i);
	        	   
	        	   PDField DateRow = acroForm.getField("DateRow"+i);
	        	   DateRow.setValue(""+new SimpleDateFormat("dd-MM-yyyy").format(report.getDate()));
	        	   
	        	   PDField SlotRow = acroForm.getField("SlotRow"+i);
	        	   SlotRow.setValue(""+Arrays.toString(report.getSlot()));
	        	   
	        	   PDField content = acroForm.getField("Brief Outline of the Content DeliveredRow"+i);
	        	   content.setValue(report.getContent_delivered());
	        	   
	        	   PDField CORow = acroForm.getField("CORow"+i);
	        	   CORow.setValue(""+report.getCourse_outcome());
	        	   for(String type: report.getType_of_lecture())
	        	   {
	        		   String name="";
	        		   if (type.equals("Lecture"))
	        			    name ="LRow"+i;
	        		   if (type.equals("Tutorial"))
	        			    name = "TRow"+i;
	        		   PDField  type_lec= acroForm.getField(name);
	        		   type_lec.setValue("Yes");
	        	   }
	        	   
	        	   PDField mode = acroForm.getField("Mode of DeliveryRow"+i);
	        	   mode.setValue(report.getMode_of_delivery());
	        	   
	        	   i++;
	           }
	           
	                
	            pdfDoc.save(out);

	            return new ByteArrayInputStream(out.toByteArray());

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}
