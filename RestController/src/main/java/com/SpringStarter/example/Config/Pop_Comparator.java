package com.SpringStarter.example.Config;

import java.util.Comparator;

import com.SpringStarter.example.Domain.Popular;

public class Pop_Comparator implements Comparator<Popular> {

	@Override
	public int compare(Popular o1, Popular o2) {
		if(o1.getpop()>o2.getpop()) {
			return -1;
		}else if (o1.getpop()< o2.getpop()) {
			return 1;
		}else {
		return 0;
		}
	}

}
