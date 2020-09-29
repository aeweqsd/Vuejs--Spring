package com.SpringStarter.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class Controller {
	@Autowired ObjectMapper objectmapper;
	
	@GetMapping("/User")
	public String selectuser() {
		
		return "/";
	}
	@PostMapping("/User")
	public String CreateUser() {
		return "/";
	}
	@DeleteMapping("/User")
	public String deleteUser() {
		return "/";
	}
	@PutMapping("/User")
	public String UpdateUser() {
		return "/";
	}

}
