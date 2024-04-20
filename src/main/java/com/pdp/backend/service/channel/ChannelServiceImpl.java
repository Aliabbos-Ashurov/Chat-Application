package com.pdp.backend.service.channel;

import com.pdp.backend.model.channel.Channel;
import com.pdp.backend.repository.channel.ChannelRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * The ChannelServiceImpl class provides the implementation for the ChannelService interface.
 * It handles operations related to channels.
 * @see com.pdp.backend.service.channel.ChannelService
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public class ChannelServiceImpl implements ChannelService{
    private static ChannelServiceImpl instance;
    private ChannelServiceImpl() {
    }
    public static ChannelServiceImpl getInstance() {
        if (instance == null) {
            instance =  new ChannelServiceImpl();
        }
        return instance;
    }
    /**
     * Adds a new channel to the repository if a channel with the same name doesn't already exist.
     * Returns true if the channel was added successfully, false otherwise.
     * @see com.pdp.backend.repository.channel.ChannelRepository#add(Channel)
     * @param object the channel to add
     * @return true if the channel was added successfully, false otherwise
     */
    @Override
    public boolean add(Channel object) {
        List<Channel> channels = repository.getAll();
        boolean match = channels.stream()
                .noneMatch(channel -> channel.getName().equalsIgnoreCase(object.getName()));
        if (match) repository.add(object);
        return match;
    }

    /**
     * Retrieves the channels owned by the specified user.
     * @param userID the ID of the user
     * @return a list of channels owned by the specified user
     * @see ChannelRepository#getAll()
     */
    @Override
    public List<Channel> getUserChannels(UUID userID) {
        var channels = repository.getAll();
        return channels.stream()
                .filter((channel -> channel.getOwnerID().equals(userID)))
                .collect(Collectors.toList());
    }

    /**
     * Updates the details of an existing channel.
     *@see ChannelRepository#findById(UUID)
     * @param object the updated channel object
     * @return true if the channel was successfully updated, false if the channel was not found
     */
    @Override
    public boolean update(Channel object) {
        Channel channel = repository.findById(object.getId());
        if (Objects.nonNull(channel)) {
            channel.setName(object.getName());
            channel.setDescription(object.getDescription());
            channel.setPrivate(object.isPrivate());
            return true;
        }
        return false;
    }

    /**
     * Searches for channels based on the provided query.
     * Searches are case-insensitive and include channels whose names start with the query.
     *@see ChannelRepository#getAll()
     * @param query the search query
     * @return a list of channels matching the search criteria
     */
    @Override
    public List<Channel> search(String query) {
        List<Channel> channels = repository.getAll();
        return channels.stream()
                .filter(channel -> channelConfig(query,channel.getName()) && !channel.isPrivate())
                .collect(Collectors.toList());
    }

    /**
     * Checks if the given query matches the channel name, case-insensitively.
     * Also checks if the channel name starts with the query, ignoring case.
     * @see com.pdp.backend.service.channel.ChannelServiceImpl#search(String)
     * @param query       the search query
     * @param channelName the name of the channel
     * @return true if the channel name matches the query, false otherwise
     */
    private boolean channelConfig(String query,String channelName) {
        boolean check1 = query.equalsIgnoreCase(channelName);
        boolean check2 = channelName.toUpperCase().startsWith(query.toUpperCase());
        return check1 || check2;
    }
    /**
     * Deletes the channel with the specified ID from the repository.
     *
     * @param id the ID of the channel to delete
     * @return true if the channel was successfully deleted, false if the channel was not found
     */
    @Override
    public boolean delete(UUID id) {
        return repository.remove(id);
    }

    @Override
    public List<Channel> getAll() {
        return repository.getAll();
    }

    @Override
    public Channel getById(UUID id) {
        return repository.findById(id);
    }
}
