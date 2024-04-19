package com.pdp.frontend.controller;

import com.pdp.backend.DTO.LoginDTO;
import com.pdp.backend.model.user.User;
import com.pdp.backend.service.user.UserService;
import com.pdp.backend.service.user.UserServiceImpl;
import com.pdp.frontend.notification.NotificationService;
import com.pdp.frontend.notification.NotificationServiceImpl;
import com.pdp.frontend.utils.MenuUtils;
import com.pdp.frontend.utils.ScanUtils;
/**
 * @author Aliabbos Ashurov
 * Date: 16/April/2024  16:25
 **/
public class LoginController {
    private static final NotificationService notification = new NotificationServiceImpl();
    public static UserService userService = UserServiceImpl.getInstance();
    public static boolean userLoginSingUp() {
        while (true) {
            MenuUtils.menu(MenuUtils.LOGIN);
            switch (ScanUtils.scanInt()) {
                case 1 -> {
                    return LoginController.logIn();
                }
                case 2 -> {
                    return LoginController.SingUp();
                }
                case 0 -> {
                    System.exit(0);
                    return false;
                }
            }
        }
    }

    private static boolean logIn() {
        var username = ScanUtils.scanStr("Enter username");
        var password = ScanUtils.scanStr("Enter password");
        var checkUserFromDB = userService.checkUserFromDB(new LoginDTO(username, password));
        if (checkUserFromDB) UserController.currentUser = userService.getUserByName(username);
        notification.notificationMessage("User","find",checkUserFromDB);
        return checkUserFromDB;
    }
    private static boolean SingUp() {
        String fullname = ScanUtils.scanStr("Enter fullname");
        String username = ScanUtils.scanStr("Enter username");
        String password = ScanUtils.scanStr("Enter password");
        User user = new User(fullname, username, password);
        var check = userService.add(user);
        if (check) UserController.currentUser = user;
        notification.notificationMessage("User","added",check);
        return check;
    }
}
