package com.CI.attendance.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.CI.attendance.Model.ConstantTypes.TypeOfCourse;
import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.CI.attendance.Model.ConstantTypes.TypeOfCourse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;


import io.hypersistence.utils.hibernate.type.array.*;
import com.CI.attendance.Model.MetaRegulation;
//import com.vladmihalcea.hibernate.type.array.StringArrayType;

@SuppressWarnings("serial")
@Entity
@Table(name = "courses")

//@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer", "fieldhandler"})
public class MetaCourse{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String courseCode;

	@NotBlank
	private String courseTitle;

	@NotNull
	private int semester;

	@NotNull
	private int credits;

	
	@Enumerated
	private TypeOfCourse typeOfCourse;

	//@JsonIgnore
	//@JsonView(MetaRegulation.class)
	 //@JsonBackReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(targetEntity = MetaRegulation.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "regulation", referencedColumnName = "id", updatable = false, nullable = false)
	private MetaRegulation regulation;

	@Type(StringArrayType.class)
	@Column(name = "branches", columnDefinition = "text[]")
	private String[] branches;

	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	//com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion (StackOverflowError)
	@JsonManagedReference //@JsonManagedReference is the forward part of reference, the one that gets serialized normally.
	 @OneToOne(mappedBy = "metaCourse", cascade = CascadeType.ALL)
	 @PrimaryKeyJoinColumn
	 private CourseOutcomeMap course_outcome_map;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	
	  public TypeOfCourse getTypeOfCourse() 
	  {
		  return typeOfCourse; 
	  }
	  
	  public void setTypeOfCourse(TypeOfCourse typeOfCourse) { this.typeOfCourse =
	  typeOfCourse; }
	  	  
	  public MetaRegulation getRegulation() { return regulation; }
	  	  
	  public void setRegulation(MetaRegulation regulation) { this.regulation =
	  regulation; }
	  
	  
	  
	  public String[] getBranches() { return branches; }
	  
	  
	  
	  public void setBranches(String[] branches) { this.branches = branches; }
	  
	 

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

	public CourseOutcomeMap getCourse_outcome_map() {
		return course_outcome_map;
	}

	public void setCourse_outcome_map(CourseOutcomeMap course_outcome_map) {
		this.course_outcome_map = course_outcome_map;
	}

	

	public MetaCourse(int id, @NotBlank String courseCode, @NotBlank String courseTitle, @NotNull int semester,
			@NotNull int credits, @NotBlank TypeOfCourse typeOfCourse, MetaRegulation regulation, String[] branches,
			LocalDateTime createdAt, LocalDateTime updatedAt, CourseOutcomeMap course_outcome_map) {
		super();
		this.id = id;
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
		this.semester = semester;
		this.credits = credits;
		this.typeOfCourse = typeOfCourse;
		this.regulation = regulation;
		this.branches = branches;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.course_outcome_map = course_outcome_map;
	}

	public MetaCourse() {
	}

}
