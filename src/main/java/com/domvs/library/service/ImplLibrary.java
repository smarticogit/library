package com.domvs.library.service;

import com.domvs.library.model.Book;
import com.domvs.library.model.User;
import com.domvs.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplLibrary implements ILibrary {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Book saveBook(Book book) {
        var bookFound = bookRepository.findBookByTitle(book.getTitle());

        if (bookFound.getTitle().equals(book.getTitle())) {
            throw new RuntimeException("The Book: " + book.getTitle() + " already exists");
        }
        return bookRepository.save(book);
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public void toLoan(User user, Book book) {

    }

    @Override
    public void giveBack(User user, Book book) {

    }
}
