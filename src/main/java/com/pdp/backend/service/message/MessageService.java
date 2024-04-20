package com.pdp.backend.service.message;

import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.model.message.Message;
import com.pdp.backend.repository.message.MessageRepository;
import com.pdp.backend.service.BaseService;
import java.util.List;
import java.util.UUID;

/**
 * The MessageService interface defines operations related to messages.
 * It extends the BaseService interface and specifies additional methods for message-specific functionality.
 *
 * Implementing classes should provide concrete implementations for these methods to handle message operations.
 *
 * @see com.pdp.backend.model.message.Message
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public interface MessageService extends BaseService<Message> {
    MessageRepository repository = new MessageRepository();

    /**
     * Retrieves messages from a specified chat.
     *
     * @see com.pdp.backend.service.message.MessageService#getChatMessage(UUID)
     * @param chatID The UUID of the chat from which messages are to be retrieved
     * @return A list of messages from the specified chat
     */
    List<Message> getChatMessage(UUID chatID);

    /**
     * Retrieves messages from a list of chats.
     *
     * @see com.pdp.backend.service.message.MessageService#getGroupMessage(List)
     * @param chats The list of chats from which messages are to be retrieved
     * @return A list of messages from the specified list of chats
     */
    List<Message> getGroupMessage(List<Chat> chats);

}
