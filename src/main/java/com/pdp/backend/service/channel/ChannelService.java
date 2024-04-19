package com.pdp.backend.service.channel;

import com.pdp.backend.model.channel.Channel;
import com.pdp.backend.repository.channel.ChannelRepository;
import com.pdp.backend.service.BaseService;

import java.util.List;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  11:15
 **/
public interface ChannelService extends BaseService<Channel> {
    ChannelRepository repository = new ChannelRepository();
    List<Channel> getUserChannels(UUID userID);
}
