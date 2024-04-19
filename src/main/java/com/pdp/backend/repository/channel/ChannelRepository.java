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
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  13:01
 **/
public class ChannelRepository implements BaseRepository<Channel> {
    private final ListFileHandler<Channel> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.CHANNELS);
    private final List<Channel> channels;

    public ChannelRepository() {
        channels = getAll();
    }
    @Override
    public boolean add(Channel object) {
        channels.add(object);
        listFileHandler.writeList(channels);
        return true;
    }
    @Override
    public boolean remove(UUID id) {
        boolean b = channels.removeIf((channel -> Objects.equals(channel.getId(), id)));
        if (b) listFileHandler.writeList(channels);
        return b;
    }
    @Override
    public Channel findById(UUID id) {
        return channels.stream()
                .filter((channel -> Objects.equals(channel.getId(),id)))
                .findFirst().orElse(null);
    }
    @Override
    public List<Channel> getAll() {
        return listFileHandler.readList();
    }
}
