package com.CI.attendance.Model;

import java.time.Instant;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "meta_programme_outcome")
public class MetaProgrammeOutcome {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	
	@NotBlank
	private String programmeCode;
	
	@NotBlank
	private String programmeOutcome;
	
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

	public String getProgrammeCode() {
		return programmeCode;
	}

	public void setProgrammeCode(String programmeCode) {
		this.programmeCode = programmeCode;
	}

	public String getProgrammeOutcome() {
		return programmeOutcome;
	}

	public void setProgrammeOutcome(String programmeOutcome) {
		this.programmeOutcome = programmeOutcome;
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

	
	public MetaProgrammeOutcome(int id, @NotBlank String programmeCode, @NotBlank String programmeOutcome,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.programmeCode = programmeCode;
		this.programmeOutcome = programmeOutcome;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public MetaProgrammeOutcome() {
		
	}
	  
	  

}
