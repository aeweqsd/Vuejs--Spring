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
	
	@Scheduled(cron=" */30 * * * * *")		//Ű���� �߰���Ű��
	public void add_keyword() {
		List<String> li = searchservice.initKeyword();
		searchservice.insertkeywo(li);
		logger.info("addkeyword");
		
	}
	@Scheduled(cron="0 0/15 * * * * ")				//������ �Խñ��� ��ۻ���
	public void print2() {
		util.deletecomm();
		logger.info("deleting comment");
	}
	
//	@Scheduled(cron="0 0/30 * * * * ")  			//���� ������ �Խ�������
//	public void sc() {
//		List<Map<String,Integer>> a = util.refreshsub();
//		logger.info(a.toString());
//	}
	@Scheduled(cron="0 0/30 * * * *")				//�α������ ���� ���� ->10�����ܷ� �����Ͽ� �����߰� (������ �ִ� ���� ����) 30�и���
	public void sub() {
		List<Popular> a = util.popular();
		Collections.sort(a,new Pop_Comparator());
		a.stream().forEach(System.out::println);
		
	}
}
