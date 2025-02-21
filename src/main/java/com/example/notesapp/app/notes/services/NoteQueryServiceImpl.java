package com.example.notesapp.app.notes.services;

import com.example.notesapp.app.notes.dtos.NoteResponse;
import com.example.notesapp.app.notes.dtos.NoteResponseList;
import com.example.notesapp.app.notes.exceptions.NoteNotFound;
import com.example.notesapp.app.notes.mapper.NoteMapper;
import com.example.notesapp.app.notes.model.Note;
import com.example.notesapp.app.notes.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteQueryServiceImpl implements NoteQueryService{

    private NoteRepository noteRepository;
    @Override
    public NoteResponseList getAllUserNotes(long userId) {

        Optional<List<Note>> list = noteRepository.getAllByUserId(userId);
        List<NoteResponse> notes = new ArrayList<>();

        if(list.isPresent()){
            list.get().forEach(note -> {
                notes.add(NoteMapper.noteToResponse(note));
            });
        }else{
            throw new NoteNotFound("No notes found for this user");
        }

        return new NoteResponseList(notes);
    }
}
