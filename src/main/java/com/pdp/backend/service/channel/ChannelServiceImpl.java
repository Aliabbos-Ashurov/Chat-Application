package com.pdp.backend.service.channel;

import com.pdp.backend.model.channel.Channel;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  11:16
 **/
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
    @Override
    public boolean add(Channel object) {
        List<Channel> channels = repository.getAll();
        boolean match = channels.stream()
                .noneMatch(channel -> channel.getName().equalsIgnoreCase(object.getName()));
        if (match) repository.add(object);
        return match;
    }
    @Override
    public List<Channel> getUserChannels(UUID userID) {
        var channels = repository.getAll();
        return channels.stream()
                .filter((channel -> channel.getOwnerID().equals(userID)))
                .collect(Collectors.toList());
    }
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
    @Override
    public List<Channel> search(String query) {
        List<Channel> channels = repository.getAll();
        return channels.stream()
                .filter(channel -> channelConfig(query,channel.getName()) && !channel.isPrivate())
                .collect(Collectors.toList());
    }
    private boolean channelConfig(String query,String channelName) {
        boolean check1 = query.equalsIgnoreCase(channelName);
        boolean check2 = channelName.toUpperCase().startsWith(query.toUpperCase());
        return check1 || check2;
    }
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
