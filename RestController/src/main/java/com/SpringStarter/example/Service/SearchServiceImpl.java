package com.SpringStarter.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringStarter.example.Mapper.SearchMapper;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired SearchMapper searchmapper;

	
	@Override
	public List<String> initKeyword(){
		return searchmapper.initKeyword();
	}

}