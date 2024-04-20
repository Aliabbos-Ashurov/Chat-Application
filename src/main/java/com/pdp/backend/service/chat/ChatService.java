package com.pdp.backend.service.chat;

import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.repository.chat.ChatRepository;
import com.pdp.backend.service.BaseService;
import java.util.List;
import java.util.UUID;

/**
 * The ChatService interface defines operations related to chat functionality.
 * It extends the BaseService interface and specifies additional methods for chat-specific functionality.
 *
 * Implementing classes should provide concrete implementations for these methods to handle chat operations.
 *
 * @see BaseService
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public interface ChatService extends BaseService<Chat> {
    ChatRepository repository = new ChatRepository();

    /**
     * Retrieves chats associated with the specified user.
     *
     * @param userID the ID of the user
     * @return a list of chats associated with the specified user
     */
    List<Chat> getUserChats(UUID userID);

    /**
     * Retrieves or creates a chat between two users.
     *
     * @param user1ID the ID of the first user
     * @param user2ID the ID of the second user
     * @return the chat between the two users
     */
    Chat getOrCreate(UUID user1ID,UUID user2ID);

    /**
     * Retrieves group chats associated with the specified group.
     *
     * @param groupID the ID of the group
     * @return a list of group chats associated with the specified group
     */
    List<Chat> getGroupChat(UUID groupID);
}
