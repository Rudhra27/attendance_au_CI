package com.CI.attendance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.User;
import com.CI.attendance.Model.UserCourseMap;

@Repository
public interface UserCourseMapRepository extends JpaRepository<UserCourseMap, Integer>{

	List<UserCourseMap> findByUsers(User users);

	UserCourseMap findByIdAndUsers(int id, User users);

}
