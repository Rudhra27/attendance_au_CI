package com.CI.attendance.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CI.attendance.Model.MetaChapter;
import com.CI.attendance.Model.MetaCourse;
import com.CI.attendance.Repository.ChapterRepository;
import com.CI.attendance.Repository.CourseRepository;

@Service 
public class ChapterService {

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ChapterRepository chapterRepository;
	
	public MetaChapter addChapter(@Valid MetaChapter chapter) {
		Optional<MetaCourse> course = courseRepository.findById(chapter.getCourse().getId());
		chapter.setCourse_code(course.get().getCourseCode());
		chapter.setCourse(course.orElseThrow(null));
		
		String [] str = chapter.getSub_topics();
		String s = str[0];
		System.out.println("s  "+s);
		System.out.println("str  "+Arrays.toString(str));
		String [] new_str = s.split("[-–,]");
		System.out.println("new_str  "+Arrays.toString(new_str));
		chapter.setSub_topics(new_str);
		return chapterRepository.save(chapter);
	}

	public List<MetaChapter> getAllChapters() {
		List<MetaChapter> chapters  = new ArrayList<MetaChapter>();  
		chapterRepository.findAll().forEach(chapter -> chapters.add(chapter));  
		return chapters; 
	}

	public MetaChapter getChapter(int chapterId) {
		return chapterRepository.findById(chapterId).get();
	}

	public void deleteChapter(int chapterId) {
		chapterRepository.deleteById(chapterId);
		
	}

	public MetaChapter updateChapter(int id, MetaChapter chapter) {
		MetaChapter updateChapter = chapterRepository.findById(id).get();
		Optional<MetaCourse> course = courseRepository.findById(chapter.getCourse().getId());
		updateChapter.setCourse_code(course.get().getCourseCode());
		updateChapter.setCourse(course.orElseThrow(null));
		updateChapter.setChapter_no(chapter.getChapter_no());
		updateChapter.setChapter_title(chapter.getChapter_title());
		String [] str = chapter.getSub_topics();
		String s = str[0];
		System.out.println("s  "+s);
		System.out.println("str  "+Arrays.toString(str));
		String [] new_str = s.split("[-–,]");
		updateChapter.setSub_topics(new_str);
		System.out.println("new_str  "+Arrays.toString(new_str));
		return chapterRepository.save(updateChapter);
	}

}
