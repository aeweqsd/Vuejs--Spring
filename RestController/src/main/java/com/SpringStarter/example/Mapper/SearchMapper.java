package com.SpringStarter.example.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchMapper {
	public List<String> initKeyword();
}
