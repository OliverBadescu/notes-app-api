package com.example.notesapp.app.users.exceptions;

public class NoUserFound extends RuntimeException {
    public NoUserFound(String message) {
        super(message);
    }
}
