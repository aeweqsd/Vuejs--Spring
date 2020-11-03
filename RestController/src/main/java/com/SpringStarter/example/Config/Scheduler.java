package com.SpringStarter.example.Config;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.SpringStarter.example.Mapper.ScheduledUtilMapper;
import com.SpringStarter.example.Service.SearchService;

@Component
public class Scheduler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ScheduledUtilMapper util;
	@Autowired SearchService searchservice;
	
	@Scheduled(cron=" */30 * * * * *")
	public void print() {
		List<String> li = searchservice.initKeyword();
		searchservice.insertkeywo(li);
		logger.info("scheduled on");
	}
	@Scheduled(cron="0 0/15 * * * * ")
	public void print2() {
		util.deletecomm();
		logger.info("deleting comment");
	}
}
