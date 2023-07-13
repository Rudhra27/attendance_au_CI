package com.CI.attendance.Model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  @Size(max = 20)
  private String username;
  
  @NotBlank
  private String name;
  
  @NotBlank
  private String designation;
  
  @NotBlank
  private String department;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

	/*
	 * @NotBlank
	 * 
	 * @Size(max = 120)
	 */
  private String password;

  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", 
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();
  
  @CreationTimestamp
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;
  
  @UpdateTimestamp
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedAt;

  public User() {
  }

  public User(String username, String name, String designation, String department, String email, String password) {
    this.username = username;
    this.name = name;
	this.designation = designation;
	this.department = department;
    this.email = email;
    this.password = password;
  }

	/*
	 * public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank
	 * String name, @NotBlank String designation,
	 * 
	 * @NotBlank String department, @NotBlank @Size(max = 50) @Email String email,
	 * 
	 * @NotBlank @Size(max = 120) String password) { super(); this.id = id;
	 * this.username = username; this.name = name; this.designation = designation;
	 * this.department = department; this.email = email; this.password = password; }
	 */



  public String getUsername() {
    return username;
  }

  public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public void setUsername(String username) {
    this.username = username;
  }
  

  public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  

public Set<Role> getRoles() {
	return roles;
}

public void setRoles(Set<Role> roles) {
	this.roles = roles;
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

public void addRole(Role role) {
	this.roles.add(role);
}
  
  
}
