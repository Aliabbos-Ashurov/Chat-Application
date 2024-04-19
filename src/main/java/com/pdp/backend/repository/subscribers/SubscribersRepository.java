package com.pdp.backend.repository.subscribers;

import com.pdp.backend.model.subscribers.Subscribers;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  13:26
 **/
public class SubscribersRepository implements BaseRepository<Subscribers> {
    private final ListFileHandler<Subscribers> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.SUBSCRIBERS);
    private final List<Subscribers> subscribers;
    public SubscribersRepository() {
        this.subscribers = getAll();
    }
    @Override
    public boolean add(Subscribers object) {
        subscribers.add(object);
        listFileHandler.writeList(subscribers);
        return true;
    }
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = subscribers.removeIf((subscriber) -> Objects.equals(subscriber.getId(), id));
        if (removeIf) listFileHandler.writeList(subscribers);
        return removeIf;
    }
    @Override
    public Subscribers findById(UUID id) {
        return subscribers.stream()
                .filter((subscriber) -> Objects.equals(subscriber.getId(),id))
                .findFirst().orElse(null);
    }
    @Override
    public List<Subscribers> getAll() {
        return listFileHandler.readList();
    }
}
