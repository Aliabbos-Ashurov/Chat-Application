package com.pdp.backend.model.chat;

import com.pdp.backend.model.BaseModel;
import lombok.Getter;
import lombok.ToString;
import java.util.UUID;
/**
 * The Chat class represents a conversation between two users in a chat application.
 * It extends the BaseModel class.
 * Each chat instance is associated with two users identified by their UUIDs.
 *
 * This class provides a simple representation of a chat conversation.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since [insert date]
 */
@Getter
@ToString(callSuper = true)
public class Chat extends BaseModel {
    private final UUID user1;
    private final UUID user2;
    /**
     * Constructs a new Chat object with the specified users.
     *
     * @param user1 the UUID of the first user
     * @param user2 the UUID of the second user
     */
    public Chat(UUID user1,UUID user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
}
