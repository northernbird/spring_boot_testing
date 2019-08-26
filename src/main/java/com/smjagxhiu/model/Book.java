package com.smjagxhiu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
	private int id;
    private String title;
    private String description;
    private String isbn;
    private String author;
    private String publisher;
    private Integer pages;
}
