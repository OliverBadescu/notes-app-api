package com.example.notesapp.app.notes.web;

import com.example.notesapp.app.notes.dtos.CreateNoteRequest;
import com.example.notesapp.app.notes.dtos.NoteResponse;
import com.example.notesapp.app.notes.dtos.NoteResponseList;
import com.example.notesapp.app.notes.dtos.UpdateNoteRequest;
import com.example.notesapp.app.notes.services.NoteCommandService;
import com.example.notesapp.app.notes.services.NoteQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/notes-app/note")
@CrossOrigin
@Slf4j
public class NoteController {

    private NoteQueryService noteQueryService;
    private NoteCommandService noteCommandService;


    @GetMapping("/getAllUserNotes/{userId}")
    public ResponseEntity<NoteResponseList> getAllUserNotes(@PathVariable long userId){
        return new ResponseEntity<>(noteQueryService.getAllUserNotes(userId), HttpStatus.OK);
    }

    @PostMapping("/addNote/{userId}")
    public ResponseEntity<NoteResponse> addNote(@PathVariable long userId, @RequestBody CreateNoteRequest createNoteRequest){
        return new ResponseEntity<>(noteCommandService.addNote(createNoteRequest,userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteNote/{noteId}")
    public ResponseEntity<String> deleteNote(@PathVariable long noteId){
        return new ResponseEntity<>(noteCommandService.deleteNote(noteId), HttpStatus.OK);
    }

    @PutMapping("/updateNote/{noteId}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable long noteId, @RequestBody UpdateNoteRequest updateNoteRequest){
        return new ResponseEntity<>(noteCommandService.updateNote(noteId, updateNoteRequest), HttpStatus.OK);
    }
}
