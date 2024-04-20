package com.pdp.backend.repository.members;

import com.pdp.backend.model.members.Members;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * The MembersRepository class provides functionality to manage Members objects in a repository.
 * It implements the BaseRepository interface for basic CRUD operations.
 * MembersRepository utilizes ListFileHandler for reading from and writing to files.
 *
 * Instances of MembersRepository are initialized with a ListFileHandler for managing Members objects stored in files.
 *
 * This class allows for adding, removing, finding by ID, and retrieving all Members objects.
 * It provides methods to interact with Members objects persisted in files.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
public class MembersRepository implements BaseRepository<Members> {
    private final ListFileHandler<Members> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.MEMBERS);
    private final List<Members> members;
    public MembersRepository() {
        this.members = getAll();

    }
    /**
     * Adds a Members object to the repository.
     * Writes the updated list of members to the file.
     *
     * @param object the Members object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(Members object) {
        members.add(object);
        listFileHandler.writeList(members);
        return true;
    }

    /**
     * Removes a Members object from the repository by ID.
     * Writes the updated list of members to the file.
     *
     * @param id the ID of the Members object to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = members.removeIf(member -> Objects.equals(member.getId(), id));
        if (removeIf) listFileHandler.writeList(members);
        return removeIf;
    }

    /**
     * Finds a Members object in the repository by ID.
     *
     * @param id the ID of the Members object to find
     * @return the Members object if found, or null if not found
     */
    @Override
    public Members findById(UUID id) {
        return members.stream()
                .filter((member) -> Objects.equals(member.getId(),id))
                .findFirst().orElse(null);
    }
    /**
     * Retrieves all Members objects stored in the repository.
     *
     * @return the list of all Members objects stored in the repository
     */
    @Override
    public List<Members> getAll() {
        return listFileHandler.readList();
    }
}
