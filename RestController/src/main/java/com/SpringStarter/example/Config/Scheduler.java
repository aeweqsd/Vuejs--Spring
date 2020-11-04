package com.SpringStarter.example.Config;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.SpringStarter.example.Domain.Popular;
import com.SpringStarter.example.Mapper.ScheduledUtilMapper;
import com.SpringStarter.example.Service.SearchService;

@Component
public class Scheduler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ScheduledUtilMapper util;
	@Autowired SearchService searchservice;
	
	@Scheduled(cron=" */30 * * * * *")		//키워드 추가시키기
	public void add_keyword() {
		List<String> li = searchservice.initKeyword();
		searchservice.insertkeywo(li);
		logger.info("addkeyword");
		
	}
	@Scheduled(cron="0 0/15 * * * * ")				//삭제된 게시글의 댓글삭제
	public void print2() {
		util.deletecomm();
		logger.info("deleting comment");
	}
	
//	@Scheduled(cron="0 0/30 * * * * ")  			//새로 생성된 게시판주제
//	public void sc() {
//		List<Map<String,Integer>> a = util.refreshsub();
//		logger.info(a.toString());
//	}
	@Scheduled(cron="0 0/30 * * * *")				//인기순으로 주제 나열 ->10개내외로 정리하여 주제추가 (기존에 있던 주제 삭제) 30분마다
	public void sub() {
		List<Popular> a = util.popular();
		Collections.sort(a,new Pop_Comparator());
		a.stream().forEach(System.out::println);
		
	}
}
