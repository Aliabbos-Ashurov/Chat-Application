package com.pdp.backend.service.message;

import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.model.message.Message;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * The MessageServiceImpl class provides the implementation for the MessageService interface.
 * It handles operations related to messages.
 *
 * @see com.pdp.backend.service.message.MessageService
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 **/
public class MessageServiceImpl implements MessageService{
    private static MessageServiceImpl instance;

    private MessageServiceImpl() {}

    public static MessageServiceImpl getInstance() {
        if (instance == null) {
            instance = new MessageServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean add(Message object) {
        repository.add(object);
        return true;
    }

    @Override
    public List<Message> search(String query) {
        return null;
    }

    /**
     * Retrieves messages from a list of chats.
     *
     * @see com.pdp.backend.service.message.MessageService#getGroupMessage(List)
     * @param chats The list of chats from which messages are to be retrieved
     * @return A list of messages from the specified list of chats
     */
    @Override
    public List<Message> getGroupMessage(List<Chat> chats) {
        List<Message> messages = repository.getAll();
        return messages.stream()
                .filter(message -> chats.stream().anyMatch(chat -> message.getChatID().equals(chat.getId())))
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves messages from a specified chat.
     *
     * @see com.pdp.backend.service.message.MessageService#getChatMessage(UUID)
     * @param chatID The UUID of the chat from which messages are to be retrieved
     * @return A list of messages from the specified chat
     */
    @Override
    public List<Message> getChatMessage(UUID chatID) {
        List<Message> messages = repository.getAll();
        return messages.stream()
                .filter(message -> message.getChatID().equals(chatID))
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(Message object) {
        Message message = repository.findById(object.getId());
        if (Objects.isNull(message)) return false;
        message.setRead(object.isRead());
        message.setContent(object.getContent());
        message.setMsgType(object.getMsgType());
        return true;
    }

    @Override
    public boolean delete(UUID id) {
        return repository.remove(id);
    }

    @Override
    public List<Message> getAll() {
        return repository.getAll();
    }

    @Override
    public Message getById(UUID id) {
        return repository.findById(id);
    }
}
