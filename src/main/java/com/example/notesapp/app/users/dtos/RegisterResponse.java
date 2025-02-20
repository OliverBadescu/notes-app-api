package com.example.notesapp.app.users.dtos;


import com.example.notesapp.app.system.security.UserRole;

public record RegisterResponse(String jwtToken,
                               String fullName,
                               String phone,
                               String email,
                               UserRole role) {
}
