package com.bookstore.jvbookstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchParametersDto {
    private String[] titles;
    private String[] authors;
    private String price;
    private String priceGt;
    private String priceLt;
    private String isbn;
}
