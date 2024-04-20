package com.pdp.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * The Permission enum represents the various permissions available in the system.
 * It provides a set of predefined permissions that can be granted to users or roles.
 *
 * Each permission has a corresponding access level, indicated by a string value.
 * This access level defines what actions or functionalities the permission allows.
 *
 * Permissions:
 * - READ: Grants read access.
 * - WRITE: Grants write access.
 * - DELETE: Grants delete access.
 * - CREATE_GROUP: Grants permission to create groups.
 * - CREATE_CHANNEL: Grants permission to create channels.
 * - VIEW_STATISTICS: Grants permission to view statistics.
 * - BLOCK_USERS: Grants permission to block users.
 * - PIN_MESSAGE: Grants permission to pin messages.
 * - START_VOICE_CHAT: Grants permission to start voice chats.
 * - START_VIDEO_CHAT: Grants permission to start video chats.
 * - MODIFY_SETTINGS: Grants permission to modify settings.
 * - CHANGE_NICKNAME: Grants permission to change nickname.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 10/April/2024
 * @see com.pdp.backend.enums.Role#ADMIN
 * @see com.pdp.backend.enums.Role#USER
 */
@AllArgsConstructor
@Getter
public enum Permission {
    READ("READ"),
    WRITE("WRITE"),
    DELETE("DELETE"),
    CREATE_GROUP("CREATE_GROUP"),
    CREATE_CHANNEL("CREATE_CHANNEL"),
    VIEW_STATISTICS("VIEW_STATISTICS"),
    BLOCK_USERS("BLOCK_USERS"),
    PIN_MESSAGE("PIN_MESSAGE"),
    START_VOICE_CHAT("START_VOICE_CHAT"),
    START_VIDEO_CHAT("START_VIDEO_CHAT"),
    MODIFY_SETTINGS("MODIFY_SETTINGS"),
    CHANGE_NICKNAME("CHANGE_NICKNAME");
    private final String access;
}
