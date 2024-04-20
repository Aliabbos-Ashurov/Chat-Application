package com.pdp.backend.service.group;

import com.pdp.backend.model.group.Group;
import com.pdp.backend.repository.group.GroupRepository;
import com.pdp.backend.service.BaseService;
import java.util.List;
import java.util.UUID;
/**
 * The GroupService interface defines operations related to groups.
 * It extends the BaseService interface and specifies additional methods for group-specific functionality.
 *
 * Implementing classes should provide concrete implementations for these methods to handle group operations.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public interface GroupService extends BaseService<Group> {
    GroupRepository repository = new GroupRepository();

    /**
     * Retrieves all groups owned by a user.
     *
     * @param ownerID The ID of the owner user.
     * @return A list of groups owned by the user.
     */
    List<Group> getOwnerGroup(UUID ownerID);
}
