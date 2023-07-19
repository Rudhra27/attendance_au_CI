package com.CI.attendance.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CI.attendance.Model.MetaCourse;
import com.CI.attendance.Model.Role;
import com.CI.attendance.Model.User;
import com.CI.attendance.Repository.RoleRepository;
import com.CI.attendance.Repository.UserRepository;


@Service
@Transactional
public class UserService {
	@Autowired private UserRepository repo;
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private RoleRepository roleRepository;
	
	public User save(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		
		return repo.save(user);
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();  
		repo.findUserByRoleNot(1).forEach(user -> users.add(user));  
		return users; 
	}

	public User getUser(int userId) {
		return repo.findById(userId).get();
	}

	public void deleteUser(int userId) {
		repo.deleteById(userId);
	}

	public User updateUser(int id, User user) {
		// TODO Auto-generated method stub
		User user1 =repo.findById(id).get();
		user1.setName(user.getName());
		user1.setEmail(user.getEmail());
		user1.setUsername(user.getUsername());
		user1.setDepartment(user.getDepartment());
		user1.setDesignation(user.getDesignation());
		
		Set<Role> roles1 =user.getRoles();
		Role role = roles1.stream().findFirst().orElse(null);
		String name = role.getName();
		roles1.clear();
		roles1.add(roleRepository.findByName(name));
		user1.setRoles(roles1);
		return repo.save(user1);
	}
}
