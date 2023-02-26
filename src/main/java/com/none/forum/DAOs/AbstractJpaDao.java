package com.none.forum.DAOs;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractJpaDao<T extends Serializable> {

    private Class<T> entityClass;

    @PersistenceContext
    EntityManager entityManager;

    protected final void setEntityClass(Class<T> classToSet) {
        this.entityClass = classToSet;
    }

    public T find(long id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + entityClass.getName())
                .getResultList();
    }

    @Transactional
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    public void delete(Long id) {
        T entity = find(id);
        if (entity != null) {
            deleteItem(entity);
        }
    }

    private void deleteItem(T entity) {
        entityManager.remove(entity);
    }

}
