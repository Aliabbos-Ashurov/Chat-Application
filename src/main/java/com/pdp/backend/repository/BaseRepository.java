package com.pdp.backend.repository;

import java.util.List;
import java.util.UUID;

/**
 * The BaseRepository interface defines the contract for repositories handling entity objects.
 * It provides methods for basic CRUD operations: add, remove, find by ID, and retrieve all objects.
 *
 * Implementing classes should provide concrete implementations for these methods to handle specific entity types.
 *
 * @param <E> the type of entity objects handled by the repository
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
public interface BaseRepository<E>{

    /**
     * Adds an object of type E to the repository.
     *
     * @param object the object to add to the repository
     * @return true if the addition was successful, false otherwise
     */
    boolean add(E object);

    /**
     * Removes an object from the repository by its ID.
     *
     * @param id the ID of the object to remove
     * @return true if the removal was successful, false otherwise
     */
    boolean remove(UUID id);

    /**
     * Finds an object in the repository by its ID.
     *
     * @param id the ID of the object to find
     * @return the object if found, or null if not found
     */
    E findById(UUID id);

    /**
     * Retrieves all objects stored in the repository.
     *
     * @return a list of all objects stored in the repository
     */
    List<E> getAll();
}
