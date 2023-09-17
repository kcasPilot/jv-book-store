package com.bookstore.jvbookstore.service;

import com.bookstore.jvbookstore.dto.BookDto;
import com.bookstore.jvbookstore.dto.BookSearchParametersDto;
import com.bookstore.jvbookstore.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto bookRequestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getById(Long id);

    BookDto update(Long id, CreateBookRequestDto bookRequestDtoDto);

    void delete(Long id);

    List<BookDto> searchBooks(BookSearchParametersDto searchParametersDto,
                              Pageable pageable);
}
