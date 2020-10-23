package com.SpringStarter.example;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SpringStarter.example.Service.SearchService;
import com.googlecode.concurrenttrees.common.KeyValuePair;
import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.RadixTree;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;

@SpringBootTest
class RestControllerApplicationTests {
	
	
	@BeforeEach()
	public void setup() {
		
	}

	@Test
	void contextLoads() {
		RadixTree<String> tree = new ConcurrentRadixTree<String>(new DefaultCharArrayNodeFactory()); 
		tree.put("springboot","springboot");
		tree.put("springcloud","springcloud");
		tree.put("springmvc","springmvc");
	//	tree.put("springboot",tree.getValueForExactKey("springboot") ==null ? 1 : +1);
		List<KeyValuePair<String>> a =  StreamSupport.stream(tree.getKeyValuePairsForKeysStartingWith("spring").spliterator(),false).collect(Collectors.toList());
		for(int i=0; i<a.size();i++) {
			System.out.println(a.get(i).getKey());
		}
	}

}
