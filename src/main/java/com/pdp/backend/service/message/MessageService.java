package com.pdp.backend.service.message;

import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.model.message.Message;
import com.pdp.backend.repository.message.MessageRepository;
import com.pdp.backend.service.BaseService;

import java.util.List;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  12:19
 **/
public interface MessageService extends BaseService<Message> {
    MessageRepository repository = new MessageRepository();
    List<Message> getUserMessage(UUID userID);
    List<Message> getChatMessage(UUID chatID);
    List<Message> getGroupMessage(List<Chat> chats);

}
