package com.example.notesapp.app.users.services;

import com.example.notesapp.app.users.dtos.CreateUserRequest;
import com.example.notesapp.app.users.dtos.UpdateUserRequest;
import com.example.notesapp.app.users.dtos.UserResponse;
import com.example.notesapp.app.users.exceptions.NoUserFound;
import com.example.notesapp.app.users.exceptions.UserAlreadyExists;
import com.example.notesapp.app.users.mapper.UserMapper;
import com.example.notesapp.app.users.model.User;
import com.example.notesapp.app.users.repository.UserRepository;
import com.example.notesapp.app.system.security.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCommandServiceImpl implements UserCommandService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        if (userRepository.existsByEmail(createUserRequest.email())) {
            throw new UserAlreadyExists("User with this email already exists!");
        }

        User newUser = User.builder()
                .fullName(createUserRequest.fullName())
                .email(createUserRequest.email())
                .phone(createUserRequest.phone())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .userRole(UserRole.USER)
                .build();

        userRepository.saveAndFlush(newUser);
    }

    @Override
    public String deleteUser(String email) {
        User userToDelete = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoUserFound("No user with this email found"));

        userRepository.delete(userToDelete);

        return "User with email: " + email + " has been deleted!";
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoUserFound("No user with this email found"));

        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPhone(request.phone());


        User savedUser = userRepository.save(user);

        return UserMapper.userToResponseDto(savedUser);
    }


}
