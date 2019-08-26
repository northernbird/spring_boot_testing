package com.smjagxhiu.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.smjagxhiu.service.BookService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookControllerIntegrationTest {

	MockMvc mockMvc;
	
    @Autowired
    protected WebApplicationContext wac;
    
    @Autowired
    BookController bookController;
    
    @Autowired
    BookService bookService;
    
    
    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.bookController).build();// Standalone context
        // mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        // .build();
    }
    
    @Test
    public void testSearchSync() throws Exception {
        mockMvc.perform(get("/books//sync/{id}",1)
        	.contentType(MediaType.APPLICATION_JSON))
        	.andDo(print())
        	.andExpect(status().isOk())
            .andExpect(jsonPath("title", is("Eloquent JavaScript, Second Edition")));
    }
    
    
    @Test
    public void testSearchASync() throws Exception {
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
            .andExpect(jsonPath("$[0].title", is("Eloquent JavaScript, Second Edition")))
            .andExpect(jsonPath("$[1].title", is("Learning JavaScript Design Patterns")));
    }

}
