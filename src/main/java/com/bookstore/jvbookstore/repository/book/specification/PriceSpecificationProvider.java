package com.bookstore.jvbookstore.repository.book.specification;

import com.bookstore.jvbookstore.model.Book;
import com.bookstore.jvbookstore.repository.Filter;
import com.bookstore.jvbookstore.repository.SpecificationProvider;
import java.math.BigDecimal;
import java.util.regex.Pattern;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Book> {
    private static final String SPECIFICATION_KEY = "price";
    private static final String PRICE_RANGE_FORMAT_PATTERN = "\\d+(.\\d+)?-\\d+(.\\d+)?";
    private static final String PRICE_FORMAT_PATTERN = "\\d+(.\\d+)?";

    public String getKey() {
        return SPECIFICATION_KEY;
    }

    @Override
    public Specification<Book> getSpecification(Filter filter) {
        Specification<Book> specification;
        switch (filter.getQueryOperator()) {
            case BETWEEN:
                specification = getBetweenSpecification(filter);
                break;
            case GREATER_THAN:
                specification = getGreaterThanSpecification(filter);
                break;
            case LESS_THAN:
                specification = getLessThanSpecification(filter);
                break;
            default:
                throw new RuntimeException("Unsupported filter: " + filter);
        }
        return specification;
    }

    private Specification<Book> getBetweenSpecification(Filter filter) {
        String params = filter.getParameters().get(0);
        checkPriceRangeFormat(params);
        String[] values = params.split("-");
        BigDecimal priceFrom = new BigDecimal(values[0]);
        BigDecimal priceTo = new BigDecimal(values[1]);
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get(filter.getField()),
                        priceFrom.min(priceTo), priceFrom.max(priceTo));
    }

    private Specification<Book> getGreaterThanSpecification(Filter filter) {
        String priceValue = filter.getParameters().get(0);
        checkPriceFormat(priceValue);
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.gt(root.get(filter.getField()), new BigDecimal(priceValue));
    }

    private Specification<Book> getLessThanSpecification(Filter filter) {
        String priceValue = filter.getParameters().get(0);
        checkPriceFormat(priceValue);
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lt(root.get(filter.getField()), new BigDecimal(priceValue));
    }

    private void checkPriceRangeFormat(String priceRange) {
        if (!Pattern.matches(PRICE_RANGE_FORMAT_PATTERN, priceRange)) {
            throw new RuntimeException("Unsupported format of price range: '" + priceRange
                    + "' It should be like ex.: price=15-20 "
                    + "and values can also have point like ex.: price=15.99-20.00");
        }
    }

    private void checkPriceFormat(String value) {
        if (!Pattern.matches(PRICE_FORMAT_PATTERN, value)) {
            throw new RuntimeException("Unsupported price format: " + value
            + " Correct format is like ex.: 15.99 or 15");
        }
    }
}
