package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotesByUserId(Integer userId) {
        return noteMapper.getAllUserNotes(userId);
    }

    public int createNote(Note note) {
        return noteMapper.createNote(note);
    }

    public int updateNote(String title, String description, Integer noteId, Integer userId) {
        return noteMapper.updateNote(title, description, noteId, userId);
    }

    public int deleteNote(Integer noteId, Integer userId) {
        return noteMapper.deleteNote(noteId, userId);
    }



}
