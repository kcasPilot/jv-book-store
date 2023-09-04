package com.bookstore.jvbookstore.repository.book;

import com.bookstore.jvbookstore.dto.BookSearchParametersDto;
import com.bookstore.jvbookstore.model.Book;
import com.bookstore.jvbookstore.repository.AbstractSpecificationBuilder;
import com.bookstore.jvbookstore.repository.QueryOperator;
import com.bookstore.jvbookstore.repository.SpecificationBuilder;
import com.bookstore.jvbookstore.repository.SpecificationProviderManager;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder extends AbstractSpecificationBuilder
        implements SpecificationBuilder<Book, BookSearchParametersDto> {
    private static final String AUTHOR_AND_TITLES_SPECIFICATION_PROVIDER_KEY = "text";
    private static final String PRICE_SPECIFICATION_PROVIDER_KEY = "price";
    private static final String PRICE_FIELD = "price";
    private static final String AUTHOR_FIELD = "author";
    private static final String TITLE_FIELD = "title";
    private final SpecificationProviderManager<Book> specificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParametersDto) {
        Specification<Book> specification = Specification.where(null);
        if (searchParametersDto.getAuthors() != null
                && searchParametersDto.getAuthors().length > 0) {
            specification = specification.and(specificationProviderManager
                    .getSpecificationProvider(AUTHOR_AND_TITLES_SPECIFICATION_PROVIDER_KEY)
                    .getSpecification(getFilter(AUTHOR_FIELD,
                            searchParametersDto.getAuthors().length == 1
                                    ? QueryOperator.LIKE : QueryOperator.IN,
                            Arrays.asList(searchParametersDto.getAuthors()))));
        }
        if (searchParametersDto.getTitles() != null && searchParametersDto.getTitles().length > 0) {
            specification = specification.and(specificationProviderManager
                    .getSpecificationProvider(AUTHOR_AND_TITLES_SPECIFICATION_PROVIDER_KEY)
                    .getSpecification(getFilter(TITLE_FIELD,
                            searchParametersDto.getTitles().length == 1
                                    ? QueryOperator.LIKE : QueryOperator.IN,
                            Arrays.asList(searchParametersDto.getTitles()))));
        }
        if (searchParametersDto.getPrice() != null) {
            specification = specification.and(specificationProviderManager
                    .getSpecificationProvider(PRICE_SPECIFICATION_PROVIDER_KEY)
                    .getSpecification(getFilter(PRICE_FIELD, QueryOperator.BETWEEN,
                            List.of(searchParametersDto.getPrice()))));
        }
        if (searchParametersDto.getPriceGt() != null) {
            specification = specification.and(specificationProviderManager
                    .getSpecificationProvider(PRICE_SPECIFICATION_PROVIDER_KEY)
                    .getSpecification(getFilter(PRICE_FIELD, QueryOperator.GREATER_THAN,
                            List.of(searchParametersDto.getPriceGt()))));
        }
        if (searchParametersDto.getPriceLt() != null) {
            specification = specification.and(specificationProviderManager
                    .getSpecificationProvider(PRICE_SPECIFICATION_PROVIDER_KEY)
                    .getSpecification(getFilter(PRICE_FIELD, QueryOperator.LESS_THAN,
                            List.of(searchParametersDto.getPriceLt()))));
        }
        return specification;
    }
}
