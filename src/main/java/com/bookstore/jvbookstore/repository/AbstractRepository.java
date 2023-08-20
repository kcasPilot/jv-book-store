package com.bookstore.jvbookstore.repository;

import com.bookstore.jvbookstore.exception.DataProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class AbstractRepository<T> implements GenericRepository<T> {
    protected final EntityManagerFactory entityManagerFactory;

    public AbstractRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public T save(T entity) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert " + entity.getClass().getSimpleName()
            + ": " + entity, e);
        }
    }
}
