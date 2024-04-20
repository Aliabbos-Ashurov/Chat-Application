package com.pdp.backend.service.subscribers;

import com.pdp.backend.model.subscribers.Subscribers;
import com.pdp.backend.repository.subscribers.SubscribersRepository;
import com.pdp.backend.service.BaseService;
import java.util.List;
import java.util.UUID;
/**
 * The SubscribersService interface provides methods to manage subscribers.
 * It extends the BaseService interface, inheriting common CRUD operations.
 * Implementing classes should provide concrete implementations for these methods.
 *
 * @see com.pdp.backend.service.BaseService
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public interface SubscribersService extends BaseService<Subscribers> {
    SubscribersRepository repository = new SubscribersRepository();

    /**
     * Retrieves the list of channels joined by a user.
     *
     * @param userID The UUID of the user whose joined channels are to be retrieved
     * @return The list of channels joined by the specified user
     */
    List<Subscribers> getUserJoinedChannel(UUID userID);
}
