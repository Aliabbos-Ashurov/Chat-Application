package com.pdp.frontend.controller;

import com.pdp.backend.enums.MsgType;
import com.pdp.backend.enums.Role;
import com.pdp.backend.model.chat.Chat;
import com.pdp.backend.model.group.Group;
import com.pdp.backend.model.members.Members;
import com.pdp.backend.model.message.Message;
import com.pdp.backend.model.user.User;
import com.pdp.backend.service.chat.ChatService;
import com.pdp.backend.service.chat.ChatServiceImpl;
import com.pdp.backend.service.group.GroupService;
import com.pdp.backend.service.group.GroupServiceImpl;
import com.pdp.backend.service.members.MembersService;
import com.pdp.backend.service.members.MembersServiceImpl;
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

public class GroupController {
    private static final MembersService membersService = MembersServiceImpl.getInstance();
    private static final GroupService groupService = GroupServiceImpl.getInstance();
    private static final NotificationService notification = new NotificationServiceImpl();
    private static final ChatService chatService = ChatServiceImpl.getInstance();
    private static final MessageService messageService = MessageServiceImpl.getInstance();
    private static final UserService userService = UserServiceImpl.getInstance();
    public static void group() {
        while (true){
            MenuUtils.menu(MenuUtils.GROUP);
            switch (ScanUtils.scanInt()){
                case 1 -> groupCreate();
                case 2 -> groupJoin();
                case 3 -> groupLeave();
                case 4 -> groupJoined();
                case 0 -> UserController.afterLog();
            }
        }
    }
    public static void myGroup() {
        while (true) {
            MenuUtils.menu(MenuUtils.MY_GROUP);
            switch (ScanUtils.scanInt()){
                case 1 -> groupSend();
                case 2 -> groupEdit();
                case 3 -> groupDelete();
                case 4 -> groupShowUsers();
                case 0 -> UserController.afterLog();
            }
        }
    }
    private static void groupEdit(){
        Group group = showAndGetUserGroupOWN();
        if (Objects.isNull(group)) return;
        boolean isWorked = false;
        List<Chat> groupChat = chatService.getGroupChat(group.getId());
        List<Message> groupMessage = messageService.getGroupMessage(groupChat);
        if (ListUtils.checkDataForNotNull(groupMessage)) {
            List<Message> collected = groupMessage.stream()
                    .filter(message -> message.getFromID().equals(getCurrentUserID()))
                    .collect(Collectors.toList());
            if (ListUtils.checkDataForNotNull(collected)) {
                for (int i = 0; i < collected.size(); i++) {
                    System.out.println((i + 1) + " - " + collected.get(i).getContent());
                }
                Message fromList = ListUtils.getFromList(collected, ScanUtils.scanInt());
                if (Objects.isNull(fromList)) return;
                fromList.setContent(ScanUtils.scanStr("Enter new content"));
                isWorked = true;
            }
        }
        notification.notificationMessage("Message","edited",isWorked);
    }
    private static void groupDelete(){
        Group group = showAndGetUserGroupOWN();
        if (Objects.isNull(group)) return;
        List<Chat> groupChat = chatService.getGroupChat(group.getId());
        List<Message> groupMessage = messageService.getGroupMessage(groupChat);
        notification.checkData(groupMessage);
        boolean isWorked = false;
        if (ListUtils.checkDataForNotNull(groupMessage)) {
            for (int i = 0; i < groupMessage.size(); i++) {
                Message message = groupMessage.get(i);
                System.out.println((i + 1) + " - " + message.getContent());
            }
            Message fromList = ListUtils.getFromList(groupMessage, ScanUtils.scanInt());
            if (Objects.isNull(fromList)) return;
            isWorked = messageService.delete(fromList.getId());
        }
        notification.notificationMessage("Message","deleted",isWorked);
    }
    private static void groupShowUsers(){
        Group group = showAndGetUserGroupOWN();
        if (Objects.isNull(group)) return;
        List<Members> groupMembers = membersService.getGroupMembers(group.getId());
        notification.checkData(groupMembers);
        System.out.println("<<<\t\t" + group.getMemberCount() + " members\t\t>>>" );
        for (int i = 0; i < groupMembers.size(); i++)
            System.out.println((i + 1) + " - " + userService.getById(groupMembers.get(i).getUserID()).getFullname());
    }
    private static void groupSend() {
        Group group = showAndGetUserGroupOWN();
        if (Objects.isNull(group)) return;
        Chat chat = chatService.getOrCreate(getCurrentUserID(), group.getId());
        sendMessageToGroup(group, chat);
    }
    private static void sendMessageToGroup(Group group, Chat orCreate) {
        while (true){
            showGroupMessage(group);
            String content = ScanUtils.scanStr("[0] - Back | Enter content");
            if (content.equals("0")) break;
            Message message = new Message(getCurrentUserID(), orCreate.getId(), MsgType.GROUP, content,false);
            messageService.add(message);
        }
    }
    private static Group showAndGetUserGroupOWN() {
        List<Group> ownerGroups = groupService.getOwnerGroup(getCurrentUserID());
        notification.checkData(ownerGroups);
        if (ListUtils.checkDataForNotNull(ownerGroups)) {
            for (int i = 0; i < ownerGroups.size(); i++) {
                System.out.println((i + 1) + " - " + ownerGroups.get(i).getName());
            }
            return ListUtils.getFromList(ownerGroups,ScanUtils.scanInt());
        }
        return null;
    }
    private static void groupCreate() {
        String name = ScanUtils.scanStr("Enter group name");
        String description = ScanUtils.scanStr("Enter description");
        boolean type = chooseGroupType();
        Group group = new Group(getCurrentUserID(),name,description,type);
        boolean check1 = groupService.add(group);
        boolean check2 = false;
        if (check1) check2 = addMemberToGroup(group, Role.ADMIN);

        notification.notificationMessage("Group","created",check1 && check2);
    }
    private static void groupJoin() {
        List<Group> searched = groupService.search(ScanUtils.scanStr("Enter query"));
        notification.checkData(searched);
        boolean isWorked = false;
        if (ListUtils.checkDataForNotNull(searched)){
            ListUtils.showItemWithIndex(searched);
            int scanInt = ScanUtils.scanInt();
            Group fromList = ListUtils.getFromList(searched, scanInt);
            if (Objects.nonNull(fromList) && !fromList.getOwnerID().equals(getCurrentUserID())) isWorked = addMemberToGroup(fromList,Role.USER);
        }
        notification.notificationMessage("Group","joined",isWorked);
    }
    private static void groupLeave() {
        List<Members> joinedGroup = membersService.getUserJoinedGroup(getCurrentUserID());
        notification.checkData(joinedGroup);
        boolean isWorked= false;
        if (ListUtils.checkDataForNotNull(joinedGroup)) {
            for (int i = 0; i < joinedGroup.size(); i++) {
                UUID groupID = joinedGroup.get(i).getGroupID();
                Group group = groupService.getById(groupID);
                System.out.println(STR."\{i + 1} - \{group.getName()}");
            }
            int choose = ScanUtils.scanInt();
            Members fromList = ListUtils.getFromList(joinedGroup, choose);
            if (Objects.isNull(fromList)) return;
            isWorked = membersService.delete(fromList.getId());
            Group group = groupService.getById(fromList.getGroupID());
            group.setMemberCount(group.getMemberCount() - 1);
            sendMessageToGroupChat(group,userService.getById(getCurrentUserID()).getFullname() + " left from this group");
        }
        notification.notificationMessage("Group","left",isWorked);
    }
    private static void sendMessageToGroupChat(Group group, String content) {
        Chat chat = chatService.getOrCreate(group.getId(), group.getId());
        Message message = new Message(group.getId(), chat.getId(), MsgType.GROUP, content, false);
        messageService.add(message);
    }
    private static void groupJoined() {
        List<Members> joinedGroup = membersService.getUserJoinedGroup(getCurrentUserID());
        notification.checkData(joinedGroup);
        if (ListUtils.checkDataForNotNull(joinedGroup)) {
            for (int i = 0; i < joinedGroup.size(); i++) {
                UUID groupID = joinedGroup.get(i).getGroupID();
                Group group = groupService.getById(groupID);
                System.out.println((i + 1) + " - " + group.getName());
            }
            Members fromList = ListUtils.getFromList(joinedGroup, ScanUtils.scanInt());
            if (Objects.isNull(fromList)) return;
            Group group = groupService.getById(fromList.getGroupID());
            Chat chat = chatService.getOrCreate(getCurrentUserID(),group.getId());
            sendMessageToGroup(group,chat);
        }
    }
    private static void showGroupMessage(Group group) {
        List<Chat> groupChat = chatService.getGroupChat(group.getId());
        if (ListUtils.checkDataForNotNull(groupChat)) {
            List<Message> groupMessage = messageService.getGroupMessage(groupChat);
            if (ListUtils.checkDataForNotNull(groupMessage)) {
                groupMessage.forEach(message -> {
                    if (message.getFromID().equals(getCurrentUserID()))printCurrentUserMessage(message);
                    else printOtherUserMessage(message);
                });
            }
        }
    }
    private static void printCurrentUserMessage(Message message) {
        System.out.println(STR."\t\t\t\t\t\t\t\{NotificationServiceImpl.ANSI_GREEN}\{userService.getById(getCurrentUserID()).getFullname()}\{NotificationServiceImpl.ANSI_RESET}");
        System.out.println(STR."\t\t\t\t\t\t\t\{message.getContent()}");
        System.out.println(STR."\t\t\t\t\t\t\t\{message.getDateTime()}");
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
            UUID otherUserID = getOtherUserID(chat, message);
            return userService.getById(otherUserID);
        }
        return null;
    }
    private static UUID getOtherUserID(Chat chat, Message message) {
        return chat.getUser1().equals(message.getFromID()) ? chat.getUser1() : chat.getUser2();
    }
    private static boolean addMemberToGroup(Group group, Role role) {
        List<Members> joinedGroup = membersService.getUserJoinedGroup(getCurrentUserID());
        boolean noneMatch = joinedGroup.stream()
                .noneMatch(member -> member.getGroupID().equals(group.getId()));
        if (noneMatch) {
            group.setMemberCount(group.getMemberCount() + 1);
            String fullname = UserController.currentUser.getFullname();
            sendMessageToGroupChat(group, fullname + " joined in this group!");
            return membersService.add(new Members(getCurrentUserID(), group.getId(), role));
        }
        return false;
    }
    private static boolean chooseGroupType() {
        System.out.println("[1] - PUBLIC  [2] - PRIVATE");
        return ScanUtils.scanInt() == 2;
    }
    private static UUID getCurrentUserID() {
        return UserController.currentUser.getId();
    }
}
