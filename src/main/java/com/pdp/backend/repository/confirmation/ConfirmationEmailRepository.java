package com.pdp.backend.repository.confirmation;

import com.pdp.backend.model.confirmation.ConfirmationEmail;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * The ConfirmationEmailRepository class provides functionality to manage ConfirmationEmail objects in a repository.
 * It implements the BaseRepository interface for basic CRUD operations.
 * ConfirmationEmailRepository utilizes ListFileHandler for reading from and writing to files.
 *
 * Instances of ConfirmationEmailRepository are initialized with a ListFileHandler for managing ConfirmationEmail objects stored in files.
 *
 * This class allows for adding, removing, finding by ID, and retrieving all ConfirmationEmail objects.
 * It provides methods to interact with ConfirmationEmail objects persisted in files.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
public class ConfirmationEmailRepository implements BaseRepository<ConfirmationEmail> {
    private final ListFileHandler<ConfirmationEmail> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.EMAILS);
    private final List<ConfirmationEmail> confirmationEmails;

    public ConfirmationEmailRepository() {
        this.confirmationEmails = getAll();
    }

    /**
     * Adds a ConfirmationEmail object to the repository.
     * Writes the updated list of confirmation emails to the file.
     *
     * @param object the ConfirmationEmail object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(ConfirmationEmail object) {
        confirmationEmails.add(object);
        listFileHandler.writeList(confirmationEmails);
        return true;
    }

    /**
     * Removes a ConfirmationEmail object from the repository by ID.
     * Writes the updated list of confirmation emails to the file.
     *
     * @param id the ID of the ConfirmationEmail object to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = confirmationEmails.removeIf(confirmationEmail -> Objects.equals(confirmationEmail.getId(), id));
        if (removeIf) listFileHandler.writeList(confirmationEmails);
        return removeIf;
    }
    /**
     * Finds a ConfirmationEmail object in the repository by ID.
     *
     * @param id the ID of the ConfirmationEmail object to find
     * @return the ConfirmationEmail object if found, or null if not found
     */
    @Override
    public ConfirmationEmail findById(UUID id) {
        return confirmationEmails.stream()
                .filter(confirmationEmail -> Objects.equals(confirmationEmail.getId(),id))
                .findFirst().orElse(null);
    }

    /**
     * Retrieves all ConfirmationEmail objects stored in the repository.
     *
     * @return the list of all ConfirmationEmail objects stored in the repository
     */
    @Override
    public List<ConfirmationEmail> getAll() {
        return listFileHandler.readList();
    }
}
