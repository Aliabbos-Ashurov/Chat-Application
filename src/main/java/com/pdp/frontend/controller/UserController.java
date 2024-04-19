package com.pdp.frontend.controller;

import com.pdp.backend.model.user.User;
import com.pdp.frontend.utils.MenuUtils;
import com.pdp.frontend.utils.ScanUtils;
public class UserController {
    public static  User currentUser;
    public static void startApp() {
        currentUser = null;
        if (LoginController.userLoginSingUp()) {
            afterLog();
        } else startApp();
    }
    public static void afterLog() {
        while (true) {
            MenuUtils.menu(MenuUtils.USER);
            switch (ScanUtils.scanInt()) {
                case 1 -> ChatController.search();
                case 2 -> ChatController.chat();
                case 3 -> GroupController.group();
                case 4 -> ChannelController.channel();
                case 5 -> GroupController.myGroup();
                case 6 -> ChannelController.myChannel();
                case 7 -> ConfirmationController.settings();
                case 0 -> {
                    startApp();
                    currentUser = null;
                }
            }
        }
    }
}
