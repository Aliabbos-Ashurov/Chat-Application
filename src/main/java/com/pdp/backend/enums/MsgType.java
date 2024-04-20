package com.pdp.backend.enums;

/**
 * The MsgType enum represents the type of message in the system.
 * It defines three types: GROUP, CHANNEL, and USER.
 *
 * Each type corresponds to a different target audience for the message.
 * - GROUP: Indicates a message intended for a group of users.
 * - CHANNEL: Indicates a message intended for a channel.
 * - USER: Indicates a message intended for an individual user.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 20/April/2024
 */
public enum MsgType {
    GROUP,
    CHANNEL,
    USER
}
