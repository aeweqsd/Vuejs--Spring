package com.SpringStarter.example.Service;

import java.util.List;

import com.SpringStarter.example.Domain.Comment;

public interface CommentService {
	public void insertcomment(Comment commnet);
	public List<Comment> selectcomment(int idboard);
	public void deletecomment(int idcomment);
}
