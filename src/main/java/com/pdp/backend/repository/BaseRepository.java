package com.pdp.backend.repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  12:51
 **/
public interface BaseRepository<E>{
    boolean add(E object);
    boolean remove(UUID id);
    E findById(UUID id);
    List<E> getAll();
}
