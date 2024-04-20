package com.pdp.backend.repository.user;

import com.pdp.backend.model.user.User;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * The UserRepository class provides functionality to manage User objects in a repository.
 * It implements the BaseRepository interface for basic CRUD operations.
 * UserRepository utilizes ListFileHandler for reading from and writing to files.
 *
 * Instances of UserRepository are initialized with a ListFileHandler for managing User objects stored in files.
 *
 * This class allows for adding, removing, finding by ID, and retrieving all User objects.
 * It provides methods to interact with User objects persisted in files.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
public class UserRepository implements BaseRepository<User> {
    private final ListFileHandler<User> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.USERS);
    private final List<User> users;
    public UserRepository() {
        this.users = getAll();
    }

    /**
     * Adds a User object to the repository.
     * Writes the updated list of users to the file.
     *
     * @param object the User object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(User object) {
        users.add(object);
        listFileHandler.writeList(users);
        return true;
    }

    /**
     * Removes a User object from the repository by ID.
     * Writes the updated list of users to the file.
     *
     * @param id the ID of the User object to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = users.removeIf((user -> Objects.equals(user.getId(), id)));
        if (removeIf) listFileHandler.writeList(users);
        return removeIf;
    }

    /**
     * Finds a User object in the repository by ID.
     *
     * @param id the ID of the User object to find
     * @return the User object if found, or null if not found
     */
    @Override
    public User findById(UUID id) {
        return users.stream()
                .filter((user -> user.getId().equals(id)))
                .findFirst().orElse(null);
    }

    /**
     * Retrieves all User objects stored in the repository.
     *
     * @return the list of all User objects stored in the repository
     */
    @Override
    public List<User> getAll() {
        return listFileHandler.readList();
    }
}
