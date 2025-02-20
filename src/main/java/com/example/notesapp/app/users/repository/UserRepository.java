package com.example.notesapp.app.users.repository;



import com.example.notesapp.app.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
