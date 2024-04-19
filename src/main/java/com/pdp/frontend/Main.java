package com.pdp.frontend;

import com.pdp.backend.model.channel.Channel;
import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.model.confirmation.ConfirmationEmail;
import com.pdp.backend.model.group.Group;
import com.pdp.backend.model.members.Members;
import com.pdp.backend.model.message.Message;
import com.pdp.backend.model.post.Post;
import com.pdp.backend.model.subscribers.Subscribers;
import com.pdp.backend.model.user.User;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.frontend.controller.UserController;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Aliabbos Ashurov
 * Date: 11/April/2024  20:30
 **/
public class Main {

    public static void main(String[] args) {
        UserController.startApp();
    }
    private static void def() {
        List<Channel> channels = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        List<Subscribers> subscribers = new ArrayList<>();
        List<Chat>chats = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<ConfirmationEmail> confirmationEmails = new ArrayList<>();
        List<Members> members = new ArrayList<>();
        List<Group> groups = new ArrayList<>();


        ListFileHandler<Channel> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.CHANNELS);
        listFileHandler.writeList(channels);
        ListFileHandler<Post> listFileHandler1 = new ListFileHandler<>(DirectoryPath.DB, ChildPath.POSTS);
        listFileHandler1.writeList(posts);
        ListFileHandler<Subscribers> listFileHandler2 = new ListFileHandler<>(DirectoryPath.DB, ChildPath.SUBSCRIBERS);
        listFileHandler2.writeList(subscribers);
        ListFileHandler<Chat> listFileHandler3 = new ListFileHandler<>(DirectoryPath.DB, ChildPath.CHAT);
        listFileHandler3.writeList(chats);
        ListFileHandler<Message> listFileHandler4 = new ListFileHandler<>(DirectoryPath.DB, ChildPath.MESSAGES);
        listFileHandler4.writeList(messages);
        ListFileHandler<User> listFileHandler5 = new ListFileHandler<>(DirectoryPath.DB, ChildPath.USERS);
        listFileHandler5.writeList(users);
        ListFileHandler<ConfirmationEmail> listFileHandler6 = new ListFileHandler<>(DirectoryPath.DB, ChildPath.EMAILS);
        listFileHandler6.writeList(confirmationEmails);
        ListFileHandler<Members> listFileHandler7 = new ListFileHandler<>(DirectoryPath.DB, ChildPath.MEMBERS);
        listFileHandler7.writeList(members);
        ListFileHandler<Group> listFileHandler8 = new ListFileHandler<>(DirectoryPath.DB, ChildPath.GROUPS);
        listFileHandler8.writeList(groups);
    }
}
