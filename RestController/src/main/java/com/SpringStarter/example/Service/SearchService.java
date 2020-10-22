package com.SpringStarter.example.Service;

import java.util.List;
import java.util.Map;

public interface SearchService {
	public List<String> initKeyword();
	public void insertKeyword(Map<String,Integer> map);
	public List<String> showKeyword(String name);
}
