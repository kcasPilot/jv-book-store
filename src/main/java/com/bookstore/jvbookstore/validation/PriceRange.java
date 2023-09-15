package com.bookstore.jvbookstore.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PriceRangeValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceRange {
    String message() default "range has invalid format. It must include minimal and maximal values"
            + " separated with '-' without spaces. Fractional part can be separated with '.' only";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
