package com.smjagxhiu.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.smjagxhiu.model.Book;
import com.smjagxhiu.utils.JsonUtil;




/**
 * Uses SpringBootContextLoader as the default ContextLoader when no specific @ContextConfiguration (loader=…) is defined.
Automatically searches for a @SpringBootConfiguration when nested @Configuration is not used, and no explicit classes are specified.
Allows custom Environment properties to be defined using the properties attribute.
Provides support for different web environment modes, including the ability to start a fully running web server listening on a defined or random port.
Registers a TestRestTemplate and/or WebTestClient bean for use in web tests that are using a fully running web server.
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceUnitTest {

	@Autowired
    private BookService bookService;
    // MockBean is the annotation provided by Spring that wraps mockito one
    // Annotation that can be used to add mocks to a Spring ApplicationContext.
    // If any existing single bean of the same type defined in the context will be replaced by the mock, if no existing bean is defined a new one will be added.
    @MockBean
    private RestTemplate template;
    
    
    @Test
    public void testGetBookById() throws IOException {
        // Parsing mock file
        Book book = JsonUtil.jsonFile2Object("book.json", Book.class);
        // Mocking remote service
        when(template.getForEntity(any(String.class), any(Class.class))).thenReturn(new ResponseEntity(book, HttpStatus.OK));
        Book bookById =bookService.getBookById(2);
        assertThat(bookById).isNotNull()
        	.matches(b -> b.getTitle().toLowerCase().contains("javascript"));
        	//.isNotEmpty()
            //.allMatch(p -> p.getTitle()
            //    .toLowerCase()
            //    .contains("javascript"));
    }

}
