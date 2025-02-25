package com.example.notesapp.app.notes.model;


import com.example.notesapp.app.notes.enums.NoteCategory;
import com.example.notesapp.app.users.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Table(name = "note")
@Entity(name = "Note")
public class Note {

    @Id
    @SequenceGenerator(
            name = "note_sequence",
            sequenceName = "note_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "note_sequence"
    )

    @Column(
            name = "id"
    )
    private long id;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "create_date",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private LocalDate createDate;

    @Column(
            name = "note_category",
            nullable = false
    )
    private NoteCategory noteCategory;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
