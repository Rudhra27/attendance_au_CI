package com.CI.attendance.Model;

import javax.persistence.*;
import io.hypersistence.utils.hibernate.type.array.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
//import com.vladmihalcea.hibernate.type.array.*;

@Entity
@Table(name = "course_outcome_maps")
public class CourseOutcomeMap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonBackReference
	 @OneToOne
	    @JoinColumn(name = "id")
	 @MapsId
	    private MetaCourse metaCourse;
	
	@Type(IntArrayType.class) 
	  @Column( name = "programmeOutcomeIds", columnDefinition = "integer[]" )
	  private int[] programmeOutcomeIds;
	  
	  @Type(IntArrayType.class)
	  @Column( name = "courseOutcomeIds", columnDefinition = "integer[]" ) private
	  int[] courseOutcomeIds;
	 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MetaCourse getMetaCourse() {
		return metaCourse;
	}

	public int[] getProgrammeOutcomeIds() {
		return programmeOutcomeIds;
	}

	public void setProgrammeOutcomeIds(int[] programmeOutcomeIds) {
		this.programmeOutcomeIds = programmeOutcomeIds;
	}

	public int[] getCourseOutcomeIds() {
		return courseOutcomeIds;
	}

	public void setCourseOutcomeIds(int[] courseOutcomeIds) {
		this.courseOutcomeIds = courseOutcomeIds;
	}

	public void setMetaCourse(MetaCourse metaCourse) {
		this.metaCourse = metaCourse;
	}

	
	public CourseOutcomeMap(int id, MetaCourse metaCourse, int[] programmeOutcomeIds, int[] courseOutcomeIds) {
		super();
		this.id = id;
		this.metaCourse = metaCourse;
		this.programmeOutcomeIds = programmeOutcomeIds;
		this.courseOutcomeIds = courseOutcomeIds;
	}

	public CourseOutcomeMap() {
		
	}
	
	
	
	  
	
	

}
