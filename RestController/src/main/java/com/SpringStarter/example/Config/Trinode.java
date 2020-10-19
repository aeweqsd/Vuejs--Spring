package com.SpringStarter.example.Config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Trinode {
	private Map<Character,Trinode> childNodes = new HashMap<>();
	private boolean isLastChar;
	Map<Character,Trinode> getChildNodes(){
		return this.childNodes;
	}
	boolean isLastChar() {
		return this.isLastChar;
	}
	void setIsLastChar(boolean isLastChar) {
		this.isLastChar = isLastChar;
	}
	
}
