package com.CI.attendance.Model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

// @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private String name;
  //private ERole name;

  public Role() {

  }
  public Role(String name) {
		this.name = name;
	}
	 // public Role(ERole name) { this.name = name; }
	 

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

	
	  public String getName() { return name; }
	  
	  public void setName(String name) { this.name = name; }
	 

  
	
		/*
		 * public ERole getName() { return name; }
		 * 
		 * public void setName(ERole name) { this.name = name; }
		 */
	 
}
