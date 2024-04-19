package com.pdp.backend.service.chat;

import com.pdp.backend.model.chat.Chat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  11:22
 **/
public class ChatServiceImpl implements ChatService{
    private static ChatServiceImpl instance;

    private ChatServiceImpl() {}

    public static ChatServiceImpl getInstance() {
        if (instance == null) {
            instance =  new ChatServiceImpl();
        }
        return instance;
    }
    @Override
    public boolean add(Chat object) {
        repository.add(object);
        return true;
    }
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
