package com.SpringStarter.example.Config;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchUtils {
	public HashMap<String, Integer> init(List<String> a){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0; i<a.size();i++) {
			StringTokenizer st = new StringTokenizer(a.get(i));
			while(st.hasMoreTokens()) {
				String keyword = st.nextToken();
				map.put(keyword,map.getOrDefault(keyword, 0)+1);
			}
		}
		return map;
	}
	
}
