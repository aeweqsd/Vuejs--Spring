package com.SpringStarter.example;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SpringStarter.example.Config.Tries;
import com.SpringStarter.example.Service.SearchService;
import com.googlecode.concurrenttrees.common.KeyValuePair;
import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.RadixTree;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;

@SpringBootTest
class RestControllerApplicationTests {
	@Autowired Tries tries;
	
	@Autowired SearchService searchservice;
	
	
	@BeforeEach()
	public void setup() {
		
	}

	@Test
	void contextLoads() {
		RadixTree<Integer> tree = new ConcurrentRadixTree<Integer>(new DefaultCharArrayNodeFactory()); 
		tree.put("springboot",2);
		tree.put("springcloud",2);
		tree.put("springmvc",3);
		tree.put("springboot",tree.getValueForExactKey("springboot") ==null ? 1 : +1);
		List<KeyValuePair<Integer>> a =  StreamSupport.stream(tree.getKeyValuePairsForKeysStartingWith("spring").spliterator(),false).collect(Collectors.toList());
		for(int i=0; i<a.size();i++) {
			System.out.println(a.get(i).getKey());
		}
	}

}
