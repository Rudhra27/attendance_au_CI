package com.CI.attendance.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

	
	User findByEmail(String email);
	
	
	@Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE NOT role.id = ?1")
	List<User> findUserByRoleNot(int role);
}
