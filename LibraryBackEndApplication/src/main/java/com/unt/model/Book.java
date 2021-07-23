package com.unt.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name="book")
public @Data
class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bookId")
	private Integer bookId;
	private String bookName;
	private String author;
	private String publication;
	

}
