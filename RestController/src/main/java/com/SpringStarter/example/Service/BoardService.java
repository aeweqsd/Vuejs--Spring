package com.SpringStarter.example.Service;

import java.util.List;

import com.SpringStarter.example.Domain.Board;
import com.SpringStarter.example.Request.selection;

public interface BoardService {
	
	
	public List<Board> selectBoardList(selection se);
	
	
	public Board selectBoardDetail(int idboard);
	
	
	public void deleteBoard(int idboard);
	
	public List<Board> subjectboardlist(String subject);

	public int insertBoard(Board board);
}
