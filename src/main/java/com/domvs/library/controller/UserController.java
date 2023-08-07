package com.domvs.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public ResponseEntity String() {
        return ResponseEntity.status(200).body("OK");
    }
}
