package com.pdp.backend.service;

import java.util.List;
import java.util.UUID;

/**
 * The BaseService interface defines the basic operations that can be performed on entities.
 * @see com.pdp.backend.service.channel.ChannelService
 * @see com.pdp.backend.service.confirmation.email.ConfirmationEmailService
 * @see com.pdp.backend.service.confirmation.number.ConfirmationNumberService
 * @see com.pdp.backend.service.group.GroupService
 * @see com.pdp.backend.service.members.MembersService
 * @see com.pdp.backend.service.message.MessageService
 * @see com.pdp.backend.service.post.PostService
 * @see com.pdp.backend.service.subscribers.SubscribersService
 * @param <T> The type of entity
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public interface BaseService<T> {

    /**
     * Adds a new entity to the repository.
     *
     * @param object The entity object to be added
     * @return True if the addition is successful, false otherwise
     */
    boolean add(T object);

    /**
     * Updates an existing entity in the repository.
     *
     * @param object The entity object to be updated
     * @return True if the update is successful, false otherwise
     */
    boolean update(T object);

    /**
     * Deletes an entity from the repository based on its ID.
     *
     * @param id The UUID of the entity to be deleted
     * @return True if the deletion is successful, false otherwise
     */
    boolean delete(UUID id);

    /**
     * Retrieves all entities from the repository.
     *
     * @return A list of all entities
     */
    List<T> getAll();

    /**
     * Searches for entities in the repository based on a query.
     *
     * @param query The search query
     * @return A list of entities matching the search query
     */
    List<T> search(String query);

    /**
     * Retrieves an entity from the repository based on its ID.
     *
     * @param id The UUID of the entity to be retrieved
     * @return The entity object if found, null otherwise
     */
    T getById(UUID id);
}
