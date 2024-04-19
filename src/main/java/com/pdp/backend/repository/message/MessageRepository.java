package com.pdp.backend.repository.message;

import com.pdp.backend.model.message.Message;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  13:24
 **/
public class MessageRepository implements BaseRepository<Message> {
    private final ListFileHandler<Message> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.MESSAGES);
    private final List<Message> messages;

    public MessageRepository() {
        this.messages = getAll();
    }
    @Override
    public boolean add(Message object) {
        messages.add(object);
        listFileHandler.writeList(messages);
        return true;
    }
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = messages.removeIf((message -> Objects.equals(message.getId(), id)));
        if (removeIf) listFileHandler.writeList(messages);
        return removeIf;
    }
    @Override
    public Message findById(UUID id) {
        return messages.stream()
                .filter((message -> Objects.equals(message.getId(),id)))
                .findFirst().orElse(null);
    }
    @Override
    public List<Message> getAll() {
        return listFileHandler.readList();
    }
}
