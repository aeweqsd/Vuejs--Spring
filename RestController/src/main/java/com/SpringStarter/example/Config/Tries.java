package com.SpringStarter.example.Config;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component
public class Tries {
	private Trinode rootNode;
	Tries(){
		rootNode = new Trinode();
	}
	public void insert(String keyword) {
		Trinode thisnode = this.rootNode;
		for(int i=0;i<keyword.length();i++) {
			thisnode = thisnode.getChildNodes().computeIfAbsent(keyword.charAt(i),c->new Trinode());
		}
	}
	public boolean contains(String keyword) {
		Trinode thisnode = this.rootNode;
		for(int i=0;i <keyword.length();i++) {
			char character =keyword.charAt(i);
			Trinode node = thisnode.getChildNodes().get(character);
			if(node == null)
				return false;
			thisnode = node;
		}
		return thisnode.isLastChar();
	}
	

}
