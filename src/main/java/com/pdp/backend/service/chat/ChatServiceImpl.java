package com.pdp.backend.service.chat;

import com.pdp.backend.model.chat.Chat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * The ChatServiceImpl class provides the implementation for the ChatService interface.
 * It handles operations related to chat functionality.
 *
 * @see ChatService
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public class ChatServiceImpl implements ChatService{
    private static ChatServiceImpl instance;

    private ChatServiceImpl() {}

    public static ChatServiceImpl getInstance() {
        if (instance == null) {
            instance =  new ChatServiceImpl();
        }
        return instance;
    }

    /**
     * Adds a new chat object to the repository.
     *
     * @param object The chat object to be added.
     * @return True if the chat object is successfully added, false otherwise.
     */
    @Override
    public boolean add(Chat object) {
        repository.add(object);
        return true;
    }

    /**
     * Retrieves an existing chat between two users or creates a new one if it doesn't exist.
     *
     * @param user1ID The UUID of the first user.
     * @param user2ID The UUID of the second user.
     * @return The existing chat or a newly created chat.
     * @see ChatService#getOrCreate(UUID, UUID)
     */
    @Override
    public Chat getOrCreate(UUID user1ID, UUID user2ID) {
        List<Chat> chats = repository.getAll();
        Optional<Chat> existingChat = chats.stream()
                .filter(chat -> (chat.getUser1().equals(user1ID) && chat.getUser2().equals(user2ID))
                        || (chat.getUser1().equals(user2ID) && chat.getUser2().equals(user1ID)))
                .findFirst();
        if (existingChat.isPresent()) {return existingChat.get();}
        else {
            Chat newChat = new Chat(user1ID, user2ID);
            repository.add(newChat);
            return newChat;
        }
    }

    /**
     * Retrieves all chats associated with a specific user.
     *
     * @param userID The UUID of the user.
     * @return A list of chats associated with the user.
     */
    @Override
    public List<Chat> getUserChats(UUID userID) {
        List<Chat> chats = repository.getAll();
        return chats.stream()
                .filter((chat -> chat.getUser1().equals(userID) || chat.getUser2().equals(userID)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Chat> getGroupChat(UUID groupID) {
        List<Chat> chats = repository.getAll();
        return chats.stream()
                .filter(chat -> chat.getUser1().equals(groupID) || chat.getUser2().equals(groupID))
                .collect(Collectors.toList());
    }

    @Override
    public List<Chat> search(String query) {
        return null;
    }


    /**
     * This method is not implemented in the current version.
     *
     * @param object The object to be updated.
     * @return Always returns false.
     * @see ChatService#getOrCreate(UUID, UUID)
     */
    @Override
    public boolean update(Chat object) {
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        return repository.remove(id);
    }

    @Override
    public List<Chat> getAll() {
        return repository.getAll();
    }

    @Override
    public Chat getById(UUID id) {
        return repository.findById(id);
    }
}
