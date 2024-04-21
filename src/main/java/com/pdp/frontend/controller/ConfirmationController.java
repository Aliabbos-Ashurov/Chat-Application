package com.pdp.frontend.controller;

import com.pdp.backend.enums.MailType;
import com.pdp.backend.model.user.User;
import com.pdp.backend.service.confirmation.email.ConfirmationEmailService;
import com.pdp.backend.service.confirmation.email.ConfirmationEmailServiceImpl;
import com.pdp.frontend.notification.NotificationService;
import com.pdp.frontend.notification.NotificationServiceImpl;
import com.pdp.frontend.utils.MenuUtils;
import com.pdp.frontend.utils.ScanUtils;

import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 18/April/2024  09:35
 **/
public class ConfirmationController {
    private static final ConfirmationEmailService confirmationEmailService = ConfirmationEmailServiceImpl.getInstance();
    private static final NotificationService notification = new NotificationServiceImpl();
    public static void settings() {
        while (true) {
            MenuUtils.menu(MenuUtils.SETTINGS);
            switch (ScanUtils.scanInt()) {
                case 1 -> changePassword();
                case 2 -> twoStepVerification();
                case 0 -> {
                    return;
                }
            }
        }
    }
    private static void twoStepVerification() {
        if (!UserController.currentUser.isEmailVerified()) {
            String email = ScanUtils.scanStr("Enter your email");
            boolean sender = confirmationEmailService.emailSender(getCurrentUserID(), email, MailType.FOR_REGISTIRATION);
            if (sender) {
                notification.notificationMessage("Email","sent", sender);
                boolean emailSendingProcess = emailSendingProcess(sender, email, MailType.FOR_REGISTIRATION);
                if (emailSendingProcess) {
                    getCurrentUser().setEmail(email);
                    getCurrentUser().setEmailVerified(true);
                    notification.notificationMessage("Email","registered", emailSendingProcess);
                }
            } else {
                notification.notificationMessage("You have already code","",false);
                boolean emailSendingProcess = emailSendingProcess(sender, email, MailType.FOR_REGISTIRATION);
                if (emailSendingProcess) {
                    getCurrentUser().setEmail(email);
                    getCurrentUser().setEmailVerified(true);
                    notification.notificationMessage("Email","registered", emailSendingProcess);
                }
            }
        } else notification.notificationMessage("Email has been registered","",false);
    }

    private static boolean emailSendingProcess(boolean sender, String email,MailType mailType) {
        int codeByUser = confirmationEmailService.getConfirmationCodeByUser(getCurrentUserID(),mailType);
        int enterCode = ScanUtils.scanInt("Enter code");
        if (enterCode == codeByUser){
            sender = true;
            UserController.currentUser.setEmail(email);
            UserController.currentUser.setEmailVerified(sender);
        }
        else {
            sender = false;
            UserController.currentUser.setEmailVerified(sender);
            notification.notificationMessage("The entered code is incorrect","",false);
        }
        return sender;
    }
    private static void changePassword() {
        boolean sender;
        String newPassword = ScanUtils.scanStr("Enter new password");
        if (UserController.currentUser.isEmailVerified()) {
            sender = confirmationEmailService.emailSender(getCurrentUserID(), getCurrentUser().getEmail(),MailType.FOR_PASSWORD_CHANGING);
            if (sender) {
                notification.notificationMessage("Email","sent", sender);
                boolean emailSendingProcess = emailSendingProcess(sender, getCurrentUser().getEmail(),MailType.FOR_PASSWORD_CHANGING);
                if (emailSendingProcess) {
                    UserController.currentUser.setPassword(newPassword);
                    sender = emailSendingProcess;
                }
            } else {notification.notificationMessage("Try again in a minute","",false);}
        } else {
            UserController.currentUser.setPassword(newPassword);
            sender = true;
        }
        notification.notificationMessage("Password","changed",sender);
    }
    private static UUID getCurrentUserID() {
        return UserController.currentUser.getId();
    }
    private static User getCurrentUser() {
        return UserController.currentUser;
    }
}
