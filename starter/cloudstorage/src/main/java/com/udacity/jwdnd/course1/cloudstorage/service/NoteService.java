package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public ArrayList<Note> getNotes(Integer userId) { return this.noteMapper.getUserNotes(userId); }

    public void addNote(NoteForm noteForm) {
        Note note = new Note();
        note.setUserId(Integer.valueOf(noteForm.getUserId()));
        note.setNoteTitle(noteForm.getNoteTitle());
        note.setNoteDescription(noteForm.getNoteDescription());
        this.noteMapper.createNote(note);
    }

    public void updateNote(NoteForm noteForm) {
        Note note = new Note();
        note.setNoteId(Integer.valueOf(noteForm.getNoteId()));
        note.setUserId(Integer.valueOf(noteForm.getUserId()));
        note.setNoteTitle(noteForm.getNoteTitle());
        note.setNoteDescription(noteForm.getNoteDescription());
        this.noteMapper.updateNote(note);
    }

    public Integer deleteNote(Integer noteId) {
        return this.noteMapper.deleteNote(noteId);
    }
}
