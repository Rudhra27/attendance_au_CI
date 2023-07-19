package com.CI.attendance.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CI.attendance.Model.MetaRegulation;
import com.CI.attendance.Model.User;
import com.CI.attendance.Model.UserCourseMap;
import com.CI.attendance.Repository.UserCourseMapRepository;
import com.CI.attendance.Repository.UserRepository;

import java.text.SimpleDateFormat;  
import java.util.Date;  

@Service
public class UserCourseMapService {
	@Autowired
	UserCourseMapRepository ucmRepository;
	
	@Autowired private UserRepository userRepo;

	public List<UserCourseMap> getAllUserCourse(User users) {
		List<UserCourseMap> maps = new ArrayList<UserCourseMap>();  
		ucmRepository.findByUsers(users).forEach(map -> maps.add(map));  
		return maps; 
	}

	public UserCourseMap addUserCourseMap(UserCourseMap ucm, User user) {
		System.out.println("code  "+ucm.getCourse_code());
		System.out.println("getRegulation  "+ucm.getRegulation());
		System.out.println("getProgramme  "+ucm.getProgramme());
		System.out.println("title  "+ucm.getTitle());
		System.out.println("getDept_of_student  "+ucm.getDept_of_student());
		System.out.println("branches  "+ucm.getBranches());
		
	
		ucm.setUsers(user);
		return ucmRepository.save(ucm);
	}

	public UserCourseMap getUserCourse(int userCourseId) {
		return ucmRepository.findById(userCourseId).get();
	}

	public void deleteUserCourseMap(int userCourseId) {
		ucmRepository.deleteById(userCourseId);
		
	}

	public UserCourseMap updateUserCourseMap(int id, UserCourseMap ucm, User users) {
		UserCourseMap map = ucmRepository.findByIdAndUsers(id, users);
		map.setUsers(users);
		map.setBranches(ucm.getBranches());
		map.setCourse_code(ucm.getCourse_code());
		map.setCourse_id(ucm.getCourse_id());
		map.setCredits(ucm.getCredits());
		map.setDept_of_student(ucm.getDept_of_student());
		map.setProgramme(ucm.getProgramme());
		map.setRegulation(ucm.getRegulation());
		map.setSem_start_date(ucm.getSem_start_date());
		map.setSem_end_date(ucm.getSem_end_date());
		map.setSemester(ucm.getSemester());
		map.setTitle(ucm.getTitle());
		return ucmRepository.save(map);
	}

}
