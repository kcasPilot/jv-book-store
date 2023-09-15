package com.bookstore.jvbookstore.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateBookRequestDto {
    @NotBlank(message = "can't be null, empty or blank")
    private String title;
    @NotBlank(message = "can't be null, empty or blank")
    private String author;
    @Size(min = 9, max = 13, message = "must be between 9 to 13")
    private String isbn;
    @NotNull(message = "can't be null")
    @DecimalMin(value = "0", message = "can't be less than 0")
    private BigDecimal price;
    private String description;
    @URL(message = "has invalid URL format")
    private String coverImage;
}
