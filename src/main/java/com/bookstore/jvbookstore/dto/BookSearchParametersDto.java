package com.bookstore.jvbookstore.dto;

import com.bookstore.jvbookstore.validation.Price;
import com.bookstore.jvbookstore.validation.PriceRange;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchParametersDto {
    private String[] titles;
    private String[] authors;
    @PriceRange
    private String price;
    @Price
    private String priceGt;
    @Price
    private String priceLt;
    @Size(min = 9, max = 13, message = "must be between 9 to 13")
    private String isbn;
}
