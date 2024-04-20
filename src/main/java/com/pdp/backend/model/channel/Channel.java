package com.pdp.backend.model.channel;

import com.pdp.backend.model.BaseModel;
import com.pdp.backend.model.support.Displayable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
/**
 * The Channel class represents a channel in a chat application.
 * It extends the BaseModel class and implements the Displayable interface.
 * Channels are used to group related messages and discussions.
 * Each channel has an owner, a name, a description, privacy status,
 * subscriber count, and a unique link for access.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Channel extends BaseModel implements Displayable {
    private UUID ownerID;
    private String name;
    private String description;
    private boolean isPrivate;
    private Integer subscriberCount;
    private String link;
    /**
     * Constructs a new Channel object with the specified parameters.
     *
     * @param ownerID      the UUID of the owner of the channel
     * @param name         the name of the channel
     * @param description  the description of the channel
     * @param isPrivate    indicates whether the channel is private or public
     */
    public Channel(UUID ownerID, String name, String description,boolean isPrivate) {
        this.ownerID = ownerID;
        this.name = name;
        this.description = description;
        this.subscriberCount = 0;
        this.isPrivate = isPrivate;
        this.link = createLink();
    }
    /**
     * Creates a unique link for the channel based on its name.
     *
     * @return the unique link for accessing the channel
     */
    private String createLink() {
        String name = String.valueOf(this.name);
        return "@" + name + "/chatapp";
    }
}
