package com.pdp.backend.repository.user;

import com.pdp.backend.model.user.User;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  12:51
 **/
public class UserRepository implements BaseRepository<User> {
    private final ListFileHandler<User> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.USERS);
    private final List<User> users;
    public UserRepository() {
        this.users = getAll();
    }

    @Override
    public boolean add(User object) {
        users.add(object);
        listFileHandler.writeList(users);
        return true;
    }

    @Override
    public boolean remove(UUID id) {
        boolean removeIf = users.removeIf((user -> Objects.equals(user.getId(), id)));
        if (removeIf) listFileHandler.writeList(users);
        return removeIf;
    }

    @Override
    public User findById(UUID id) {
        return users.stream()
                .filter((user -> user.getId().equals(id)))
                .findFirst().orElse(null);
    }
    @Override
    public List<User> getAll() {
        return listFileHandler.readList();
    }
}
