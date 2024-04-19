package com.pdp.backend.defaultDB;

import com.pdp.backend.enums.Role;
import com.pdp.backend.model.channel.Channel;
import com.pdp.backend.model.group.Group;
import com.pdp.backend.model.members.Members;
import com.pdp.backend.model.post.Post;
import com.pdp.backend.model.user.User;
import com.pdp.backend.service.channel.ChannelServiceImpl;
import com.pdp.backend.service.group.GroupServiceImpl;
import com.pdp.backend.service.members.MembersServiceImpl;
import com.pdp.backend.service.post.PostServiceImpl;
import com.pdp.backend.service.subscribers.SubscribersServiceImpl;
import com.pdp.backend.service.user.UserServiceImpl;

/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  10:29
 **/
public interface DefaultDATA {
    User baseUser = new User("Aliabbos Ashurov", "a", "a");
    Channel channel1 = new Channel(baseUser.getId(),"KUNUZ","news uzbekistan",false);
    Channel channel2 = new Channel(baseUser.getId(), "QALAMPIRUZ", "news uzbekistan", false);
    Group group1 = new Group(baseUser.getId(), "UZBEKISTAN PROGRAMMERS", "JAVA,PYTHON", false);
    Group group2 = new Group(baseUser.getId(), "OPEN-BUDJET", "OVOZ BERILA", false);
    static void defaultUser(UserServiceImpl userService) {
        /*userService.add(baseUser);
        userService.add(new User("John Teshmatov","John Teshmatov","b"));
        userService.add(new User("John Eshmatov","John Eshmatov","c"));
        userService.add(new User("EXAMPLE 1","q","q"));
        userService.add(new User("EXAMPLE 2","w","w"));*/
    }
    static void defaultChannel(ChannelServiceImpl channelService) {
        /*channelService.add(channel1);
        channelService.add(channel2);*/
    }
    static void defaultGroup(GroupServiceImpl groupService) {
        /*groupService.add(group1);
        groupService.add(group2);*/
    }
    static void defaultPost(PostServiceImpl postService) {
        /*postService.add(new Post("Subscribers enjoy more with New York Times All Access.",channel2.getId()));
        postService.add(new Post("Get the New York Times International paper delivered, plus the full digital experience.",channel2.getId()));
        postService.add(new Post("The Central Bank of Uzbekistan does not support the government’s efforts to keep track of transfers between bank cards.",channel1.getId()));
        postService.add(new Post("The inflation expectations of Uzbeks decreased slightly in March, according to a survey conducted by the Central Bank.",channel1.getId()));*/
    }
}
