package com.example.notesapp.app.users.services;

import com.example.notesapp.app.users.dtos.UserResponse;
import com.example.notesapp.app.users.dtos.UserResponseList;
import com.example.notesapp.app.users.exceptions.NoUserFound;
import com.example.notesapp.app.users.mapper.UserMapper;
import com.example.notesapp.app.users.model.User;
import com.example.notesapp.app.users.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserQueryServiceImpl implements UserQueryService{

    private UserRepository userRepository;

    @Override
    public UserResponse findUserById(long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoUserFound("No user with this id found"));

        return UserMapper.userToResponseDto(user);
    }

    @Override
    public UserResponseList getAllUsers() {
        List<User> list = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();

        list.forEach(user -> {
            responses.add(UserMapper.userToResponseDto(user));
        });

        return new UserResponseList(responses);
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email).orElseThrow(() -> new NoUserFound("No user with this email found"));
    }
}
