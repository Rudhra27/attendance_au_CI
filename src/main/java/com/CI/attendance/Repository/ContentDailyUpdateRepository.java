package com.CI.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.ContentDailyUpdate;
import com.CI.attendance.Model.User;
import java.util.*;
@Repository
public interface ContentDailyUpdateRepository extends JpaRepository<ContentDailyUpdate,Integer>{

	List<ContentDailyUpdate> findByUsers(User users);

	ContentDailyUpdate findByIdAndUsers(int id, User users);

	@Query(value = "SELECT * FROM content_daily_update where users = ?1 AND course_id = ?2", nativeQuery = true)
	List<ContentDailyUpdate> getUserByUsersAndCourse(User users, int course_id);

}
