package com.domvs.library.service;

import com.domvs.library.model.Book;
import com.domvs.library.model.User;
import com.domvs.library.repository.BookRepository;
import com.domvs.library.repository.UserRepository;
import com.domvs.library.service.exception.BookException;
import com.domvs.library.service.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ImplLibrary implements ILibrary {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Book saveBook(Book book) throws BookException, UserException {
        var bookFound = bookRepository.findBookByTitle(book.getTitle());

        if (bookFound != null && bookFound.getTitle().equals(book.getTitle())) {
            throw new BookException("The Book: " + book.getTitle() + " already exists");
        }
        return bookRepository.save(book);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void toLoan(Long userId, Long bookId) throws BookException, UserException {
        var bookFound = bookRepository.findById(bookId).orElseThrow(() -> new BookException("Book not found"));
        var userFound = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));

        var borrowedBooks = userFound.getBorrowedBooks();
        for (var book : borrowedBooks) {
            if (book.getId().equals(bookId)) {
                throw new BookException("The Book: " + book.getTitle() + " is already loaned");
            }
        }

        if(!bookFound.isAvailability()) {
            throw new BookException("The Book: " + bookFound.getTitle() + " is not available");
        }

        if(userFound.getBorrowedBooks().size() >= 5) {
            throw new UserException("Can't loan more than 5 books");
        }

        for (var book : userFound.getBorrowedBooks()) {
            if (book.getDeliveryDate().isBefore(LocalDate.now().minusDays(6))) {
                throw new UserException("Has pending returns");
            }
        }
        bookFound.setAvailableQuantity(bookFound.getAvailableQuantity() - 1);
        bookFound.setDeliveryDate(LocalDate.now().plusDays(6));
        bookRepository.save(bookFound);

        userFound.getBorrowedBooks().add(bookFound);
        userRepository.save(userFound);
    }

    @Override
    public void giveBack(Long userId, Long bookId) throws BookException {
        var userFound = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var bookFound = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        var booksBorrowed = userFound.getBorrowedBooks();
        var bookFoundInList = false;

        for (var book : booksBorrowed) {
            if (book.getId().equals(bookId)) {
                bookFoundInList = true;
                bookFound.setAvailableQuantity(bookFound.getAvailableQuantity() + 1);
                bookFound.setDeliveryDate(null);
                userFound.getBorrowedBooks().remove(book);
                bookRepository.save(book);
            }
        }
        if (!bookFoundInList) {
            throw new BookException("The Book: " + bookFound.getTitle() + " is not loaned");
        }
    }

    public Object getAllBooks() {
        return bookRepository.findAll();
    }

    public Object getAllUsers() {
        return userRepository.findAll();
    }
}
