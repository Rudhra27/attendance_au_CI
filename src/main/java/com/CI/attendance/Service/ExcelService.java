package com.CI.attendance.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CI.attendance.Model.Role;
import com.CI.attendance.Model.User;
import com.CI.attendance.Repository.RoleRepository;
import com.CI.attendance.Repository.UserRepository;


@Service
public class ExcelService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  ExcelReader excelReader; //use autowired for all
  public void save(MultipartFile file) throws Exception {
	  //ExcelReader excelReader = new ExcelReader();
    System.out.println(" In excel service");
     List<User> users = excelReader.excelToUsers(file);
     //userRepository.saveAll(users);
     System.out.println("users "+users);
  }
  
  

  public List<Role> getAllUsers() {
    return roleRepository.findAll();
  }
}
