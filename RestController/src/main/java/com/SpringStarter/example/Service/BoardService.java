package com.SpringStarter.example.Service;

import java.util.List;

import com.SpringStarter.example.Domain.Board;

public interface BoardService {
	
	
	public List<Board> selectBoardList();
	
	
	public Board selectBoardDetail(int idboard);
	
	
	public void deleteBoard(int idboard);
	

}
