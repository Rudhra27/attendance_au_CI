package com.CI.attendance.Service;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CI.attendance.Model.Role;
import com.CI.attendance.Model.User;
import com.CI.attendance.Repository.RoleRepository;
import com.CI.attendance.Repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class ExcelReader {
	@Autowired
	 UserRepository userRepository;
	@Autowired
	  RoleRepository roleRepository;
	@Autowired
	  RandomPasswordGenerator passayGenerator;
	 @Autowired
	  PasswordEncoder encoder;
	
	  
	public static  String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "username", "name", "designation", "department", "email", "role" };
	static String SHEET = "User";

	public boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}
	
	@Transactional
	public List<User> excelToUsers(MultipartFile file) {
		try {
			InputStream initialStream = file.getInputStream();
			  XSSFWorkbook workbook = new XSSFWorkbook(initialStream); 
			  XSSFSheet sheet = workbook.getSheetAt(0); 
			  System.out.println(sheet.getPhysicalNumberOfRows());
			  for(int i = 1; i<sheet.getPhysicalNumberOfRows();i++)
			  {
				  User user = new User();
				  
				  DecimalFormat decimalFormat = new DecimalFormat("#");
				  String formattedId = decimalFormat.format(sheet.getRow(i).getCell(0).getNumericCellValue()); 
				  user.setUsername(formattedId);
		                user.setName(sheet.getRow(i).getCell(1).toString());
		                user.setDesignation(sheet.getRow(i).getCell(2).toString());
		                user.setDepartment(sheet.getRow(i).getCell(3).toString());
		                user.setEmail(sheet.getRow(i).getCell(4).toString());
		                String strRole = (sheet.getRow(i).getCell(5).toString());
		                
						Set<String> strRoles = new HashSet<String>();
						strRoles.add(strRole);
						Set<Role> roles = new HashSet<>();

						if (strRoles.stream().findFirst().orElse(null) == null) {
							Role userRole = roleRepository.findByName("User");
								//	.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(userRole);
						} else {
							String role = strRoles.stream().findFirst().orElse(null);
							Role role1 = roleRepository.findByName(role);//.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(role1);
						}
						user.setRoles(roles);
						String Password = passayGenerator.generatePassayPassword();
					    System.out.println("Password " +Password);
						user.setPassword(encoder.encode(Password));
						userRepository.save(user);
			  }
            
            workbook.close();
            List<User> users = new ArrayList<User>();
            users = userRepository.findAll();
			return users;
        } catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}

