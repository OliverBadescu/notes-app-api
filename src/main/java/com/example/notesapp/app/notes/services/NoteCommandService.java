package com.example.notesapp.app.notes.services;

import com.example.notesapp.app.notes.dtos.CreateNoteRequest;
import com.example.notesapp.app.notes.dtos.NoteResponse;
import com.example.notesapp.app.notes.dtos.UpdateNoteRequest;

public interface NoteCommandService {

    NoteResponse addNote(CreateNoteRequest createNoteRequest ,long userId);

    String deleteNote(long id);

    NoteResponse updateNote(long id, UpdateNoteRequest updateNoteRequest);
}
