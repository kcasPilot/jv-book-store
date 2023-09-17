package com.bookstore.jvbookstore.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PriceValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {
    String message() default "has invalid format. Allowed only positive digits without spaces."
            + " Fractional part can be separated with '.' only";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
