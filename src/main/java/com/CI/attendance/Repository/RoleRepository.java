package com.CI.attendance.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CI.attendance.Model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String role);

Role findById(int i);


}
