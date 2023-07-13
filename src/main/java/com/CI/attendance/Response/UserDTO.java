package com.CI.attendance.Response;

import java.util.List;
import java.util.Set;

import com.CI.attendance.Model.Role;

public class UserDTO {
		private int id;
		private String username;
		private String email;
		private String designation;
		private String department;
		private Set<Role> roles;


		public UserDTO(int id, String username, String email, String designation, String department,
				Set<Role> roles) {
			super();
			this.id = id;
			this.username = username;
			this.email = email;
			this.designation = designation;
			this.department = department;
			this.roles = roles;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Set<Role> getRoles() {
			return roles;
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


		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}
		
		
	}
