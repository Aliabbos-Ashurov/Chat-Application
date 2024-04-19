package com.pdp.backend.repository.members;

import com.pdp.backend.model.members.Members;
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
 * Date: 12/April/2024  13:21
 **/
public class MembersRepository implements BaseRepository<Members> {
    private final ListFileHandler<Members> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.MEMBERS);
    private final List<Members> members;
    public MembersRepository() {
        this.members = getAll();

    }
    @Override
    public boolean add(Members object) {
        members.add(object);
        listFileHandler.writeList(members);
        return true;
    }
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = members.removeIf(member -> Objects.equals(member.getId(), id));
        if (removeIf) listFileHandler.writeList(members);
        return removeIf;
    }
    @Override
    public Members findById(UUID id) {
        return members.stream()
                .filter((member) -> Objects.equals(member.getId(),id))
                .findFirst().orElse(null);
    }
    @Override
    public List<Members> getAll() {
        return listFileHandler.readList();
    }
}
