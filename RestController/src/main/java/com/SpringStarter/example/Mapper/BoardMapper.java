package com.SpringStarter.example.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SpringStarter.example.Domain.Board;
import com.SpringStarter.example.Request.selection;

@Mapper
public interface BoardMapper {
	public List<Board> selectBoardList(selection sel);
	
	
	public Board selectBoardDetail(int idboard);
	
	
	public void deleteBoard(int idboard);
	
	public List<Board> subjectboardlist(String subject);
	
	public int insertBoard(Board board);
	
	public void updatehit(int idboard);
	
}
