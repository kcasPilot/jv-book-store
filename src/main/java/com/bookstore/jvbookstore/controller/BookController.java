package com.bookstore.jvbookstore.controller;

import com.bookstore.jvbookstore.dto.BookDto;
import com.bookstore.jvbookstore.dto.BookSearchParametersDto;
import com.bookstore.jvbookstore.dto.CreateBookRequestDto;
import com.bookstore.jvbookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Books manager", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Get all books", description = "Get all books. "
            + "Supports pagination and sorting")
    @GetMapping
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @Operation(summary = "Get book by id", description = "Get book by id")
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @Operation(summary = "Create book", description = "Create a new book")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookRequestDto) {
        return bookService.save(bookRequestDto);
    }

    @Operation(summary = "Update book by id", description = "Update book by id")
    @PutMapping("/{id}")
    public BookDto updateBookById(@PathVariable Long id,
                                  @RequestBody CreateBookRequestDto bookRequestDto) {
        return bookService.update(id, bookRequestDto);
    }

    @Operation(summary = "Delete book by id", description = "Delete book by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @Operation(summary = "Search books with filter", description = "Search books with filter."
            + " Supports pagination and sorting")
    @GetMapping("/search")
    public List<BookDto> searchBooks(@Valid BookSearchParametersDto searchParametersDto,
                                     Pageable pageable) {
        return bookService.searchBooks(searchParametersDto, pageable);
    }
}
