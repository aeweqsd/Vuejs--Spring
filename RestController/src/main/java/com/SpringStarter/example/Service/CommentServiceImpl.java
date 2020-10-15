package com.SpringStarter.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringStarter.example.Domain.Comment;
import com.SpringStarter.example.Mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired CommentMapper commentmapper;
	
	
	
	@Override
	public void insertcomment(Comment commnet) {
		commentmapper.insertcomment(commnet);
	}
	
	
	@Override
	public List<Comment> selectcomment(int idboard){
		return commentmapper.selectcomment(idboard);
	}


	@Override
	public void deletecomment(int idcomment) {
		commentmapper.deletecomment(idcomment);
		
	}
}
