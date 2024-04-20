package com.pdp.backend.service.group;

import com.pdp.backend.model.group.Group;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * The GroupServiceImpl class provides the implementation for the GroupService interface.
 * It handles operations related to groups.
 *
 * @see com.pdp.backend.service.group.GroupService
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 **/
public class GroupServiceImpl implements GroupService{
    private static GroupServiceImpl instance;

    private GroupServiceImpl() {
    }

    /**
     * Returns the singleton instance of GroupServiceImpl.
     * @return The singleton instance of GroupServiceImpl
     */
    public static GroupServiceImpl getInstance() {
        if (instance == null) {
            instance = new GroupServiceImpl();
        }
        return instance;
    }
    @Override
    public boolean add(Group object) {
        List<Group> groups = repository.getAll();
        boolean noneExist = groups.stream()
                .noneMatch(group -> group.getName().equals(object.getName()));
        if (noneExist) repository.add(object);
        return noneExist;
    }
    @Override
    public List<Group> search(String query) {
        List<Group> groups = repository.getAll();
        return groups.stream()
                .filter((group -> groupConfig(query,group.getName()) && !group.isPrivate()))
                .collect(Collectors.toList());
    }
    private boolean groupConfig(String query,String groupName) {
        boolean check1 = query.equalsIgnoreCase(groupName);
        boolean check2 = groupName.toUpperCase().startsWith(query.toUpperCase());
        return check1 || check2;
    }
    @Override
    public List<Group> getOwnerGroup(UUID ownerID) {
        List<Group> groups = repository.getAll();
        return groups.stream()
                .filter(group -> group.getOwnerID().equals(ownerID))
                .collect(Collectors.toList());
    }
    @Override
    public boolean update(Group object) {
        Group group = repository.findById(object.getId());
        if (Objects.nonNull(group)) {
            group.setName(object.getName());
            group.setDescription(object.getDescription());
            group.setPrivate(object.isPrivate());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        return repository.remove(id);
    }

    @Override
    public List<Group> getAll() {
        return repository.getAll();
    }

    @Override
    public Group getById(UUID id) {
        return repository.findById(id);
    }
}
