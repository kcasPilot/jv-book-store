package com.bookstore.jvbookstore.mapper;

import com.bookstore.jvbookstore.config.MapperConfig;
import com.bookstore.jvbookstore.dto.BookDto;
import com.bookstore.jvbookstore.dto.CreateBookRequestDto;
import com.bookstore.jvbookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookDtoMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookRequestDto);
}
