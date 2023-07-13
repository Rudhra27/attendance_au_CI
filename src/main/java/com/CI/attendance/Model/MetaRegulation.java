package com.CI.attendance.Model;

import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "regulations")
//@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer", "fieldhandler"})
public class MetaRegulation {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;

	  @NotNull
	  private Long regulation;
	  
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



	public Long getRegulation() {
		return regulation;
	}



	public void setRegulation(Long regulation) {
		this.regulation = regulation;
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

		public MetaRegulation(int id, @NotNull Long regulation, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.regulation = regulation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



		public MetaRegulation()
	  {
		  
	  }
	  

}
