package com.SpringStarter.example.Service;

import java.util.List;

import com.SpringStarter.example.Domain.Note;

public interface NoteService {
	public void createNote(Note note);
	public List<Note> selectnote(String id);
	public void updatenote(Integer id);
}
