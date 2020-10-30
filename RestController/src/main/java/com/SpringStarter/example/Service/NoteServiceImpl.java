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
	public List<Note> selectnote(String id,int option) {
		if(option ==0) {
		return notemapper.selectnote(id);
		}else {
		return notemapper.selectnoterev(id);
		}
	}


	@Override
	public void updatenote(Integer id) {
		notemapper.updatenote(id);
		
	}


	@Override
	public void deletenote(int id) {
		notemapper.deletenote(id);
		
	}

}
