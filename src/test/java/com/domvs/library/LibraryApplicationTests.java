package com.domvs.library;

import com.domvs.library.model.Book;
import com.domvs.library.model.User;
import com.domvs.library.repository.BookRepository;
import com.domvs.library.service.ImplLibrary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LibraryApplicationTests {

	@InjectMocks
	private ImplLibrary libraryApplication;

	@Mock
	private BookRepository bookRepository;

	private User user;
	private Book book;

	@BeforeEach
	void setUp() {
		user = new User(2L, "John");
		book = new Book(1L, "Sample Book Title", user); // Assume que você também tem uma classe Book com um construtor adequado

		user.getBorrowedBooks().add(book); // Empresta o livro ao usuário
	}

	private
	@Test
	void shouldSaveBook() {

	}

}
