package org.training.model.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    T save(T entity);
    T findById(long id);
    List<T> findAll();
    List<T> findAll(int offset, int qRecords);
    void update(T entity);
    boolean delete(long id);
}
