package com.CI.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.MetaCourseOutcome;

@Repository
public interface CourseOutcomeRepository extends JpaRepository<MetaCourseOutcome, Integer>{

}
