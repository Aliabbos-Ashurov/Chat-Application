package com.pdp.backend.model.message;

import com.pdp.backend.enums.MsgType;
import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
/**
 * The Message class represents a message in a chat application.
 * It extends the BaseModel class.
 * Each Message instance is associated with a sender ID, a chat ID, message type, content, and read status.
 *
 * This class provides a structured representation of messages exchanged in the chat application.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class Message extends BaseModel  {
    private UUID fromID;
    private UUID chatID;
    private MsgType msgType;
    private String content;
    private boolean isRead = false;

    /**
     * Constructs a new Message object with the specified parameters.
     *
     * @param fromID   the UUID of the sender of the message
     * @param chatID   the UUID of the chat associated with the message
     * @param content  the content of the message
     * @param msgType  the type of the message
     */
    public Message(UUID fromID,UUID chatID, String content,MsgType msgType) {
        this.fromID = fromID;
        this.chatID = chatID;
        this.content = content;
        this.msgType = msgType;
    }
}
