package com.bookstore.jvbookstore.service.impl;

import com.bookstore.jvbookstore.model.Book;
import com.bookstore.jvbookstore.repository.BookRepository;
import com.bookstore.jvbookstore.service.BookService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
