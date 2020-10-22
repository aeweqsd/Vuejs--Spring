package com.SpringStarter.example.Config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Scheduled(fixedDelay=1000)
	public void print() {
		logger.info("scheduled on");
	}
	
}
