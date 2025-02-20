package com.example.notesapp.app.global_exceptions;

import com.example.notesapp.app.users.exceptions.AccessDeniedException;
import com.example.notesapp.app.users.exceptions.NoUserFound;
import com.example.notesapp.app.users.exceptions.UserAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler({NoUserFound.class})
    public ResponseEntity<Object> handleUserNotFoundException(NoUserFound exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({UserAlreadyExists.class})
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExists exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccesDeniedException(AccessDeniedException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(exception.getMessage());
    }
}
