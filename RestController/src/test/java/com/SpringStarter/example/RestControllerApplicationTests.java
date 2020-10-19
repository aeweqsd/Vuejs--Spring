package com.SpringStarter.example;

import java.util.List;
import java.util.StringTokenizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SpringStarter.example.Config.Tries;
import com.SpringStarter.example.Service.SearchService;
import com.mysql.cj.log.Log;
import com.mysql.cj.log.LogFactory;

@SpringBootTest
class RestControllerApplicationTests {
	@Autowired Tries tries;
	
	@Autowired SearchService searchservice;
	
	
	@BeforeEach()
	public void setup() {
		
	}

	@Test
	void contextLoads() {
		List<String> keyword  = searchservice.initKeyword();
	
		for(int i=0;i<keyword.size();i++) {
			String key = keyword.get(i);
			StringTokenizer st =  new StringTokenizer(key);
			while(st.hasMoreTokens()) {
				String in = st.nextToken();
				System.out.println(in);
				tries.insert(in);
			}
		}
		
	}

}
