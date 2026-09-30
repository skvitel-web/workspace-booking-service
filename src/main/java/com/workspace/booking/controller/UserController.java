package com.workspace.booking.controller;

import com.workspace.booking.dto.UserRequestDto;
import com.workspace.booking.entity.User;
import com.workspace.booking.repository.UserRepository;
import com.workspace.booking.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequestDto requestDto){

        User user = userService.createUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){

        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


}
