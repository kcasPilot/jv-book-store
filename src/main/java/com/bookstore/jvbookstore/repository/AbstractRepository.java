package com.bookstore.jvbookstore.repository;

import com.bookstore.jvbookstore.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractRepository<T> implements GenericRepository<T> {
    protected final SessionFactory sessionFactory;

    public AbstractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T save(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert " + entity.getClass().getSimpleName()
                    + " :" + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
