package com.domvs.library.repository;

import com.domvs.library.model.Book;
import com.domvs.library.service.exception.BookException;
import com.domvs.library.service.exception.UserException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitle(String title);
}
