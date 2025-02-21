package com.example.notesapp.app.notes.services;

import com.example.notesapp.app.notes.dtos.CreateNoteRequest;
import com.example.notesapp.app.notes.dtos.NoteResponse;
import com.example.notesapp.app.notes.dtos.UpdateNoteRequest;
import com.example.notesapp.app.notes.exceptions.NoteNotFound;
import com.example.notesapp.app.notes.mapper.NoteMapper;
import com.example.notesapp.app.notes.model.Note;
import com.example.notesapp.app.notes.repository.NoteRepository;
import com.example.notesapp.app.users.exceptions.NoUserFound;
import com.example.notesapp.app.users.model.User;
import com.example.notesapp.app.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class NoteCommandServiceImpl implements NoteCommandService{

    private NoteRepository noteRepository;
    private UserRepository userRepository;

    @Override
    public NoteResponse addNote(CreateNoteRequest createNoteRequest, long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoUserFound("No user with this id found"));

        Note note = Note.builder()
                .createDate(LocalDate.now())
                .description(createNoteRequest.description())
                .user(user)
                .title(createNoteRequest.title()).build();

        noteRepository.saveAndFlush(note);

        return NoteMapper.noteToResponse(note);

    }

    @Override
    public String deleteNote(long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFound("No note with this id found"));

        noteRepository.delete(note);

        return "Deleted note with id: " + id;
    }

    @Override
    public NoteResponse updateNote(long id, UpdateNoteRequest updateNoteRequest) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFound("No note with this id found"));

        note.setDescription(updateNoteRequest.description());
        note.setTitle(updateNoteRequest.title());

        noteRepository.save(note);

        return NoteMapper.noteToResponse(note);

    }
}
