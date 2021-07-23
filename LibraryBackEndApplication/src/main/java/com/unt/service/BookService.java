package com.unt.service;

import com.unt.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unt.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBook(int bookId) {
        return bookRepository.findByBookId(bookId);
    }

    public void deleteBook(int bookId) {
        Book book = this.getBook(bookId);
        bookRepository.delete(book);
    }

    public Book updateBook(Book newbook){
        Book oldbook = this.getBook(newbook.getBookId());
        oldbook.setBookName(newbook.getBookName());
        oldbook.setAuthor(newbook.getAuthor());
        oldbook.setPublication(newbook.getPublication());
        return this.addBook(oldbook);
    }

    public List<Book> getallBook() {
        List<Book> allBooks = new ArrayList<Book>();
        bookRepository.findAll().forEach(allBooks::add);
        return allBooks;
    }

    public List<Book> searchBook(String name) {
        List<Book> allBooks = new ArrayList<Book>();
        bookRepository.searchBook(name).forEach(allBooks::add);
        return allBooks;
    }

    public List<Book> getAvailable() {
        List<Book> allBooks = new ArrayList<Book>();
        bookRepository.getAvailable().forEach(allBooks::add);
        return allBooks;
    }
}
