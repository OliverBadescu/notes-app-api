package com.example.notesapp.app.users.dtos;


import com.example.notesapp.app.system.security.UserRole;

public record LoginResponse(String jwtToken,
                            Long id,
                            String fullName,
                            String phone,
                            String email,
                            UserRole role) {
}
