package com.CI.attendance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.ContentDailyUpdate;
import com.CI.attendance.Model.User;
import com.CI.attendance.Model.UserCourseMap;

@Repository
public interface UserCourseMapRepository extends JpaRepository<UserCourseMap, Integer>{

	List<UserCourseMap> findByUsers(User users);

	UserCourseMap findByIdAndUsers(int id, User users);
	
	@Query(value = "SELECT * FROM user_course_map where users = ?1 AND course_id = ?2", nativeQuery = true)
	UserCourseMap getByUsersAndCourse(User users, int course_id);

	

}
