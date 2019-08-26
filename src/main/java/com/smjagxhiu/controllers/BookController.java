package com.smjagxhiu.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smjagxhiu.model.Book;
import com.smjagxhiu.service.BookService;

@RestController
@RequestMapping(value = "/books")
public class BookController {
	Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/async", method = RequestMethod.GET)
	@Async
	public CompletableFuture<List<Book>> getAll() {
		return CompletableFuture.completedFuture(bookService.getAllBooks());
	}
	

	@RequestMapping(value = "/sync/{id}", method = RequestMethod.GET)
    public @ResponseBody Book searchSync(@PathVariable(name = "id") int id) {
		return bookService.getBookById(id);
    }
}
