package com.pdp.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Set;
import static com.pdp.backend.enums.Permission.*;

/**
 * The Role enum represents roles that users can have in the system.
 * Each role defines a set of permissions that users with that role possess.
 *
 * Roles are typically used to control access to different features and functionalities within the system.
 * This enum provides predefined roles such as USER and ADMIN, each with its associated set of permissions.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 10/April/2024
 */
@AllArgsConstructor
@Getter
public enum Role {
    USER(Set.of(READ,WRITE,DELETE,CHANGE_NICKNAME,CREATE_GROUP,CREATE_CHANNEL,MODIFY_SETTINGS)),
    ADMIN(Set.of(READ,WRITE,DELETE,CREATE_GROUP,CREATE_CHANNEL,VIEW_STATISTICS,BLOCK_USERS,PIN_MESSAGE,START_VIDEO_CHAT,START_VOICE_CHAT,MODIFY_SETTINGS,CHANGE_NICKNAME));
    private final Set<Permission> permissions;
}
