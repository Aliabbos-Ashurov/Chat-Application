package com.pdp.backend.repository.channel;

import com.pdp.backend.model.channel.Channel;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * The ChannelRepository class provides functionality to manage Channel objects in a repository.
 * It implements the BaseRepository interface for basic CRUD operations.
 * ChannelRepository utilizes ListFileHandler for reading from and writing to files.
 *
 * Instances of ChannelRepository are initialized with a ListFileHandler for managing Channel objects stored in files.
 *
 * This class allows for adding, removing, finding by ID, and retrieving all Channel objects.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
public class ChannelRepository implements BaseRepository<Channel> {
    private final ListFileHandler<Channel> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.CHANNELS);
    private final List<Channel> channels;

    public ChannelRepository() {
        channels = getAll();
    }
    /**
     * Adds a Channel object to the repository.
     * Writes the updated list of channels to the file.
     *
     * @param object the Channel object to add
     * @return true if the addition was successful, false otherwise
     */
    @Override
    public boolean add(Channel object) {
        channels.add(object);
        listFileHandler.writeList(channels);
        return true;
    }
    /**
     * Removes a Channel object from the repository by ID.
     * Writes the updated list of channels to the file.
     *
     * @param id the ID of the Channel object to remove
     * @return true if the removal was successful, false otherwise
     */
    @Override
    public boolean remove(UUID id) {
        boolean b = channels.removeIf((channel -> Objects.equals(channel.getId(), id)));
        if (b) listFileHandler.writeList(channels);
        return b;
    }
    /**
     * Finds a Channel object in the repository by ID.
     *
     * @param id the ID of the Channel object to find
     * @return the Channel object if found, or null if not found
     */
    @Override
    public Channel findById(UUID id) {
        return channels.stream()
                .filter((channel -> Objects.equals(channel.getId(),id)))
                .findFirst().orElse(null);
    }
    /**
     * Retrieves all Channel objects stored in the repository.
     *
     * @return the list of all Channel objects stored in the repository
     */
    @Override
    public List<Channel> getAll() {
        return listFileHandler.readList();
    }
}
