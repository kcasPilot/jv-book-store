package com.bookstore.jvbookstore.repository;

public interface GenericRepository<T> {
    T save(T entity);
}
