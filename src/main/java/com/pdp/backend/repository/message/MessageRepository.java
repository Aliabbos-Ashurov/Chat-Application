package com.pdp.backend.repository.message;

import com.pdp.backend.model.message.Message;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * The MessageRepository class provides functionality to manage Message objects in a repository.
 * It implements the BaseRepository interface for basic CRUD operations.
 * MessageRepository utilizes ListFileHandler for reading from and writing to files.
 *
 * Instances of MessageRepository are initialized with a ListFileHandler for managing Message objects stored in files.
 *
 * This class allows for adding, removing, finding by ID, and retrieving all Message objects.
 * It provides methods to interact with Message objects persisted in files.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
public class MessageRepository implements BaseRepository<Message> {
    private final ListFileHandler<Message> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.MESSAGES);
    private final List<Message> messages;

    public MessageRepository() {
        this.messages = getAll();
    }
    /**
     * Adds a Message object to the repository.
     * Writes the updated list of messages to the file.
     *
     * @param object the Message object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(Message object) {
        messages.add(object);
        listFileHandler.writeList(messages);
        return true;
    }
    /**
     * Removes a Message object from the repository by ID.
     * Writes the updated list of messages to the file.
     *
     * @param id the ID of the Message object to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = messages.removeIf((message -> Objects.equals(message.getId(), id)));
        if (removeIf) listFileHandler.writeList(messages);
        return removeIf;
    }
    /**
     * Finds a Message object in the repository by ID.
     *
     * @param id the ID of the Message object to find
     * @return the Message object if found, or null if not found
     */
    @Override
    public Message findById(UUID id) {
        return messages.stream()
                .filter((message -> Objects.equals(message.getId(),id)))
                .findFirst().orElse(null);
    }
    /**
     * Retrieves all Message objects stored in the repository.
     *
     * @return the list of all Message objects stored in the repository
     */
    @Override
    public List<Message> getAll() {
        return listFileHandler.readList();
    }
}
