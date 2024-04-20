package com.pdp.backend.service.subscribers;

import com.pdp.backend.enums.Role;
import com.pdp.backend.model.subscribers.Subscribers;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * The SubscribersServiceImpl class provides the implementation for the SubscribersService interface.
 * It handles operations related to subscribers.
 *
 * @see com.pdp.backend.service.subscribers.SubscribersService
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public class SubscribersServiceImpl implements SubscribersService{
    private static SubscribersServiceImpl instance;

    private SubscribersServiceImpl() {}

    public static SubscribersServiceImpl getInstance() {
        if (instance == null) {
            instance = new SubscribersServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean add(Subscribers object) {
        List<Subscribers> subscribers = repository.getAll();
        boolean alreadySubscribed = subscribers.stream()
                .anyMatch(subscriber -> subscriber.getUserID().equals(object.getUserID())
                        && subscriber.getChannelID().equals(object.getChannelID()));
        if (alreadySubscribed) return false;
        return repository.add(object);
    }

    /**
     * Retrieves all subscribers joined to a specific channel by a user.
     *
     * @see com.pdp.backend.service.subscribers.SubscribersService#getUserJoinedChannel(UUID)
     * @param userID The UUID of the user whose joined channel subscribers are to be retrieved
     * @return The list of subscribers joined to the specified channel by the user
     */
    @Override
    public List<Subscribers> getUserJoinedChannel(UUID userID) {
        List<Subscribers> subscribers = repository.getAll();
        return subscribers.stream()
                .filter(subscriber -> subscriber.getUserID().equals(userID) && subscriber.getRole().equals(Role.USER))
                .collect(Collectors.toList());
    }

    @Override
    public List<Subscribers> search(String query) {
        return null;
    }

    @Override
    public boolean update(Subscribers object) {
        Subscribers subscribers = repository.findById(object.getId());
        if (Objects.isNull(subscribers)) return false;
        subscribers.setRole(object.getRole());
        return true;
    }

    @Override
    public boolean delete(UUID id) {
        return repository.remove(id);
    }

    @Override
    public List<Subscribers> getAll() {
        return repository.getAll();
    }

    @Override
    public Subscribers getById(UUID id) {
        return repository.findById(id);
    }
}
