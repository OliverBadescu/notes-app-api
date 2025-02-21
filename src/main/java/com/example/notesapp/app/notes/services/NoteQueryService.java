package com.example.notesapp.app.notes.services;

import com.example.notesapp.app.notes.dtos.NoteResponseList;

public interface NoteQueryService {

    NoteResponseList getAllUserNotes(long userId);
}
