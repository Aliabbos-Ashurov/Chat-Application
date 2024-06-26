package com.pdp.frontend.notification;

import java.util.List;
import java.util.Objects;
/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  13:36
 **/
public class NotificationServiceImpl implements NotificationService {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void checkData(List<?> list) {
        System.out.println(Objects.isNull(list) ||  list.isEmpty()
                ? ANSI_RED + "Data base is empty" + ANSI_RESET : ANSI_GREEN + "------------DATA------------" + ANSI_RESET);
    }
    @Override
    public void notificationMessage(String objName, String action, boolean isWorked) {
        String message = isWorked ? "successfully" : "failed";
        String colorCode = isWorked ? ANSI_GREEN : ANSI_RED;
        System.out.println(colorCode + objName + " " + message + " " + action + ANSI_RESET);
    }
}
