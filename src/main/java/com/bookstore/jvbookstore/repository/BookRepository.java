package com.bookstore.jvbookstore.repository;

import com.bookstore.jvbookstore.model.Book;
import java.util.List;

public interface BookRepository extends GenericRepository<Book> {
    List<Book> findAll();
}
