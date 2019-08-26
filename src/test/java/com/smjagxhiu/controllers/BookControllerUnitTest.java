package com.smjagxhiu.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.smjagxhiu.model.Book;
import com.smjagxhiu.service.BookService;

/**
 * The controller part is the part of the system that manages 
 * HttpRequest, so we need a system to simulate this behavior 
 * without starting a full HTTP server.
 * MockMvc is the Spring class that does that. 
 * It can be set up in different ways:
 *  1. Using Standalone Context.
	2. Using WebApplication Context. 
	3. Let Spring autoconfigure it by loading all context 
		by using these annotations on test class 
		@SpringBootTest @AutoConfigureMockMvc. 
		@AutoConfigureMockMvc uses the same builder that you use 
		manually on point 2
	4. Let Spring autoconfigure it by loading just the web layer 
		context by using these annotations on the test class 
		@WebMvcTest
	In the points 1 and 2, the MockMvc is not injected but 
	manually built using builders.In the points 3 and 4, 
	the MockMvc is a spring Bean generated by the framework.
	Use 1 when you want control over bean creation, 
	otherwise use 4. Both limits the number of beans loaded.
	If you want to test all layers chose between 3 and 4.
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookControllerUnitTest {

	MockMvc mockMvc;
	
    @Autowired
    protected WebApplicationContext wac;
    
    @Autowired
    BookController bookController;
    
    @MockBean
    BookService bookService;
    
    /**
     * List of samples books
     */
    private List<Book> books;
    
    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.bookController).build();// Standalone context
        // mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        // .build();
        Book book1 = Book.builder()
            .title("Eloquent JavaScript")
            .isbn("9781593275846")
            .id(1)
            .author("Marijn Haverbeke")
            .description("JavaScript lies at the heart of almost every modern web application...")
            .pages(472)
            .publisher("No Starch Press")
            .build();
        Book book2 = Book.builder()
                .title("Learning JavaScript Design Patterns")
                .isbn("9781449331818")
                .id(2)
                .author("Addy Osmani")
                .description("With Learning JavaScript Design Patterns, you'll learn how to write beautiful, structured, and maintainable JavaScript...")
                .pages(254)
                .publisher("O'Reilly Media")
                .build();
        books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
    }
    
    @Test
    public void testSearchSync() throws Exception {
        // Mocking service
        when(bookService.getBookById(1)).thenReturn(books.get(0));
        mockMvc.perform(get("/books//sync/{id}",1)
        	.contentType(MediaType.APPLICATION_JSON))
        	.andDo(print())
        	.andExpect(status().isOk())
            .andExpect(jsonPath("title", is("Eloquent JavaScript")));
    }
    
    
    @Test
    public void testSearchASync() throws Exception {
        // Mocking service
        when(bookService.getAllBooks()).thenReturn(books);
        MvcResult result = mockMvc.perform(get("/books/async")
        	.contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(request().asyncStarted())
            .andDo(print())
            // .andExpect(status().is2xxSuccessful()).andReturn();
            .andReturn();
        // result.getRequest().getAsyncContext().setTimeout(10000);
        mockMvc.perform(asyncDispatch(result))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title", is("Eloquent JavaScript")))
            .andExpect(jsonPath("$[1].title", is("Learning JavaScript Design Patterns")));
    }

}
