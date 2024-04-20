package com.pdp.backend.model.subscribers;

import com.pdp.backend.enums.Role;
import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
/**
 * The Subscribers class represents the subscribers of a channel in a chat application.
 * It extends the BaseModel class.
 * Each Subscribers instance represents a user's subscription to a specific channel, identified by the channel ID.
 * It includes the user's ID, the channel's ID, and the role of the user within the channel.
 *
 * This class provides a structured representation of channel subscribers.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class Subscribers extends BaseModel  {
    private UUID userID;
    private UUID channelID;
    private Role role;
}
