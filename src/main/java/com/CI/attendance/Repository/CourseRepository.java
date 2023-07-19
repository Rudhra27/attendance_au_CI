package com.CI.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.MetaCourse;

@Repository
public interface CourseRepository extends JpaRepository<MetaCourse, Integer> {

}
