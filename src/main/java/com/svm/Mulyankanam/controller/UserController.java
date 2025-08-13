package com.svm.Mulyankanam.controller;

import com.svm.Mulyankanam.model.User;
import com.svm.Mulyankanam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User savedUser = userService.addUser(newUser);
        return ResponseEntity.ok(savedUser);
    }
}
