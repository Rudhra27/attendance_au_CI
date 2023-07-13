package com.CI.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.CourseOutcomeMap;

@Repository
public interface CourseOutcomeMapRepository extends JpaRepository<CourseOutcomeMap, Integer>{

	CourseOutcomeMap findById(int id);

}
