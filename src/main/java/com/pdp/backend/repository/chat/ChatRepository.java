package com.pdp.backend.repository.chat;

import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * The ChatRepository class provides functionality to manage Chat objects in a repository.
 * It implements the BaseRepository interface for basic CRUD operations.
 * ChatRepository utilizes ListFileHandler for reading from and writing to files.
 *
 * Instances of ChatRepository are initialized with a ListFileHandler for managing Chat objects stored in files.
 *
 * This class allows for adding, removing, finding by ID, and retrieving all Chat objects.
 * It provides methods to interact with Chat objects persisted in files.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
public class ChatRepository implements BaseRepository<Chat> {
    private final ListFileHandler<Chat> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.CHAT);
    private final List<Chat> chats;
    public ChatRepository() {
        chats = getAll();
    }
    /**
     * Adds a Chat object to the repository.
     * Writes the updated list of chats to the file.
     *
     * @param object the Chat object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(Chat object) {
        chats.add(object);
        listFileHandler.writeList(chats);
        return true;
    }
    /**
     * Removes a Chat object from the repository by ID.
     * Writes the updated list of chats to the file.
     *
     * @param id the ID of the Chat object to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = chats.removeIf((chat -> Objects.equals(chat.getId(), id)));
        if (removeIf) listFileHandler.writeList(chats);
        return removeIf;
    }
    /**
     * Finds a Chat object in the repository by ID.
     *
     * @param id the ID of the Chat object to find
     * @return the Chat object if found, or null if not found
     */
    @Override
    public Chat findById(UUID id) {
        return chats.stream()
                .filter((chat) -> Objects.equals(chat.getId(),id))
                .findFirst().orElse(null);
    }
    /**
     * Retrieves all Chat objects stored in the repository.
     *
     * @return the list of all Chat objects stored in the repository
     */
    @Override
    public List<Chat> getAll() {
        return listFileHandler.readList();
    }
}
