package com.domvs.library.controller;

import com.domvs.library.model.Book;
import com.domvs.library.service.ImplLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private ImplLibrary library;

    @PostMapping
    public ResponseEntity Book (@RequestBody Book book) {
        try {
            return ResponseEntity.status(201).body(library.saveBook(book));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllBooks() {
        return ResponseEntity.status(200).body(library.getAllBooks());
    }

    @PostMapping("/loan/{userId}/{bookId}")
    public ResponseEntity loanBook(@PathVariable UUID userId, @PathVariable UUID bookId) {
        try {
            library.toLoan(userId, bookId);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.status(200).body("Book loaned");
    }

}
