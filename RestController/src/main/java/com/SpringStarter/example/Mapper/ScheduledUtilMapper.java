package com.SpringStarter.example.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.SpringStarter.example.Domain.Popular;

@Mapper
public interface ScheduledUtilMapper {
	public void deletecomm();
	public List<Map<String,Integer>> refreshsub();
	public List<Popular> popular();
}
