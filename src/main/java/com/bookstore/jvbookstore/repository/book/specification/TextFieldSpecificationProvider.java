package com.bookstore.jvbookstore.repository.book.specification;

import com.bookstore.jvbookstore.model.Book;
import com.bookstore.jvbookstore.repository.Filter;
import com.bookstore.jvbookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TextFieldSpecificationProvider implements SpecificationProvider<Book> {
    private static final String SPECIFICATION_KEY = "text";

    @Override
    public String getKey() {
        return SPECIFICATION_KEY;
    }

    @Override
    public Specification<Book> getSpecification(Filter filter) {
        Specification<Book> specification;
        switch (filter.getQueryOperator()) {
            case IN:
                specification = getInSpecification(filter);
                break;
            case LIKE:
                specification = getLikeSpecification(filter);
                break;
            default:
                throw new RuntimeException("Unsupported filter: " + filter);
        }
        return specification;
    }

    private Specification<Book> getLikeSpecification(Filter filter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(filter.getField()),
                        "%" + filter.getParameters().get(0) + "%");
    }

    private Specification<Book> getInSpecification(Filter filter) {
        return (root, query, criteriaBuilder) ->
                root.get(filter.getField()).in(filter.getParameters());
    }
}
