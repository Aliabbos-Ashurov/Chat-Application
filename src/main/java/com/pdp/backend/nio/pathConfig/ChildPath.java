package com.pdp.backend.nio.pathConfig;

/**
 * The ChildPath class provides constants for file paths used in the application.
 * It defines the child paths for various types of data stored in files.
 *
 * This class is final and contains only static members.
 * ChildPath is intended to be used for accessing predefined file paths throughout the application.
 *
 * The constants specify the child paths for different types of data stored in files, such as channels, chats, emails, etc.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 19/April/2024
 */
public final class ChildPath {
    public static final String CHANNELS = "channels.txt";
    public static final String CHAT = "chats.txt";
    public static final String EMAILS = "emails.txt";
    public static final String GROUPS = "groups.txt";
    public static final String MEMBERS = "members.txt";
    public static final String MESSAGES = "messages.txt";
    public static final String POSTS = "posts.txt";
    public static final String SUBSCRIBERS = "subscribers.txt";
    public static final String USERS = "users.txt";
}
