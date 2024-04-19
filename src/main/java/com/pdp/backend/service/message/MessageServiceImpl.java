package com.pdp.backend.service.message;

import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.model.message.Message;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  12:20
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


    @Override
    public List<Message> getGroupMessage(List<Chat> chats) {
        List<Message> messages = repository.getAll();
        return messages.stream()
                .filter(message -> chats.stream().anyMatch(chat -> message.getChatID().equals(chat.getId())))
                .collect(Collectors.toList());
    }
    @Override
    public List<Message> getChatMessage(UUID chatID) {
        List<Message> messages = repository.getAll();
        return messages.stream()
                .filter(message -> message.getChatID().equals(chatID))
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getUserMessage(UUID userID) {
        List<Message> messages = repository.getAll();
        return messages.stream()
                .filter(message -> message.getFromID().equals(userID))
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
