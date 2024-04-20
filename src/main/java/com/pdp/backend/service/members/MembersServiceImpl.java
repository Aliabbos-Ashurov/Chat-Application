package com.pdp.backend.service.members;

import com.pdp.backend.enums.Role;
import com.pdp.backend.model.members.Members;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * The MembersServiceImpl class provides the implementation for the MembersService interface.
 * It handles operations related to group members.
 *
 * @see com.pdp.backend.service.members.MembersService
 * @see com.pdp.backend.repository.members.MembersRepository
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 **/
public class MembersServiceImpl implements MembersService{
    private static MembersServiceImpl instance;
    private MembersServiceImpl() {
    }
    public static MembersServiceImpl getInstance() {
        if (instance == null) {
            instance =  new MembersServiceImpl();
        }
        return instance;
    }
    
    @Override
    public boolean add(Members object) {
        List<Members> members = repository.getAll();
        boolean alreadySubscribed = members.stream()
                .anyMatch(member -> member.getUserID().equals(object.getUserID()) && member.getGroupID().equals(object.getGroupID()));
        if (alreadySubscribed) return false;
        return repository.add(object);
    }

    /**
     * @see com.pdp.backend.service.members.MembersService#getGroupMembers(UUID) 
     * @param groupID The UUID of the group whose members are to be retrieved
     * @return
     */
    @Override
    public List<Members> getGroupMembers(UUID groupID) {
        List<Members> members = repository.getAll();
        return members.stream()
                .filter((member -> member.getGroupID().equals(groupID)))
                .collect(Collectors.toList());
    }

    /**
     * @see com.pdp.backend.service.members.MembersService#getUserJoinedGroup(UUID) 
     * @param userID The UUID of the user whose joined group members are to be retrieved
     * @return
     */
    @Override
    public List<Members> getUserJoinedGroup(UUID userID) {
        List<Members> members = repository.getAll();
        return members.stream()
                .filter(member -> member.getUserID().equals(userID) && member.getRole().equals(Role.USER))
                .collect(Collectors.toList());
    }

    @Override
    public List<Members> search(String query) {
        return null;
    }

    @Override
    public boolean update(Members object) {
        Members members = repository.findById(object.getId());
        if (Objects.isNull(members)) return false;
        members.setUserID(object.getUserID());
        members.setGroupID(object.getGroupID());
        return true;
    }
    @Override
    public boolean delete(UUID id) {
        return repository.remove(id);
    }
    @Override
    public List<Members> getAll() {
        return repository.getAll();
    }
    @Override
    public Members getById(UUID id) {
        return repository.findById(id);
    }
}
