package com.SpringStarter.example.Controller;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringStarter.example.Config.JwtUtils;
import com.SpringStarter.example.Config.Scheduler;
import com.SpringStarter.example.Domain.User;
import com.SpringStarter.example.Request.JoinRequest;
import com.SpringStarter.example.Request.LoginRequest;
import com.SpringStarter.example.Response.JwtResponse;
import com.SpringStarter.example.Service.UserService;

import io.jsonwebtoken.Jwts;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api/auth")
public class authController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired AuthenticationManager authenticationManager;
	
	@Autowired PasswordEncoder encoder;
	
	@Autowired JwtUtils jwtutils;
	
	@Autowired UserService userservice;
	
	@Autowired private Scheduler sc;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest){
		logger.info("test" + loginRequest);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtutils.generateJwtToken(authentication);
		User user = (User) authentication.getPrincipal();
		logger.info("1111"  + authentication.getPrincipal());
		List<String> roles= user.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, user.getUsername(),user.getName(),roles));
	}
	@GetMapping("/logout")
	public ResponseEntity<?> disableUser(HttpServletRequest req){
		String headerauth = req.getHeader("Authorization");
		if(StringUtils.hasText(headerauth) && headerauth.startsWith("Bearer")) {
			headerauth.substring(7, headerauth.length());
		}
		return ResponseEntity.ok(11);
		
		
	}
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@Validated @RequestBody JoinRequest joinrequest){
		String encodedPassword = new BCryptPasswordEncoder().encode(joinrequest.getPassword());
		  User user = new User();
		user.setUsername(joinrequest.getUsername());
	      user.setName(joinrequest.getName());
	      user.setPassword(encodedPassword);
	      user.setPhone(joinrequest.getPhone());
	      user.setAccountNonExpired(true);
	      user.setEnabled(true);
	      user.setAccountNonLocked(true);
	      user.setCredentialsNonExpired(true);
	      user.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
	      
	      //蜡历 积己
	      userservice.createUser(user);
	      //蜡历 鼻茄 积己
	      userservice.createAuthority(user);
	      
	      return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
