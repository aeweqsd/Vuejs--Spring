package com.SpringStarter.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringStarter.example.Domain.Board;
import com.SpringStarter.example.Mapper.BoardMapper;
import com.SpringStarter.example.Request.selection;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired BoardMapper boardmapper;
	
	
	@Override
	public List<Board> selectBoardList(selection sel) {
		return boardmapper.selectBoardList(sel);
	}

	@Override
	public Board selectBoardDetail(int idboard) {
		boardmapper.updatehit(idboard);
		return boardmapper.selectBoardDetail(idboard);
	}

	@Override
	public void deleteBoard(int idboard) {
		boardmapper.deleteBoard(idboard);	
	}

	@Override
	public List<Board> subjectboardlist(String subject) {
	
		return boardmapper.subjectboardlist(subject);
	}

	@Override
	public int insertBoard(Board board) {
		return boardmapper.insertBoard(board);
		
	}

}
