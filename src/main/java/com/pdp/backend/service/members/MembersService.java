package com.pdp.backend.service.members;

import com.pdp.backend.model.members.Members;
import com.pdp.backend.repository.members.MembersRepository;
import com.pdp.backend.service.BaseService;
import java.util.List;
import java.util.UUID;

/**
 * The MembersService interface defines operations related to group members.
 * It extends the BaseService interface and specifies additional methods for member-specific functionality.
 *
 * Implementing classes should provide concrete implementations for these methods to handle member operations.
 *
 * @see com.pdp.backend.model.members.Members
 * @see com.pdp.backend.repository.members.MembersRepository
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 **/
public interface MembersService extends BaseService<Members> {
    MembersRepository repository = new MembersRepository();

    /**
     * Retrieves a list of members who have joined the group associated with the given userID.
     *
     * @param userID The UUID of the user whose joined group members are to be retrieved
     * @return A list of members who have joined the group associated with the given userID
     */
    List<Members> getUserJoinedGroup(UUID userID);

    /**
     * Retrieves a list of members belonging to the group associated with the given groupID.
     *
     * @param groupID The UUID of the group whose members are to be retrieved
     * @return A list of members belonging to the group associated with the given groupID
     */
    List<Members> getGroupMembers(UUID groupID);
}
