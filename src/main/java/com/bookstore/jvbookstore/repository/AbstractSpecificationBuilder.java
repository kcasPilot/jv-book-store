package com.bookstore.jvbookstore.repository;

import java.util.List;

public abstract class AbstractSpecificationBuilder {
    protected Filter getFilter(String field, QueryOperator queryOperator, List<String> params) {
        Filter filter = new Filter();
        filter.setField(field);
        filter.setQueryOperator(queryOperator);
        filter.setParameters(params);
        return filter;
    }
}
