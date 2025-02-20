package com.example.notesapp.app.users.services;


import com.example.notesapp.app.users.dtos.CreateUserRequest;
import com.example.notesapp.app.users.dtos.UpdateUserRequest;
import com.example.notesapp.app.users.dtos.UserResponse;

public interface UserCommandService {

    void createUser(CreateUserRequest createUserRequest);

    String deleteUser(String email);

    UserResponse updateUser(UpdateUserRequest updateUserRequest, String email);

}
