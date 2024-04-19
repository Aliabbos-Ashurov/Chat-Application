package com.pdp.backend.service.subscribers;

import com.pdp.backend.model.subscribers.Subscribers;
import com.pdp.backend.repository.subscribers.SubscribersRepository;
import com.pdp.backend.service.BaseService;
import java.util.List;
import java.util.UUID;
/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  12:24
 **/
public interface SubscribersService extends BaseService<Subscribers> {
    SubscribersRepository repository = new SubscribersRepository();
    List<Subscribers> getUserJoinedChannel(UUID userID);
}
