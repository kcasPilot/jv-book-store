package com.bookstore.jvbookstore.repository;

import com.bookstore.jvbookstore.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends GenericRepository<Book> {
    List<Book> findAll();

    Optional<Book> findById(Long id);
}
