package com.CI.attendance.Model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;


@Entity
public class UserCourseMap {

	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	 
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "users", referencedColumnName = "id", updatable = false, nullable = false)
	 private User users;
	 
	 
	 @NotNull
	 private int course_id;
	 
	 @NotBlank
	 private String course_code;
	 
	 @Type(StringArrayType.class)
		@Column(name = "branches", columnDefinition = "text[]")
		private String[] branches;
	 
	 @NotBlank
	 private String title;
	 
	 @NotNull
	private int semester;
	 
	 @NotBlank
	 private String programme;
	 
	 @NotBlank
	 private String dept_of_student;
	 
	 @NotNull
	private int credits;
	 
	 @NotNull
	private Long regulation;
	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date sem_start_date;
	
	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date sem_end_date;
	 
	 @CreationTimestamp
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

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	public String[] getBranches() {
		return branches;
	}

	public void setBranches(String[] branches) {
		this.branches = branches;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getDept_of_student() {
		return dept_of_student;
	}

	public void setDept_of_student(String dept_of_student) {
		this.dept_of_student = dept_of_student;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Long getRegulation() {
		return regulation;
	}

	public void setRegulation(Long regulation) {
		this.regulation = regulation;
	}

	public Date getSem_start_date() {
		return sem_start_date;
	}

	public void setSem_start_date(Date sem_start_date) {
		this.sem_start_date = sem_start_date;
	}

	public Date getSem_end_date() {
		return sem_end_date;
	}

	public void setSem_end_date(Date sem_end_date) {
		this.sem_end_date = sem_end_date;
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

	public UserCourseMap(int id, User users, @NotNull int course_id, @NotBlank String course_code, String[] branches,
			@NotBlank String title, @NotNull int semester, @NotBlank String programme, @NotBlank String dept_of_student,
			@NotNull int credits, @NotNull Long regulation, Date sem_start_date, Date sem_end_date,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.users = users;
		this.course_id = course_id;
		this.course_code = course_code;
		this.branches = branches;
		this.title = title;
		this.semester = semester;
		this.programme = programme;
		this.dept_of_student = dept_of_student;
		this.credits = credits;
		this.regulation = regulation;
		this.sem_start_date = sem_start_date;
		this.sem_end_date = sem_end_date;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserCourseMap() {
	}
	  
	  
}
