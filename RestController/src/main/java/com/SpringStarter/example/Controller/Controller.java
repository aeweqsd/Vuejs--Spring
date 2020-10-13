package com.SpringStarter.example.Controller;

import org.slf4j.LoggerFactory;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.SpringStarter.example.Domain.Board;
import com.SpringStarter.example.Domain.Subject;
import com.SpringStarter.example.Service.BoardService;
import com.SpringStarter.example.Service.SubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin(origins="*", maxAge =3600)
@RestController
@RequestMapping("/api/test")
public class Controller {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
		 @Autowired private BoardService boardservice;
		 @Autowired private SubjectService subjectservice;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> userAccess(){
		List<Board> boardList = boardservice.selectBoardList();
		return ResponseEntity.ok(boardList);
		}
	
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('Admin')")
	public String adminaccess() {
		return "Admin Content";
	}
	
	@GetMapping("/board")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> boarddetail(@RequestParam int idboard){
		logger.info("///"+ idboard);
		Board board = boardservice.selectBoardDetail(idboard);
		return ResponseEntity.ok(board);		
	}
	
	@DeleteMapping("/board/{idboard}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> boarddelete(@PathVariable(value ="idboard") int idboard){
		logger.info("delete" + idboard);
		boardservice.deleteBoard(idboard);
		return ResponseEntity.ok(idboard);
	}
	
	@GetMapping("/boardSubject")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> boardsubject(){
		List<Subject> subjectlist = subjectservice.selectSubjectList();
		return ResponseEntity.ok(subjectlist);
	}
	@PostMapping("/subjectselect")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> subjectselect(@RequestBody String name){
		List<Board> board= boardservice.subjectboardlist(name);
		return ResponseEntity.ok("aaa");
	}
	
}
