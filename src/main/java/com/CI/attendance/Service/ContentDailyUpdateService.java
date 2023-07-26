package com.CI.attendance.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CI.attendance.Model.ContentDailyUpdate;
import com.CI.attendance.Model.User;
import com.CI.attendance.Model.UserCourseMap;
import com.CI.attendance.Repository.ContentDailyUpdateRepository;
import com.CI.attendance.Repository.CourseRepository;

@Service
public class ContentDailyUpdateService {
	
	@Autowired
	ContentDailyUpdateRepository cduRepo;
	
	@Autowired
	CourseRepository courseRepository;

	public ContentDailyUpdate addDailyContent(ContentDailyUpdate cdu, User user) {
		String code = courseRepository.findById(cdu.getCourse_id()).get().getCourseCode();
		cdu.setCourse_code(code);
		cdu.setUsers(user);
		return cduRepo.save(cdu);
	}

	public List<ContentDailyUpdate> getAllDailyContent(User users) {
		List<ContentDailyUpdate> maps = new ArrayList<ContentDailyUpdate>();  
		cduRepo.findByUsers(users).forEach(map -> maps.add(map));  
		return maps; 
	}

	public ContentDailyUpdate getDailyContent(int dailyContentId) {
		return cduRepo.findById(dailyContentId).get();
	}

	public void deleteDailyContent(int dailyContentId) {
		cduRepo.deleteById(dailyContentId);
	}

	public ContentDailyUpdate updateDailyContent(int id, ContentDailyUpdate cdu, User users) {
		ContentDailyUpdate cdu1= cduRepo.findByIdAndUsers(id, users);
		cdu1.setUsers(users);
		cdu1.setCourse_id(cdu.getCourse_id());
		String code = courseRepository.findById(cdu.getCourse_id()).get().getCourseCode();
		cdu1.setCourse_code(code);
		cdu1.setContent_delivered(cdu.getContent_delivered());
		cdu1.setCourse_outcome(cdu.getCourse_outcome());
		cdu1.setDate(cdu1.getDate());
		cdu1.setMode_of_delivery(cdu.getMode_of_delivery());
		cdu1.setSlot(cdu.getSlot());
		cdu1.setType_of_lecture(cdu.getType_of_lecture());
		return cduRepo.save(cdu1);
		
	}
	

}
