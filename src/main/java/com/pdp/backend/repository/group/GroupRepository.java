package com.pdp.backend.repository.group;

import com.pdp.backend.model.group.Group;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * The GroupRepository class provides functionality to manage Group objects in a repository.
 * It implements the BaseRepository interface for basic CRUD operations.
 * GroupRepository utilizes ListFileHandler for reading from and writing to files.
 *
 * Instances of GroupRepository are initialized with a ListFileHandler for managing Group objects stored in files.
 *
 * This class allows for adding, removing, finding by ID, and retrieving all Group objects.
 * It provides methods to interact with Group objects persisted in files.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
public class GroupRepository implements BaseRepository<Group> {
    private final ListFileHandler<Group> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.GROUPS);
    private final List<Group> groups;
    public GroupRepository() {
        groups = getAll();
    }

    /**
     * Adds a Group object to the repository.
     * Writes the updated list of groups to the file.
     *
     * @param object the Group object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(Group object) {
        groups.add(object);
        listFileHandler.writeList(groups);
        return true;
    }

    /**
     * Removes a Group object from the repository by ID.
     * Writes the updated list of groups to the file.
     *
     * @param id the ID of the Group object to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = groups.removeIf((group -> Objects.equals(group.getId(), id)));
        if (removeIf) listFileHandler.writeList(groups);
        return removeIf;
    }

    /**
     * Finds a Group object in the repository by ID.
     *
     * @param id the ID of the Group object to find
     * @return the Group object if found, or null if not found
     */
    @Override
    public Group findById(UUID id) {
        return groups.stream()
                .filter((group -> Objects.equals(group.getId(),id)))
                .findFirst().orElse(null);
    }

    /**
     * Retrieves all Group objects stored in the repository.
     *
     * @return the list of all Group objects stored in the repository
     */
    @Override
    public List<Group> getAll() {
        return listFileHandler.readList();
    }
}
