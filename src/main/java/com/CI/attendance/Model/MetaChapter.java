package com.CI.attendance.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.*;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;

@Entity
public class MetaChapter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //remove comment if need to show course in response
	@JsonBackReference//make it comment if need to show course in response
	@ManyToOne(targetEntity = MetaCourse.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course", referencedColumnName = "id", updatable = false, nullable = false)
	private MetaCourse course;
	
	//@NotBlank
	private String course_code;
	
	@NotNull
	private int chapter_no;
	
	@NotBlank
	private String chapter_title;

	@Type(StringArrayType.class)
	@Column(name = "sub_topics", columnDefinition = "text[]")
	private String[] sub_topics;
	
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MetaCourse getCourse() {
		return course;
	}

	public void setCourse(MetaCourse course) {
		this.course = course;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public int getChapter_no() {
		return chapter_no;
	}

	public void setChapter_no(int chapter_no) {
		this.chapter_no = chapter_no;
	}

	public String getChapter_title() {
		return chapter_title;
	}

	public void setChapter_title(String chapter_title) {
		this.chapter_title = chapter_title;
	}

	public String[] getSub_topics() {
		return sub_topics;
	}

	public void setSub_topics(String[] sub_topics) {
		this.sub_topics = sub_topics;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public MetaChapter(int id, MetaCourse course, @NotBlank String course_code, @NotNull int chapter_no,
			@NotBlank String chapter_title, String[] sub_topics, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.course = course;
		this.course_code = course_code;
		this.chapter_no = chapter_no;
		this.chapter_title = chapter_title;
		this.sub_topics = sub_topics;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public MetaChapter() {
		
	}
	
	

}
