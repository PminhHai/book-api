package com.pmh.library.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmh.library.dto.LoginDTO;
import com.pmh.library.dto.SignupDTO;
import com.pmh.library.entity.Role;
import com.pmh.library.entity.User;
import com.pmh.library.jwt.JwtUtils;
import com.pmh.library.repository.RoleRepository;
import com.pmh.library.repository.UserRepository;
import com.pmh.library.response.JwtResponse;
import com.pmh.library.response.MessageResponse;
import com.pmh.library.service.UserDetailImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,
								userDetails.getId(),
								userDetails.getUsername(),
								userDetails.getEmail(),
								roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDTO signUpDTO) {
		if(userRepository.existsByUsername(signUpDTO.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		
		if (userRepository.existsByEmail(signUpDTO.getEmail())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Email is already in use!"));
		}
		
		User user = new User();
		user.setUsername(signUpDTO.getUsername());
	    user.setEmail(signUpDTO.getEmail());
	    user.setPassword(encoder.encode(signUpDTO.getPassword()));
	    Set<String> strRoles = signUpDTO.getRole();
	    Set<Role> roles = new HashSet<>();
	    
	    if(strRoles == null) {
	    	Role userRole = roleRepository.findByName("ROLE_USER")
	    			.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	    	roles.add(userRole);
	    } else {
	    	strRoles.forEach(role -> {
	            switch (role) {
	            case "admin":
	              Role adminRole = roleRepository.findByName("ROLE_ADMIN")
	                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	              roles.add(adminRole);

	              break;
	            default:
	              Role userRole = roleRepository.findByName("ROLE_USER")
	                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	              roles.add(userRole);
	            }
	          });
		}
	    
	    user.setRoles(roles);
	    userRepository.save(user);
	    
	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
