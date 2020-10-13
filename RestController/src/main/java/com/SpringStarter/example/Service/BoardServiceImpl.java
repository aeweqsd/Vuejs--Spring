package com.SpringStarter.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringStarter.example.Domain.Board;
import com.SpringStarter.example.Mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired BoardMapper boardmapper;
	
	
	@Override
	public List<Board> selectBoardList() {
		return boardmapper.selectBoardList();
	}

	@Override
	public Board selectBoardDetail(int idboard) {
	
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

}
