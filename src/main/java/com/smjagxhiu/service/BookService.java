package com.smjagxhiu.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smjagxhiu.model.Book;
import com.smjagxhiu.model.BookResult;

@Service
public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);
    private static final String BOOK_URL="https://my-json-server.typicode.com/smjagxhiu/Fake-Rest-Api/books/";
    
    
    @Autowired
    RestTemplate restTemplate;
    
    
    public Book getBookById(int id) {
    	Book book = restTemplate.getForEntity(BOOK_URL+id, Book.class).getBody();
    	System.out.println(book);
        return book;
    }


	public List<Book> getAllBooks() {
		ResponseEntity<List<Book>> bookResponse =
		        restTemplate.exchange(BOOK_URL,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {
		            });
		List<Book> books = bookResponse.getBody();
		System.out.println(books);
		return books;
	}
}
