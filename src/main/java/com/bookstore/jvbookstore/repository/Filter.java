package com.bookstore.jvbookstore.repository;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Filter {
    private String field;
    private QueryOperator queryOperator;
    private List<String> parameters;
}
