package com.pdp.frontend;

import com.pdp.backend.DTO.LoginDTO;
import com.pdp.backend.enums.MsgType;
import com.pdp.backend.enums.Role;
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
import com.pdp.backend.service.channel.ChannelService;
import com.pdp.backend.service.channel.ChannelServiceImpl;
import com.pdp.backend.service.chat.ChatService;
import com.pdp.backend.service.chat.ChatServiceImpl;
import com.pdp.backend.service.confirmation.email.ConfirmationEmailService;
import com.pdp.backend.service.confirmation.email.ConfirmationEmailServiceImpl;
import com.pdp.backend.service.group.GroupService;
import com.pdp.backend.service.group.GroupServiceImpl;
import com.pdp.backend.service.members.MembersService;
import com.pdp.backend.service.members.MembersServiceImpl;
import com.pdp.backend.service.message.MessageService;
import com.pdp.backend.service.message.MessageServiceImpl;
import com.pdp.backend.service.post.PostService;
import com.pdp.backend.service.post.PostServiceImpl;
import com.pdp.backend.service.subscribers.SubscribersService;
import com.pdp.backend.service.subscribers.SubscribersServiceImpl;
import com.pdp.backend.service.user.UserService;
import com.pdp.backend.service.user.UserServiceImpl;
import com.pdp.frontend.controller.LoginController;
import com.pdp.frontend.controller.UserController;
import com.pdp.frontend.notification.NotificationService;
import com.pdp.frontend.notification.NotificationServiceImpl;
import com.pdp.frontend.utils.ListUtils;
import com.pdp.frontend.utils.MenuUtils;
import com.pdp.frontend.utils.ScanUtils;

import java.text.SimpleDateFormat;
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
