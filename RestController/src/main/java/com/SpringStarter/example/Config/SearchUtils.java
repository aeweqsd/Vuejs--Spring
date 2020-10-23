package com.SpringStarter.example.Config;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.googlecode.concurrenttrees.common.KeyValuePair;
import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.RadixTree;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;

@Component
public class SearchUtils {
	private RadixTree<String> tree = new ConcurrentRadixTree<String>(new DefaultCharArrayNodeFactory()); 
	public void insertTree(String keyword) {
		tree.put(keyword,keyword);
	}
	public List<KeyValuePair<String>> search(String keyword){
		List<KeyValuePair<String>> a =StreamSupport.stream(tree.
							getKeyValuePairsForKeysStartingWith(keyword)
							.spliterator(),false)
							.collect(Collectors.toList());
		return a;
	}
}
