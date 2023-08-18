package com.bookstore.jvbookstore.repository.impl;

import com.bookstore.jvbookstore.exception.DataProcessingException;
import com.bookstore.jvbookstore.model.Book;
import com.bookstore.jvbookstore.repository.AbstractRepository;
import com.bookstore.jvbookstore.repository.BookRepository;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl extends AbstractRepository<Book> implements BookRepository {
    public BookRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Book> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Book.class);
            criteriaQuery.from(Book.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all books", e);
        }
    }
}
