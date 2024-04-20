package com.pdp.backend.repository.subscribers;

import com.pdp.backend.model.subscribers.Subscribers;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * The SubscribersRepository class provides functionality to manage Subscribers objects in a repository.
 * It implements the BaseRepository interface for basic CRUD operations.
 * SubscribersRepository utilizes ListFileHandler for reading from and writing to files.
 *
 * Instances of SubscribersRepository are initialized with a ListFileHandler for managing Subscribers objects stored in files.
 *
 * This class allows for adding, removing, finding by ID, and retrieving all Subscribers objects.
 * It provides methods to interact with Subscribers objects persisted in files.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
public class SubscribersRepository implements BaseRepository<Subscribers> {
    private final ListFileHandler<Subscribers> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.SUBSCRIBERS);
    private final List<Subscribers> subscribers;
    public SubscribersRepository() {
        this.subscribers = getAll();
    }

    /**
     * Adds a Subscribers object to the repository.
     * Writes the updated list of subscribers to the file.
     *
     * @param object the Subscribers object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(Subscribers object) {
        subscribers.add(object);
        listFileHandler.writeList(subscribers);
        return true;
    }

    /**
     * Removes a Subscribers object from the repository by ID.
     * Writes the updated list of subscribers to the file.
     *
     * @param id the ID of the Subscribers object to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = subscribers.removeIf((subscriber) -> Objects.equals(subscriber.getId(), id));
        if (removeIf) listFileHandler.writeList(subscribers);
        return removeIf;
    }

    /**
     * Finds a Subscribers object in the repository by ID.
     *
     * @param id the ID of the Subscribers object to find
     * @return the Subscribers object if found, or null if not found
     */
    @Override
    public Subscribers findById(UUID id) {
        return subscribers.stream()
                .filter((subscriber) -> Objects.equals(subscriber.getId(),id))
                .findFirst().orElse(null);
    }
    
    /**
     * Retrieves all Subscribers objects stored in the repository.
     *
     * @return the list of all Subscribers objects stored in the repository
     */
    @Override
    public List<Subscribers> getAll() {
        return listFileHandler.readList();
    }
}
