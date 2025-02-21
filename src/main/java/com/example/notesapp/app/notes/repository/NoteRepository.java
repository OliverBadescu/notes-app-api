package com.example.notesapp.app.notes.repository;

import com.example.notesapp.app.notes.model.Note;
import com.example.notesapp.app.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    Optional<List<Note>> getAllByUserId(long userId);

    Optional<Note> findById(long id);
}
