package com.bookstore.jvbookstore.service.impl;

import com.bookstore.jvbookstore.dto.BookDto;
import com.bookstore.jvbookstore.dto.BookSearchParametersDto;
import com.bookstore.jvbookstore.dto.CreateBookRequestDto;
import com.bookstore.jvbookstore.exception.EntityNotFoundException;
import com.bookstore.jvbookstore.mapper.BookDtoMapper;
import com.bookstore.jvbookstore.model.Book;
import com.bookstore.jvbookstore.repository.SpecificationBuilder;
import com.bookstore.jvbookstore.repository.book.BookRepository;
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
    private final SpecificationBuilder<Book, BookSearchParametersDto> specificationBuilder;

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

    @Override
    public BookDto update(Long id, CreateBookRequestDto bookRequestDto) {
        return bookDtoMapper.toDto(bookRepository.save(bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookRequestDto.getTitle());
                    book.setAuthor(bookRequestDto.getAuthor());
                    book.setIsbn(bookRequestDto.getIsbn());
                    book.setPrice(bookRequestDto.getPrice());
                    book.setDescription(bookRequestDto.getDescription());
                    book.setCoverImage(bookRequestDto.getCoverImage());
                    return book;
                })
                .orElseThrow(() ->
                        new EntityNotFoundException("Can't find book in DB with id: " + id))));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> searchBooks(BookSearchParametersDto searchParametersDto) {
        return searchParametersDto.getIsbn() == null
                ? bookRepository.findAll(specificationBuilder.build(searchParametersDto)).stream()
                .map(bookDtoMapper::toDto)
                .collect(Collectors.toList())
                : List.of(bookDtoMapper.toDto(
                bookRepository.findBookByIsbn(searchParametersDto.getIsbn())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Can't find book with ISBN: " + searchParametersDto.getIsbn()))));
    }
}
