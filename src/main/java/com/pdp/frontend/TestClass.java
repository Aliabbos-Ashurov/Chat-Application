package com.pdp.frontend;

import com.pdp.backend.model.confirmation.ConfirmationEmail;
import com.pdp.backend.model.user.User;
import com.pdp.backend.service.confirmation.email.ConfirmationEmailService;
import com.pdp.backend.service.confirmation.email.ConfirmationEmailServiceImpl;
import com.pdp.frontend.utils.ListUtils;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author Aliabbos Ashurov
 * Date: 15/April/2024  11:25
 **/
public class TestClass {
    @SneakyThrows
    public static void main(String[] args) {
        User user = new User("abbos", "abbos", "abbos");
        ConfirmationEmailService emailService = ConfirmationEmailServiceImpl.getInstance();
        while (true) {
           // boolean b = emailService.emailSender(user.getId(), "aliabbosashurov.forwork@gmail.com");
            //if (b) System.out.println("Successfully");
            //else System.out.println("Invalid");
            ListUtils.showListClearly(emailService.getAll());
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
