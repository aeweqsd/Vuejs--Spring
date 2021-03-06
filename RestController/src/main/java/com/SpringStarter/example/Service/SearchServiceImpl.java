package com.SpringStarter.example.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringStarter.example.Domain.Board;
import com.SpringStarter.example.Mapper.SearchMapper;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired SearchMapper searchmapper;

	
	@Override
	public List<String> initKeyword(){
		return searchmapper.initKeyword();
	}


	@Override
	public void insertKeyword(Map<String, Integer> map) {
		searchmapper.insertKeyword(map);
		
	}


	@Override
	public List<String> showKeyword(String name) {
		return searchmapper.showKeyword(name);
	}


	@Override
	public List<Board> selectKeyword(String name) {
		
		return searchmapper.selectKeyword(name);
	}


	@Override
	public void insertkeywo(List<String> name) {
		searchmapper.insertkeywo(name);
		
	}



}
