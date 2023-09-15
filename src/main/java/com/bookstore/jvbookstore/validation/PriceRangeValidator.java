package com.bookstore.jvbookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PriceRangeValidator implements ConstraintValidator<PriceRange, String> {
    private static final String PRICE_RANGE_FORMAT_PATTERN = "\\d+(\\.\\d+)?-\\d+(\\.\\d+)?";

    @Override
    public boolean isValid(String priceRange,
                           ConstraintValidatorContext constraintValidatorContext) {
        return priceRange == null || Pattern.matches(PRICE_RANGE_FORMAT_PATTERN, priceRange);
    }
}
