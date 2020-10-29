package com.SpringStarter.example.Controller;

import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.SpringStarter.example.Config.Scheduler;
import com.SpringStarter.example.Domain.Board;
import com.SpringStarter.example.Domain.Comment;
import com.SpringStarter.example.Domain.Note;
import com.SpringStarter.example.Domain.Subject;
import com.SpringStarter.example.Request.BoardRequest;
import com.SpringStarter.example.Request.CommentRequest;
import com.SpringStarter.example.Service.BoardService;
import com.SpringStarter.example.Service.CommentService;
import com.SpringStarter.example.Service.NoteService;
import com.SpringStarter.example.Service.SearchService;
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
		 @Autowired private SearchService searchservice;
		 @Autowired private NoteService noteservice;
		 
		 
		 
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
	@GetMapping("/keyword")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> initKeyword(){
		List<String> Result = searchservice.initKeyword();
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0; i<Result.size();i++) {
			StringTokenizer st = new StringTokenizer(Result.get(i));
			while(st.hasMoreTokens()) {
				String keyword = st.nextToken();
				map.put(keyword,map.getOrDefault(keyword, 0)+1);
			}
		}
		return ResponseEntity.ok(map);
	}

	@PostMapping(value ="/keyword")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> insertKeyword(@RequestBody Map<String,Integer> list){
		logger.info(list.toString());
		searchservice.insertKeyword(list);
		return ResponseEntity.ok(list);
	}
	@GetMapping("/search")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> createsearch(@RequestParam(value="keyword",required=false,defaultValue="-1")String search){
		List<String>list = searchservice.showKeyword("%"+search+"%");
		return ResponseEntity.ok(list);
	}
	@GetMapping("/searchlist")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> searchlist(@RequestParam(value="keyword") String keyword){
		List<Board>list  = searchservice.selectKeyword("%"+keyword+"%");
		list.get(0).setBoardmax(list.size());
		return ResponseEntity.ok(list);
	}
	@PostMapping(value="/note")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> createnote(@RequestBody Note note){
		noteservice.createNote(note);
		return ResponseEntity.ok(note);
	}
	@GetMapping("/note")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> selectnote(@RequestParam(value="id")String id){
		List<Note> list = noteservice.selectnote(id);
		return ResponseEntity.ok(list);
	}
	@PatchMapping("/note")
	public ResponseEntity<?> readingnote(@RequestBody String idnote){
		Integer a = Integer.parseInt(idnote);
		noteservice.updatenote(a);
		return ResponseEntity.ok(" ");
		
	}
	
}
