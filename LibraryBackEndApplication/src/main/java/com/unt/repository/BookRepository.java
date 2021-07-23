package com.unt.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.unt.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>   {

    public Book findByBookId(int bookId);

    @Query(value = "select * from book b where b.book_name like %?1% or b.author like %?1%", nativeQuery = true)
    public Iterable<Book> searchBook(String name);

    @Query(value = "select b.* from book b left join checkin c on b.book_id=c.book_id and c.returned_date is null where c.book_id is null", nativeQuery = true)
    public Iterable<Book> getAvailable();
}
