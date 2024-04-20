package com.pdp.backend.model.members;

import com.pdp.backend.enums.Role;
import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
/**
 * The Members class represents the membership of a user in a group in a chat application.
 * It extends the BaseModel class.
 * Each Members instance represents a user's membership in a specific group, identified by the group ID.
 * It includes the user's ID, the group's ID, and the role of the user within the group.
 *
 * This class provides a structured representation of group memberships.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class Members extends BaseModel {
    private UUID userID;
    private UUID groupID;
    private Role role;
}
