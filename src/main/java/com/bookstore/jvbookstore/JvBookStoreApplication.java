package com.bookstore.jvbookstore;

import com.bookstore.jvbookstore.model.Book;
import com.bookstore.jvbookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JvBookStoreApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(JvBookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setIsbn("ISBN 000 111");
            book.setTitle("Some book");
            book.setPrice(BigDecimal.valueOf(100));
            book.setAuthor("Author");
            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
