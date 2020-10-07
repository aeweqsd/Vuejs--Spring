package com.SpringStarter.example.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SpringStarter.example.Domain.Subject;

@Mapper
public interface SubjectMapper {
	public List<Subject> selectSubjectList();

}
