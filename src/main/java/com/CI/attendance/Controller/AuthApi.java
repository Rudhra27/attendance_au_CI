package com.CI.attendance.Controller;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CI.attendance.jwt.JwtTokenUtil;
import com.CI.attendance.Service.*;
import com.CI.attendance.Service.RandomPasswordGenerator;
import com.CI.attendance.Service.ExcelService;
import com.CI.attendance.Service.UserService;
import com.CI.attendance.Service.ExcelReader;
import com.CI.attendance.Model.Role;
import com.CI.attendance.Model.User;
import com.CI.attendance.Repository.RoleRepository;
import com.CI.attendance.Repository.UserRepository;
import com.CI.attendance.Request.AuthRequest;
import com.CI.attendance.Response.*;

@RestController
@RequestMapping("/auth")
public class AuthApi {
	@Autowired AuthenticationManager authManager;
	@Autowired JwtTokenUtil jwtUtil;
	
	@Autowired
	  ExcelService fileService;
	@Autowired private RoleRepository roleRepository;
	
	@Autowired private UserRepository userRepository;
	
	@Autowired private UserService service;
	
	 @Autowired
	  RandomPasswordGenerator passayGenerator;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			/*
			 * Authentication authentication = authManager.authenticate( new
			 * UsernamePasswordAuthenticationToken( request.getUsername(),
			 * request.getPassword()) );
			 * 
			 * User user = (User) authentication.getPrincipal(); String accessToken =
			 * jwtUtil.generateAccessToken(user); AuthResponse response = new
			 * AuthResponse(user.getUsername(), accessToken);
			 * 
			 * return ResponseEntity.ok().body(response);
			 */
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtil.generateAccessToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			 AuthResponse response = new AuthResponse(userDetails.getUsername(), jwt);
			 return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@PutMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
		/*
		 * Set<Role> roles1 =user.getRoles(); //Set<String> strRoles = user.getRoles();
		 * 
		 * if (roles1 == null) { System.out.println("if  " ); Role userRole =
		 * roleRepository.findByName("User"); System.out.println("userrole  "+userRole
		 * ); roles1.add(userRole); }
		 */
		/*
		 * else { System.out.println("else" ); String role = roles1.toString(); Role
		 * role1 = roleRepository.findByName(role);//.orElseThrow(() -> new //
		 * RuntimeException("Error: Role is not found."));
		 * System.out.println("role  "+role ); System.out.println("role1  "+role );
		 * roles.add(role1); }
		 */
		/*
		 * System.out.println("Roles  " + roles); user.setRoles(roles);
		 */
		Set<Role> roles1 =user.getRoles();
		Role role = roles1.stream().findFirst().orElse(null);
		String name = role.getName();
		roles1.clear();
		roles1.add(roleRepository.findByName(name));
		user.setRoles(roles1);
	   String Password = passayGenerator.generatePassayPassword();
	   System.out.println("Password " +Password);
	   user.setPassword(Password);
		User createdUser =  service.save(user);
		URI uri = URI.create("/users/" + createdUser.getId());
		
		UserDTO userDto = new UserDTO(createdUser.getId(), createdUser.getUsername(),createdUser.getEmail(),
				createdUser.getDepartment(),createdUser.getDesignation(),createdUser.getRoles() );
		
		return ResponseEntity.created(uri).body(userDto);
	}
	
	@PostMapping("/signupUsers")
	  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		    
		    ExcelReader excelReader = new ExcelReader();
		    if (excelReader.hasExcelFormat(file)) {
		      try {
		        fileService.save(file);
		        return ResponseEntity.status(HttpStatus.OK).body( "Uploaded the file successfully: " + file.getOriginalFilename());
		      } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file: " + file.getOriginalFilename() + "!"+e.toString());
		      }
		    }
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( "Please upload an excel file!");
		  }
}
