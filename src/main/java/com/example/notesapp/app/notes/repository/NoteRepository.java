package com.example.notesapp.app.notes.repository;

import com.example.notesapp.app.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {
}
