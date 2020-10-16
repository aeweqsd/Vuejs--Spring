package com.SpringStarter.example.Controller;

import org.slf4j.LoggerFactory;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.SpringStarter.example.Domain.Board;
import com.SpringStarter.example.Domain.Comment;
import com.SpringStarter.example.Domain.Subject;
import com.SpringStarter.example.Request.BoardRequest;
import com.SpringStarter.example.Request.CommentRequest;
import com.SpringStarter.example.Service.BoardService;
import com.SpringStarter.example.Service.CommentService;
import com.SpringStarter.example.Service.SubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin(origins="*", maxAge =3600)
@RestController
@RequestMapping("/api/test")
public class Controller {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
		 @Autowired private BoardService boardservice;
		 @Autowired private SubjectService subjectservice;
		 @Autowired private CommentService commentservice;
		 
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> userAccess(@RequestParam int boardpage){
		logger.info("///" + boardpage);
		List<Board> boardList = boardservice.selectBoardList(boardpage);
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
	@PostMapping("/board")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> createboard(@RequestBody BoardRequest bo){
		Board board = new Board();
		board.setBoardcontent(bo.getContent());
		board.setBoardname(bo.getTitle());
		board.setBoardwriter(bo.getUserid());
		boardservice.insertBoard(board);
		logger.info("///" + board.getIdboard());
		return ResponseEntity.ok(boardservice.selectBoardDetail(board.getIdboard()));
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
/*	
	@PostMapping("/subjectselect")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> subjectselect(@RequestBody String name){
		List<Board> board= boardservice.subjectboardlist(name);
		return ResponseEntity.ok("aaa");
	}*/
	@PostMapping("/comment")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> insertcomment(@RequestBody CommentRequest com){
		Comment comment = new Comment();
		comment.setComment(com.getComment());
		comment.setIdboard(com.getIdboard());
		comment.setWriterid(com.getUserid());
		commentservice.insertcomment(comment);
		List<Comment> commentlist = commentservice.selectcomment(com.getIdboard());
		return ResponseEntity.ok(commentlist);
	}
	
	@GetMapping("/comment")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> selectcomment(@RequestParam int idboard){
		List<Comment> comment = commentservice.selectcomment(idboard);
		return ResponseEntity.ok(comment);
	}
	@DeleteMapping("/comment/{idcomment}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> deletecomment(@PathVariable(value="idcomment") int idcomment){
		commentservice.deletecomment(idcomment);
		return ResponseEntity.ok(idcomment);
	}
}
