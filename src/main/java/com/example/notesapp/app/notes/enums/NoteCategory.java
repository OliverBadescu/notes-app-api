package com.example.notesapp.app.notes.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NoteCategory {

    BUSINESS("business"),
    SOCIAL("social"),
    IMPORTANT("important");

    private final String NoteCategory;

}
