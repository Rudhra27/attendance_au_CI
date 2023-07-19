package com.CI.attendance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.MetaChapter;

@Repository
public interface ChapterRepository extends JpaRepository<MetaChapter, Integer>{

}
