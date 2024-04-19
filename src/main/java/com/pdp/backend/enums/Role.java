package com.pdp.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Set;
import static com.pdp.backend.enums.Permission.*;
/**
 * @author Aliabbos Ashurov
 * Date: 10/April/2024  22:14
 **/
@AllArgsConstructor
@Getter
public enum Role {
    USER(Set.of(READ,WRITE,DELETE,CHANGE_NICKNAME,CREATE_GROUP,CREATE_CHANNEL,MODIFY_SETTINGS)),
    ADMIN(Set.of(READ,WRITE,DELETE,CREATE_GROUP,CREATE_CHANNEL,VIEW_STATISTICS,BLOCK_USERS,PIN_MESSAGE,START_VIDEO_CHAT,START_VOICE_CHAT,MODIFY_SETTINGS,CHANGE_NICKNAME));
    private final Set<Permission> permissions;
}
