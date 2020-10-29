package com.SpringStarter.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringStarter.example.Domain.Note;
import com.SpringStarter.example.Mapper.NoteMapper;

@Service
public class NoteServiceImpl implements NoteService {
	
	@Autowired NoteMapper notemapper;
	

	@Override
	public void createNote(Note note) {
		notemapper.createnote(note);
	
	}


	@Override
	public List<Note> selectnote(String id) {
		return notemapper.selectnote(id);
	}


	@Override
	public void updatenote(Integer id) {
		notemapper.updatenote(id);
		
	}

}
