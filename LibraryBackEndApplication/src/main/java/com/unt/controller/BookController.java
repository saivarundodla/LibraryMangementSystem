package com.unt.controller;

import com.unt.model.Book;
import com.unt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin()
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public Book getBook(@PathVariable("id") Integer bookId) {
        return bookService.getBook(bookId);
    }

    @RequestMapping(value = "/all", method= RequestMethod.GET)
    public List<Book> getallBook() {
        return bookService.getallBook();
    }

    @RequestMapping(value = "/available", method= RequestMethod.GET)
    public List<Book> getAvailable() {
        return bookService.getAvailable();
    }

    @RequestMapping(value = "search/{name}", method= RequestMethod.GET)
    public List<Book> searchBook(@PathVariable("name") String name){
        return bookService.searchBook(name);
    }

    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @RequestMapping(value = "/delete/{bookId}", method= RequestMethod.DELETE)
    public void addBook(@PathVariable Integer bookId) {
         bookService.deleteBook(bookId);
    }

    @RequestMapping(value = "/update", method= RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

}
