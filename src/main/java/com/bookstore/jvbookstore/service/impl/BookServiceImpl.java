package com.bookstore.jvbookstore.service.impl;

import com.bookstore.jvbookstore.dto.BookDto;
import com.bookstore.jvbookstore.dto.CreateBookRequestDto;
import com.bookstore.jvbookstore.exception.EntityNotFoundException;
import com.bookstore.jvbookstore.mapper.BookDtoMapper;
import com.bookstore.jvbookstore.repository.BookRepository;
import com.bookstore.jvbookstore.service.BookService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookDtoMapper bookDtoMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        return bookDtoMapper.toDto(bookRepository.save(bookDtoMapper.toModel(bookRequestDto)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getById(Long id) {
        return bookRepository.findById(id)
                .map(bookDtoMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't get book with id: " + id));
    }
}
