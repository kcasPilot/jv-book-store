package com.bookstore.jvbookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PriceValidator implements ConstraintValidator<Price, String> {
    private static final String PRICE_FORMAT_PATTERN = "\\d+(\\.\\d+)?";

    @Override
    public boolean isValid(String price, ConstraintValidatorContext constraintValidatorContext) {
        return price == null || Pattern.matches(PRICE_FORMAT_PATTERN, price);
    }
}
