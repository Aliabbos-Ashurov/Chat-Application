package com.pdp.frontend.controller;

import com.pdp.backend.enums.Role;
import com.pdp.backend.model.channel.Channel;
import com.pdp.backend.model.post.Post;
import com.pdp.backend.model.subscribers.Subscribers;
import com.pdp.backend.service.channel.ChannelService;
import com.pdp.backend.service.channel.ChannelServiceImpl;
import com.pdp.backend.service.post.PostService;
import com.pdp.backend.service.post.PostServiceImpl;
import com.pdp.backend.service.subscribers.SubscribersService;
import com.pdp.backend.service.subscribers.SubscribersServiceImpl;
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
 * Date: 17/April/2024  13:56
 **/
public class ChannelController {
    private static final NotificationService notification = new NotificationServiceImpl();
    private static final ChannelService channelService = ChannelServiceImpl.getInstance();
    private static final PostService postService = PostServiceImpl.getInstance();
    private static final SubscribersService subscribersService = SubscribersServiceImpl.getInstance();
    public static void myChannel() {
        List<Channel> userChannels = channelService.getUserChannels(UserController.currentUser.getId());
        notification.checkData(userChannels);
        if (ListUtils.checkDataForNotNull(userChannels)) {
            while (true) {
                MenuUtils.menu(MenuUtils.MY_CHANNEL);
                switch (ScanUtils.scanInt()){
                    case 1 -> postChannel();
                    case 2 -> postEdit();
                    case 3 -> postDelete();
                    case 4 -> postShow();
                    case 0 -> UserController.afterLog();
                }
            }
        } else UserController.afterLog();
    }
    public static void channel() {
        while (true) {
            MenuUtils.menu(MenuUtils.CHANNEL);
            switch (ScanUtils.scanInt()) {
                case 1 -> channelCreate();
                case 2 -> channelJoin();
                case 3 -> channelLeave();
                case 4 -> channelJoined();
                case 0 -> {
                    return;
                }
            }
        }
    }
    private static void channelCreate() {
        String name = ScanUtils.scanStr("Enter channel name");
        String description = ScanUtils.scanStr("Enter description");
        boolean type = chooseGroupType();
        boolean isWorked;
        Channel channel = new Channel(UserController.currentUser.getId(), name,description,type);
        isWorked = channelService.add(channel);
        if (isWorked) isWorked = addSubscriberToChannel(channel, Role.ADMIN);
        notification.notificationMessage("Channel","created",isWorked);
    }
    private static void channelJoin() {
        String query = ScanUtils.scanStr("Enter query");
        List<Channel> searched = channelService.search(query);
        notification.checkData(searched);
        boolean isWorked = false;
        if (ListUtils.checkDataForNotNull(searched)) {
            ListUtils.showItemWithIndex(searched);
            int choose = ScanUtils.scanInt();
            Channel fromList = ListUtils.getFromList(searched, choose);
            if (Objects.nonNull(fromList) && !fromList.getOwnerID().equals(UserController.currentUser.getId())) isWorked  = addSubscriberToChannel(fromList,Role.USER);
        }
        notification.notificationMessage("Channel","joined",isWorked);
    }
    private static void channelLeave() {
        List<Subscribers> userJoinedChannel = subscribersService.getUserJoinedChannel(UserController.currentUser.getId());
        notification.checkData(userJoinedChannel);
        boolean isWorked = false;
        if (ListUtils.checkDataForNotNull(userJoinedChannel)) {
            for (int i = 0; i < userJoinedChannel.size(); i++) {
                UUID channelID = userJoinedChannel.get(i).getChannelID();
                Channel channel = channelService.getById(channelID);
                System.out.println((i + 1) + " - " + channel.getName());
            }
            int choose = ScanUtils.scanInt();
            Subscribers fromList = ListUtils.getFromList(userJoinedChannel, choose);
            if (Objects.nonNull(fromList)) isWorked = subscribersService.delete(fromList.getId());
        }
        notification.notificationMessage("Channel","left",isWorked);

    }
    private static void channelJoined() {
        List<Subscribers> userJoinedChannel = subscribersService.getUserJoinedChannel(UserController.currentUser.getId());
        notification.checkData(userJoinedChannel);
        if (ListUtils.checkDataForNotNull(userJoinedChannel)) {
            for (int i = 0; i < userJoinedChannel.size(); i++) {
                UUID channelID = userJoinedChannel.get(i).getChannelID();
                Channel channel = channelService.getById(channelID);
                System.out.println(STR."\{i + 1} - \{channel.getName()}");
            }
            int choose = ScanUtils.scanInt();
            Subscribers fromList = ListUtils.getFromList(userJoinedChannel, choose);
            if (Objects.nonNull(fromList))ListUtils.showListClearly(getChannelPost(fromList.getChannelID()));
        }
    }
    private static List<String> getChannelPost(UUID channelID) {
        List<Post> channelPost = postService.getChannelPost(channelID);
        return channelPost.stream()
                .map(Post::getContent)
                .collect(Collectors.toList());
    }
    private static void postShow() {
        Channel channel = showAndGetUserChannelOWN();
        if (Objects.isNull(channel)) return;
        List<Post> channelPost = postService.getChannelPost(channel.getId());
        notification.checkData(channelPost);
        if (ListUtils.checkDataForNotNull(channelPost)) channelPost.forEach(post -> System.out.println(post.getContent()));
    }
    private static void postDelete() {
        Channel channel = showAndGetUserChannelOWN();
        Post fromList = getPostByChannel(channel);
        if (Objects.isNull(fromList)) return;
        boolean deleted = postService.delete(fromList.getId());
        notification.notificationMessage("Post","deleted",deleted);
    }
    private static void postEdit() {
        Channel channel = showAndGetUserChannelOWN();
        Post fromList = getPostByChannel(channel);
        if (Objects.isNull(fromList))return;
        fromList.setContent(ScanUtils.scanStr("Enter new content"));
        notification.notificationMessage("Post","edited",true);
    }

