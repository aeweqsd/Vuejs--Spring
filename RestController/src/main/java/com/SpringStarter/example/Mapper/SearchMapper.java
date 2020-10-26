package com.SpringStarter.example.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.SpringStarter.example.Domain.Board;

@Mapper
public interface SearchMapper {
	public List<String> initKeyword();
	public void insertKeyword(Map<String,Integer> map);
	public List<String> showKeyword(String name);
	public void insertkeywo(List<String> name);
	public List<Board> selectKeyword(String name);
}
