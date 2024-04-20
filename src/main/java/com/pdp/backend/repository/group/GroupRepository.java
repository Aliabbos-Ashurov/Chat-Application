package com.pdp.backend.repository.group;

import com.pdp.backend.model.group.Group;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  13:20
 **/
public class GroupRepository implements BaseRepository<Group> {
    private final ListFileHandler<Group> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.GROUPS);
    private final List<Group> groups;
    public GroupRepository() {
        groups = getAll();
    }

    @Override
    public boolean add(Group object) {
        groups.add(object);
        listFileHandler.writeList(groups);
        return true;
    }

    @Override
    public boolean remove(UUID id) {
        boolean removeIf = groups.removeIf((group -> Objects.equals(group.getId(), id)));
        if (removeIf) listFileHandler.writeList(groups);
        return removeIf;
    }

    @Override
    public Group findById(UUID id) {
        return groups.stream()
                .filter((group -> Objects.equals(group.getId(),id)))
                .findFirst().orElse(null);
    }

    @Override
    public List<Group> getAll() {
        return listFileHandler.readList();
    }
}
