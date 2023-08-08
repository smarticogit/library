package com.domvs.library.repository;

import com.domvs.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Book findBookByTitle(String title);

    Book findUserById(UUID bookId);
}
