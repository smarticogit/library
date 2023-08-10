package com.domvs.library.controller;

import com.domvs.library.model.User;
import com.domvs.library.service.ImplLibrary;
import com.domvs.library.service.exception.UserNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private ImplLibrary library;

    @PostMapping
    public ResponseEntity User (@RequestBody User user) {
        try {
            return ResponseEntity.status(201).body(library.saveUser(user));
        } catch (UserNotFountException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(library.getAllUsers());
    }

}
