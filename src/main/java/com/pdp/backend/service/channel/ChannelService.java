package com.pdp.backend.service.channel;

import com.pdp.backend.model.channel.Channel;
import com.pdp.backend.repository.channel.ChannelRepository;
import com.pdp.backend.service.BaseService;
import java.util.List;
import java.util.UUID;

/**
 * The ChannelService interface defines operations related to channels.
 * It extends the BaseService interface and specifies additional methods for channel-specific functionality.
 *
 * Implementing classes should provide concrete implementations for these methods to handle channel operations.
 *
 * @see com.pdp.backend.model.channel.Channel
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public interface ChannelService extends BaseService<Channel> {
    ChannelRepository repository = new ChannelRepository();

    /**
     * Retrieves the channels associated with a specific user.
     *
     * @param userID the ID of the user whose channels are to be retrieved
     * @return a list of channels associated with the specified user
     */
    List<Channel> getUserChannels(UUID userID);

}
