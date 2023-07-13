package com.CI.attendance.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CI.attendance.Model.CourseOutcomeMap;
import com.CI.attendance.Model.MetaCourse;
import com.CI.attendance.Model.MetaCourseOutcome;
import com.CI.attendance.Model.MetaRegulation;
import com.CI.attendance.Repository.*;


@Service
public class CourseService {

	@Autowired
	CoursePepository courseRepository;
	
	@Autowired
	CourseOutcomeMapRepository comapRepository;
	
	@Autowired
	RegulationRepository regulationRepo;
	
	public MetaCourse addCourse(@Valid MetaCourse course) {
		Optional<MetaRegulation> reg = regulationRepo.findById(course.getRegulation().getId());
		course.setRegulation(reg.orElseThrow(null));
		CourseOutcomeMap map = new CourseOutcomeMap();
		map.setCourseOutcomeIds(course.getCourse_outcome_map().getCourseOutcomeIds());
		map.setProgrammeOutcomeIds(course.getCourse_outcome_map().getProgrammeOutcomeIds());
		
		course.setCourse_outcome_map(null);	
		MetaCourse course1 =  courseRepository.save(course);	
		map.setMetaCourse(course1);	
		comapRepository.save(map);
		course1.setCourse_outcome_map(map);
		return courseRepository.save(course1);
	}

	public List<MetaCourse> getAllCourses() {
		List<MetaCourse> courses = new ArrayList<MetaCourse>();  
		courseRepository.findAll().forEach(course -> courses.add(course));  
		return courses; 
	}
	 public MetaCourse getCourse(int courseId) {
		return courseRepository.findById(courseId).get();
	}

	public void deleteCourse(int courseId) {
		courseRepository.deleteById(courseId);
		
	}

	public MetaCourse updateCourse(int id, MetaCourse course) {
		MetaCourse updateCourse = courseRepository.findById(id).get();
		updateCourse.setCourseCode(course.getCourseCode());
		updateCourse.setCourseTitle(course.getCourseTitle());
		updateCourse.setCredits(course.getCredits());
		updateCourse.setSemester(course.getSemester());
		updateCourse.setTypeOfCourse(course.getTypeOfCourse());
		updateCourse.setBranches(course.getBranches());
		Optional<MetaRegulation> reg = regulationRepo.findById(course.getRegulation().getId());
		updateCourse.setRegulation(reg.orElseThrow(null));
		
		CourseOutcomeMap map = comapRepository.findById(id);
		map.setCourseOutcomeIds(course.getCourse_outcome_map().getCourseOutcomeIds());
		map.setProgrammeOutcomeIds(course.getCourse_outcome_map().getProgrammeOutcomeIds());
		comapRepository.save(map);
		updateCourse.setCourse_outcome_map(map);
		return courseRepository.save(updateCourse);
	}

}
