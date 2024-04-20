package com.pdp.frontend.controller;

import com.pdp.backend.enums.MsgType;
import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.model.message.Message;
import com.pdp.backend.model.user.User;
import com.pdp.backend.service.chat.ChatService;
import com.pdp.backend.service.chat.ChatServiceImpl;
import com.pdp.backend.service.message.MessageService;
import com.pdp.backend.service.message.MessageServiceImpl;
import com.pdp.backend.service.user.UserService;
import com.pdp.backend.service.user.UserServiceImpl;
import com.pdp.frontend.notification.NotificationService;
import com.pdp.frontend.notification.NotificationServiceImpl;
import com.pdp.frontend.utils.ListUtils;
import com.pdp.frontend.utils.MenuUtils;
import com.pdp.frontend.utils.ScanUtils;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * @author Aliabbos Ashurov
 * Date: 19/April/2024  08:36
 **/
public class ChatController {
    private static final ChatService chatService = ChatServiceImpl.getInstance();
    private static final MessageService messageService = MessageServiceImpl.getInstance();
    private static final NotificationService notification = new NotificationServiceImpl();
    private static final UserService userService = UserServiceImpl.getInstance();
    public static void chat() {
        while (true) {
            MenuUtils.menu(MenuUtils.CHAT);
            switch (ScanUtils.scanInt()) {
                case 1 -> sendMessages();
                case 2 -> editMessages();
                case 3 -> deleteMessages();
                case 0 -> UserController.afterLog();
            }
        }
    }
    public static void search() {
        String query = ScanUtils.scanStr("Enter query");
        List<User> searched = userService.search(query);
        if (ListUtils.checkDataForNotNull(searched)) {
            List<User> collected = getValidUserList(searched);
            notification.checkData(collected);
            if (ListUtils.checkDataForNotNull(collected)) {
                printUserList(collected);
                User fromList = ListUtils.getFromList(collected, ScanUtils.scanInt());
                if (Objects.isNull(fromList)) return;
                sendMessageToChat(fromList.getId());
            }
        }
    }
    private static void sendMessages() {
        List<Chat> userChats = chatService.getUserChats(getCurrentUserID());
        if (ListUtils.checkDataForNotNull(userChats)) {
            List<User> collected = getUserListFromChats(userChats);
            if (ListUtils.checkDataForNotNull(collected)) {
                printUserList(collected);
                User fromList = ListUtils.getFromList(collected, ScanUtils.scanInt());
                if (Objects.isNull(fromList)) return;
                sendMessageToChat(fromList.getId());
                return;
            }
        }
        notification.notificationMessage("You don't have any chats", "", false);
    }
    private static void editMessages() {
        List<Chat> userChats = chatService.getUserChats(getCurrentUserID());
        if (ListUtils.checkDataForNotNull(userChats)) {
            Message message = getFromMessageUserChat(userChats);
            if (Objects.isNull(message)) return;
            message.setContent(ScanUtils.scanStr("Enter new message"));
            notification.notificationMessage("Message", "edited", true);
            return;
        }
        notification.notificationMessage("You don't have any message","",false);
    }
    private static void deleteMessages() {
        List<Chat> userChats = chatService.getUserChats(getCurrentUserID());
        if (ListUtils.checkDataForNotNull(userChats)) {
            Message fromMessageUserChat = getFromMessageUserChat(userChats);
            if (Objects.isNull(fromMessageUserChat)) return;
            messageService.delete(fromMessageUserChat.getId());
            notification.notificationMessage("Message", "deleted", true);
            return;
        }
        notification.notificationMessage("You don't have any message","",false);
    }
    private static Message getFromMessageUserChat(List<Chat> userChats) {
        List<User> collected = getUserListFromChats(userChats);
        if (ListUtils.checkDataForNotNull(collected)) {
            printUserList(collected);
            User fromList = ListUtils.getFromList(collected, ScanUtils.scanInt());
            if (Objects.nonNull(fromList)) {
                Chat chat = chatService.getOrCreate(getCurrentUserID(), fromList.getId());
                List<Message> chatMessage = messageService.getChatMessage(chat.getId());
                List<Message> collect = chatMessage.stream()
                        .filter(message -> message.getFromID().equals(getCurrentUserID()))
                        .collect(Collectors.toList());
                if (ListUtils.checkDataForNotNull(collect)) {
                    for (int i = 0; i < collect.size(); i++)
                        System.out.println((i + 1) + " - " + collect.get(i).getContent());
                    return ListUtils.getFromList(collect, ScanUtils.scanInt());
                }
                notification.notificationMessage("You don't have any message","",false);
            }
        }
        return null;
    }
    private static User getOtherUserInChat(Chat chat) {
        UUID otherUserID = chat.getUser1().equals(getCurrentUserID()) ? chat.getUser2() : chat.getUser1();
        return userService.getById(otherUserID);
    }
    private static List<User> getUserListFromChats(List<Chat> userChats) {
        return userChats.stream()
                .map(ChatController::getOtherUserInChat)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    private static void printUserList(List<User> userList) {
        for (int i = 0; i < userList.size(); i++) {
            System.out.println((i + 1) + " - " + userList.get(i).getUsername());
        }
    }
    private static List<User> getValidUserList(List<User> userList) {
        return userList.stream()
                .filter(user -> !user.getId().equals(getCurrentUserID()))
                .collect(Collectors.toList());
    }
    private static void sendMessageToChat(UUID receiverID) {
        Chat chat = chatService.getOrCreate(getCurrentUserID(), receiverID);
        while (true) {
            showChatMessages(chat);
            String content = ScanUtils.scanStr("[0] - Back | Enter content");
            if (content.equals("0")) break;
            Message message = new Message(getCurrentUserID(), chat.getId(), content, MsgType.USER);
            messageService.add(message);
        }
    }
    private static void showChatMessages(Chat chat) {
        List<Message> chatMessages = messageService.getChatMessage(chat.getId());
        if (ListUtils.checkDataForNotNull(chatMessages)) {
            chatMessages.forEach(message -> {
                if (message.getFromID().equals(getCurrentUserID())) {
                    printCurrentUserMessage(message);
                } else {
                    printOtherUserMessage(message);
                }
            });
        }
    }
    private static void printCurrentUserMessage(Message message) {
        System.out.println(STR."\t\t\t\t\t\t\t\{NotificationServiceImpl.ANSI_GREEN}\{userService.getById(getCurrentUserID()).getFullname()}\{NotificationServiceImpl.ANSI_RESET}");
        System.out.println("\t\t\t\t\t\t\t" + message.getContent());
        System.out.println("\t\t\t\t\t\t\t" + message.getDateTime());
    }
    private static void printOtherUserMessage(Message message) {
        User otherUser = getOtherUser(message);
        if (otherUser != null) {
            System.out.println(NotificationServiceImpl.ANSI_GREEN + otherUser.getFullname() + NotificationServiceImpl.ANSI_RESET);
        }
        System.out.println(message.getContent());
        System.out.println(message.getDateTime());
    }
    private static User getOtherUser(Message message) {
        UUID chatID = message.getChatID();
        Chat chat = chatService.getById(chatID);
        if (chat != null) {
            UUID otherUserID = message.getFromID().equals(chat.getUser1()) ? chat.getUser1() : chat.getUser2();
            return userService.getById(otherUserID);
        }
        return null;
    }
    private static UUID getCurrentUserID() {
        return UserController.currentUser.getId();
    }
}
