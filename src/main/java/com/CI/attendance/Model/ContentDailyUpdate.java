package com.CI.attendance.Model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.CI.attendance.Model.ConstantTypes.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import io.hypersistence.utils.hibernate.type.array.StringArrayType;

@Entity
public class ContentDailyUpdate {
	
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
	 

	 @NotBlank
	 private String content_delivered;
	 
	 @NotNull
	 private int course_outcome;
	 
	 @Type(StringArrayType.class)
		@Column(name = "type_of_lecture", columnDefinition = "text[]")
		private String[] type_of_lecture;
	 
	 
	 @NotBlank
	 private String mode_of_delivery;
	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		private Date date;
	 
	 @Type(IntArrayType.class) 
	  @Column( name = "slot", columnDefinition = "integer[]" )
	  private int[] slot;
	 
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

	public String getContent_delivered() {
		return content_delivered;
	}

	public void setContent_delivered(String content_delivered) {
		this.content_delivered = content_delivered;
	}

	public int getCourse_outcome() {
		return course_outcome;
	}

	public void setCourse_outcome(int course_outcome) {
		this.course_outcome = course_outcome;
	}

	public String[] getType_of_lecture() {
		return type_of_lecture;
	}

	public void setType_of_lecture(String[] type_of_lecture) {
		this.type_of_lecture = type_of_lecture;
	}

	public String getMode_of_delivery() {
		return mode_of_delivery;
	}

	public void setMode_of_delivery(String mode_of_delivery) {
		this.mode_of_delivery = mode_of_delivery;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int[] getSlot() {
		return slot;
	}

	public void setSlot(int[] slot) {
		this.slot = slot;
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

	public ContentDailyUpdate(int id, User users, @NotNull int course_id, @NotBlank String course_code,
			@NotBlank String content_delivered, @NotNull int course_outcome, String[] type_of_lecture,
			@NotBlank String mode_of_delivery, Date date, int[] slot, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.users = users;
		this.course_id = course_id;
		this.course_code = course_code;
		this.content_delivered = content_delivered;
		this.course_outcome = course_outcome;
		this.type_of_lecture = type_of_lecture;
		this.mode_of_delivery = mode_of_delivery;
		this.date = date;
		this.slot = slot;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public ContentDailyUpdate() {
		
	}
	  
	  
}
