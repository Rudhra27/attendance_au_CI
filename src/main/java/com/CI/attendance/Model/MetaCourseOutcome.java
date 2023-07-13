package com.CI.attendance.Model;


import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "course_outcomes")
public class MetaCourseOutcome {

	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	
	@NotBlank
	private String courseOutcomeCode;
	
	@NotBlank
	private String courseOutcome;
	
	 @CreationTimestamp
	 //To convert array format to below mentioned format
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	  private LocalDateTime createdAt;
	  
	  @UpdateTimestamp
	  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	  private LocalDateTime updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseOutcomeCode() {
		return courseOutcomeCode;
	}

	public void setCourseOutcomeCode(String courseOutcomeCode) {
		this.courseOutcomeCode = courseOutcomeCode;
	}

	public String getCourseOutcome() {
		return courseOutcome;
	}

	public void setCourseOutcome(String courseOutcome) {
		this.courseOutcome = courseOutcome;
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

	public MetaCourseOutcome(int id, @NotBlank String courseOutcomeCode, @NotBlank String courseOutcome,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.courseOutcomeCode = courseOutcomeCode;
		this.courseOutcome = courseOutcome;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public MetaCourseOutcome() {
		
	}
	  
	  
}
