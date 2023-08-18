package com.bookstore.jvbookstore.service;

import com.bookstore.jvbookstore.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
