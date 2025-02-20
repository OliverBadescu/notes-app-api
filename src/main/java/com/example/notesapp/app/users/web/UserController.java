package com.example.notesapp.app.users.web;


import com.example.notesapp.app.users.dtos.*;
import com.example.notesapp.app.users.model.User;
import com.example.notesapp.app.users.services.UserCommandService;
import com.example.notesapp.app.users.services.UserQueryService;
import com.example.notesapp.app.system.jwt.JWTTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.example.notesapp.app.system.constants.Constants.JWT_TOKEN_HEADER;


@RestController
@AllArgsConstructor
@RequestMapping("/notes-app/user")
@CrossOrigin
@Slf4j
public class UserController {
    
    private UserCommandService userCommandService;
    private UserQueryService userQueryService;
    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;



    @GetMapping("/getAllUsers")
    public ResponseEntity<UserResponseList> getAllUsers(){
        return new ResponseEntity<>(userQueryService.getAllUsers(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        String responseMessage = userCommandService.deleteUser(email);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable String email) {
        return new ResponseEntity<>(userCommandService.updateUser(updateUserRequest, email), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest user) {

        User loginUser = userQueryService.findByEmail(user.email());
        User userPrincipal = getUser(loginUser);

        authenticate(user.email(), user.password());
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        LoginResponse loginResponse = new LoginResponse(
                jwtHeader.getFirst(JWT_TOKEN_HEADER),
                userPrincipal.getId(),
                userPrincipal.getFullName(),
                userPrincipal.getPhone(),
                userPrincipal.getEmail(),
                userPrincipal.getUserRole()
        );
        return new ResponseEntity<>(loginResponse, jwtHeader, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody CreateUserRequest createUserRequest){
        this.userCommandService.createUser(createUserRequest);
        User userPrincipal = userQueryService.findByEmail(createUserRequest.email());
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        RegisterResponse registerResponse = new RegisterResponse(
                jwtHeader.getFirst(JWT_TOKEN_HEADER),
                userPrincipal.getFullName(),
                userPrincipal.getPhone(),
                userPrincipal.getEmail(),
                userPrincipal.getUserRole()
        );
        return new ResponseEntity<>(registerResponse, jwtHeader, HttpStatus.CREATED);


    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private HttpHeaders getJwtHeader(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJWTToken(user));
        return headers;
    }

    private User getUser(User loginUser) {
        User userPrincipal = new User();
        userPrincipal.setEmail(loginUser.getEmail());
        userPrincipal.setId(loginUser.getId());
        userPrincipal.setPassword(loginUser.getPassword());
        userPrincipal.setUserRole(loginUser.getUserRole());
        userPrincipal.setFullName(loginUser.getFullName());
        userPrincipal.setPhone(loginUser.getPhone());
        return userPrincipal;
    }
}
