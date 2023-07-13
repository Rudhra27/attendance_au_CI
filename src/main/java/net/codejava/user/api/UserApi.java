/*
 * package net.codejava.user.api;
 * 
 * import java.net.URI; import java.util.HashSet; import java.util.Set;
 * 
 * import javax.validation.Valid;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.CI.attendance.jwt.JwtTokenUtil; import
 * com.CI.attendance.Service.ExcelService; import
 * com.CI.attendance.Service.ExcelReader; import com.CI.attendance.Model.Role;
 * import com.CI.attendance.Model.User; import
 * com.CI.attendance.Repository.RoleRepository; import
 * com.CI.attendance.Repository.UserRepository;
 * 
 * 
 * @RestController public class UserApi {
 * 
 * @Autowired private UserService service;
 * 
 * @Autowired private RoleRepository roleRepository;
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @PutMapping("/users") public ResponseEntity<?> createUser(@RequestBody @Valid
 * User user) { Set<Role> roles1 =user.getRoles(); //Set<String> strRoles =
 * user.getRoles(); // System.out.println("strRoles" + strRoles); Set<Role>
 * roles = new HashSet<>();
 * 
 * if (roles1 == null) { Role userRole =
 * roleRepository.findByName("ROLE_EDITOR"); //.orElseThrow(() -> new
 * RuntimeException("Error: Role is not found.")); roles.add(userRole); } else {
 * String role = roles1.toString(); Role role1 =
 * roleRepository.findByName(role);//.orElseThrow(() -> new //
 * RuntimeException("Error: Role is not found.")); roles.add(role1); }
 * user.setRoles(roles); User createdUser =service.save(user); URI uri =
 * URI.create("/users/" + createdUser.getId()); UserDTO userDto = new
 * UserDTO(createdUser.getId(),
 * createdUser.getUsername(),createdUser.getEmail(),
 * createdUser.getDepartment(),createdUser.getDesignation(),createdUser.getRoles
 * () );
 * 
 * //UserDTO userDto = new UserDTO(createdUser.getId(), createdUser.getEmail());
 * 
 * return ResponseEntity.created(uri).body(userDto); } }
 */