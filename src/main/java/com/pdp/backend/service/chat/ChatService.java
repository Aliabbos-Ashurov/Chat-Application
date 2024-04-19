package com.pdp.backend.service.chat;

import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.repository.chat.ChatRepository;
import com.pdp.backend.service.BaseService;

import java.util.List;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  11:22
 **/
public interface ChatService extends BaseService<Chat> {
    ChatRepository repository = new ChatRepository();
    List<Chat> getUserChats(UUID userID);
    Chat getOrCreate(UUID user1ID,UUID user2ID);
    List<Chat> getGroupChat(UUID groupID);
}
