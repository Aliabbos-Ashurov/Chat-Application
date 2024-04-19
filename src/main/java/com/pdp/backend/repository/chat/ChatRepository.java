package com.pdp.backend.repository.chat;

import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  13:18
 **/
public class ChatRepository implements BaseRepository<Chat> {
    private final ListFileHandler<Chat> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.CHAT);
    private final List<Chat> chats;
    public ChatRepository() {
        chats = getAll();
    }

    @Override
    public boolean add(Chat object) {
        chats.add(object);
        listFileHandler.writeList(chats);
        return true;
    }
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = chats.removeIf((chat -> Objects.equals(chat.getId(), id)));
        if (removeIf) listFileHandler.writeList(chats);
        return removeIf;
    }
    @Override
    public Chat findById(UUID id) {
        return chats.stream()
                .filter((chat) -> Objects.equals(chat.getId(),id))
                .findFirst().orElse(null);
    }
    @Override
    public List<Chat> getAll() {
        return listFileHandler.readList();
    }
}
