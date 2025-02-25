package com.example.notesapp.app.notes.dtos;

import com.example.notesapp.app.notes.enums.NoteCategory;
import com.example.notesapp.app.users.dtos.UserResponse;


import java.time.LocalDate;

public record NoteResponse(long id, String title, String description, LocalDate date, UserResponse user, NoteCategory noteCategory) {
}
