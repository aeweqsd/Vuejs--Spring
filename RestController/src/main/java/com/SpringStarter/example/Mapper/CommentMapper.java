package com.SpringStarter.example.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SpringStarter.example.Domain.Comment;

@Mapper
public interface CommentMapper {
	public void insertcomment(Comment commnet);
	public List<Comment> selectcomment(int idboard);
}
