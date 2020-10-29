package com.SpringStarter.example.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SpringStarter.example.Domain.Note;

@Mapper
public interface NoteMapper {
	public void createnote(Note note);
	public List<Note> selectnote(String id);
	public void updatenote(Integer id);
}
