package com.SpringStarter.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringStarter.example.Domain.Subject;
import com.SpringStarter.example.Mapper.SubjectMapper;

@Service
public class SubjectServiceImpl implements SubjectService{
	@Autowired SubjectMapper subjectmapper;
	
	@Override
	public List<Subject> selectSubjectList() {
		
		return subjectmapper.selectSubjectList();
	}

}
