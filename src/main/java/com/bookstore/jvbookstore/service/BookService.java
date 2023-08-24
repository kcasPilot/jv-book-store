package com.bookstore.jvbookstore.service;

import com.bookstore.jvbookstore.dto.BookDto;
import com.bookstore.jvbookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto bookRequestDto);

    List<BookDto> findAll();

    BookDto getById(Long id);
}
