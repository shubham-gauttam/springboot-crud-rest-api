package com.example.CrudWithRest.controller;

import com.example.CrudWithRest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.CrudWithRest.entities.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {

        // password encode
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // default role
        if(user.getRole() == null) {
            user.setRole("ROLE_USER");
        }

        User savedUser = userService.createUser(user);

        return ResponseEntity.status(201).body(savedUser);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.createUser(user));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
