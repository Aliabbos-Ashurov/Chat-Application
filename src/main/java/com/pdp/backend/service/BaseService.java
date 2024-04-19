package com.pdp.backend.service;

import java.util.List;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  10:31
 **/
public interface BaseService<T> {
    boolean add(T object);
    boolean update(T object);
    boolean delete(UUID id);
    List<T> getAll();
    List<T> search(String query);
    T getById(UUID id);
}
