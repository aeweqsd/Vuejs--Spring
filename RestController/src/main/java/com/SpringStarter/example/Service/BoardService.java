package com.SpringStarter.example.Service;

import java.util.List;

import com.SpringStarter.example.Domain.Board;

public interface BoardService {
	
	
	public List<Board> selectBoardList(int boardpage);
	
	
	public Board selectBoardDetail(int idboard);
	
	
	public void deleteBoard(int idboard);
	
	public List<Board> subjectboardlist(String subject);

	public int insertBoard(Board board);
}