    private static Post getPostByChannel(Channel channel) {
        if (Objects.isNull(channel)) return null;
        List<Post> channelPost = postService.getChannelPost(channel.getId());
        notification.checkData(channelPost);
        if (!ListUtils.checkDataForNotNull(channelPost)) return null;
        for (int i = 0; i < channelPost.size(); i++) {
            System.out.println((i + 1) + " - " + channelPost.get(i).getContent());
        }
        return ListUtils.getFromList(channelPost,ScanUtils.scanInt());
    }

    private static void postChannel() {
        Channel channel = showAndGetUserChannelOWN();
        if (Objects.nonNull(channel)) {
            String content = ScanUtils.scanStr("Enter content");
            postService.add(new Post(content, channel.getId()));
            notification.notificationMessage("Post", "added", true);
        }
    }
    private static Channel showAndGetUserChannelOWN() {
        List<Channel> userChannels = channelService.getUserChannels(UserController.currentUser.getId());
        notification.checkData(userChannels);
        if (ListUtils.checkDataForNotNull(userChannels)) {
            for (int i = 0; i < userChannels.size(); i++) {
                System.out.println((i + 1) + " - " + userChannels.get(i).getName());
            }
            int choose = ScanUtils.scanInt();
            return ListUtils.getFromList(userChannels, choose);
        }
        return null;
    }
    private static boolean addSubscriberToChannel(Channel channel,Role role) {
        channel.setSubscriberCount(channel.getSubscriberCount() + 1);
        return subscribersService.add(new Subscribers(UserController.currentUser.getId(),channel.getId(),role));
    }
    private static boolean chooseGroupType() {
        System.out.println("[1] - PUBLIC  [2] - PRIVATE");
        return ScanUtils.scanInt() == 2;
    }
}
