package com.pdp.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author Aliabbos Ashurov
 * Date: 10/April/2024  21:56
 **/
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
