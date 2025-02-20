package com.example.notesapp.app.users.dtos;


import com.example.notesapp.system.security.UserRole;

public record RegisterResponse(String jwtToken,
                               String fullName,
                               String phone,
                               String email,
                               UserRole role) {
}
