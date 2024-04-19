package com.pdp.backend.service.group;

import com.pdp.backend.model.group.Group;
import com.pdp.backend.repository.group.GroupRepository;
import com.pdp.backend.service.BaseService;
import java.util.List;
import java.util.UUID;
/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  11:08
 **/
public interface GroupService extends BaseService<Group> {
    GroupRepository repository = new GroupRepository();
    List<Group> getOwnerGroup(UUID ownerID);
}
