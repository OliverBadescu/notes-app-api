package com.example.notesapp.app.users.services;


import com.example.notesapp.app.users.dtos.UserResponse;
import com.example.notesapp.app.users.dtos.UserResponseList;
import com.example.notesapp.app.users.model.User;

public interface UserQueryService {

    UserResponse findUserById(long id);

    UserResponseList getAllUsers();

    User findByEmail(String email);

}
