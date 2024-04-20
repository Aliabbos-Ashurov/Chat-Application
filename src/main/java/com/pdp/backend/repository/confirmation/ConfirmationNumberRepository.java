package com.pdp.backend.repository.confirmation;

import com.pdp.backend.model.confirmation.ConfirmationNumber;
import com.pdp.backend.repository.BaseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The ConfirmationNumberRepository class provides functionality to manage ConfirmationNumber objects in a repository.
 * It implements the BaseRepository interface for basic CRUD operations.
 *
 * Instances of ConfirmationNumberRepository store ConfirmationNumber objects in memory using a list.
 *
 * This class allows for adding, removing, finding by ID, and retrieving all ConfirmationNumber objects.
 * It provides methods to interact with ConfirmationNumber objects stored in memory.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
public class ConfirmationNumberRepository implements BaseRepository<ConfirmationNumber> {
    List<ConfirmationNumber> confirmationNumbers;

    public ConfirmationNumberRepository() {
        this.confirmationNumbers = new ArrayList<>();
    }

    /**
     * Adds a ConfirmationNumber object to the repository.
     *
     * @param object the ConfirmationNumber object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(ConfirmationNumber object) {
        confirmationNumbers.add(object);
        return true;
    }

    /**
     * Removes a ConfirmationNumber object from the repository by ID.
     *
     * @param id the ID of the ConfirmationNumber object to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean remove(UUID id) {
        return confirmationNumbers.removeIf((confirmationNumber -> Objects.equals(confirmationNumber.getId(),id)));
    }

    /**
     * Finds a ConfirmationNumber object in the repository by ID.
     *
     * @param id the ID of the ConfirmationNumber object to find
     * @return the ConfirmationNumber object if found, or null if not found
     */
    @Override
    public ConfirmationNumber findById(UUID id) {
        return confirmationNumbers.stream()
                .filter((confirmationNumber -> Objects.equals(confirmationNumber.getId(),id)))
                .findFirst().orElse(null);
    }
    /**
     * Retrieves all ConfirmationNumber objects stored in the repository.
     *
     * @return the list of all ConfirmationNumber objects stored in the repository
     */
    @Override
    public List<ConfirmationNumber> getAll() {
        return confirmationNumbers;
    }
}
