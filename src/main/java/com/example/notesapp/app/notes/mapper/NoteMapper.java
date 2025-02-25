package com.example.notesapp.app.notes.mapper;

import com.example.notesapp.app.notes.dtos.NoteResponse;
import com.example.notesapp.app.notes.model.Note;
import com.example.notesapp.app.users.mapper.UserMapper;

public class NoteMapper {

    public static NoteResponse noteToResponse(Note note){
        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.getCreateDate(),
                UserMapper.userToResponseDto(note.getUser()),
                note.getNoteCategory()
        );
    }
}
