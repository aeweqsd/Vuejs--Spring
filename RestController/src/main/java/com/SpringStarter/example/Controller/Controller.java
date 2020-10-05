package com.SpringStarter.example.Controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin(origins="*",maxAge =3600)
@RestController
@RequestMapping("/api/test")
public class Controller {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//	 @Autowired private BoardService boardservice;
	 
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> userAccess(){
		return ResponseEntity.ok("aa");
		}
	
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('Admin')")
	public String adminaccess() {
		return "Admin Content";
	}
	@GetMapping("/boarddetail")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> boarddetail(@RequestParam int bId){
		logger.info("///"+bId);
		return ResponseEntity.ok(bId);		
	}
	
	@DeleteMapping("/boardDelete/{bId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> boarddelete(@PathVariable(value ="bId") int bId){
		return ResponseEntity.ok(bId);
	}
	
	
}
