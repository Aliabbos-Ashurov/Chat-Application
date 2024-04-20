package com.pdp.backend.service.members;

import com.pdp.backend.model.members.Members;
import com.pdp.backend.repository.members.MembersRepository;
import com.pdp.backend.service.BaseService;
import java.util.List;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  11:32
 **/
public interface MembersService extends BaseService<Members> {
    MembersRepository repository = new MembersRepository();
    List<Members> getUserJoinedGroup(UUID userID);
    List<Members> getGroupMembers(UUID groupID);
}
