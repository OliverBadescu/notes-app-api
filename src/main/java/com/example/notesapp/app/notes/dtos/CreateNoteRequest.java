package com.example.notesapp.app.notes.dtos;


import com.example.notesapp.app.notes.enums.NoteCategory;

public record CreateNoteRequest(String title, String description, NoteCategory noteCategory) {
}
