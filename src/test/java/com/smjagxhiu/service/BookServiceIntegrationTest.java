package com.smjagxhiu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.smjagxhiu.model.Book;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceIntegrationTest {

	@Autowired
	private BookService bookService;

	@Test
	public void testGetBooks() {
		List<Book> booksByTitle = bookService.getAllBooks();
		assertThat(booksByTitle).isNotNull().isNotEmpty();
	}
	
	@Test
	public void testGetBookById() {
		Book booksByTitle = bookService.getBookById(1);
		assertThat(booksByTitle).isNotNull()
			.matches(book -> 
				book.getId() == 1 && 
				book.getTitle().equals("Eloquent JavaScript, Second Edition")
			);
	}

}
