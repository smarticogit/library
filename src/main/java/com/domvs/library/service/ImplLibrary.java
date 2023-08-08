package com.domvs.library.service;

import com.domvs.library.model.Book;
import com.domvs.library.model.User;
import com.domvs.library.repository.BookRepository;
import com.domvs.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ImplLibrary implements ILibrary {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Book saveBook(Book book) {
        var bookFound = bookRepository.findBookByTitle(book.getTitle());

        if (bookFound != null && bookFound.getTitle().equals(book.getTitle())) {
            throw new RuntimeException("The Book: " + book.getTitle() + " already exists");
        }
        return bookRepository.save(book);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void toLoan(UUID userId, UUID bookId) {
        var bookFound = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        var userFound = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (!bookFound.isAvailability()) {
            throw new RuntimeException("The Book: " + bookFound.getTitle() + " is not available");
        }

        if(userFound.getBorrowedBooks().contains(bookFound.getTitle())) {
            throw new RuntimeException("The Book: " + bookFound.getTitle() + " is already loaned");
        }

        if(userFound.getBorrowedBooks().size() >= 5) {
            throw new RuntimeException("Can't loan more than 5 books");
        }

        bookFound.setAvailableQuantity(bookFound.getAvailableQuantity() - 1);
        bookFound.setDeliveryDate(LocalDate.now().plusDays(6));
        bookRepository.save(bookFound);
        userFound.getBorrowedBooks().add(bookFound);
        userRepository.save(userFound);
    }

    @Override
    public void giveBack(User user, Book book) {

    }

    public Object getAllBooks() {
        return bookRepository.findAll();
    }

    public Object getAllUsers() {
        return userRepository.findAll();
    }
}
