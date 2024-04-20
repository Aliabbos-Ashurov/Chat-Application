package com.pdp.frontend.notification;

import java.util.List;
/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  13:36
 **/
public interface NotificationService {
    void checkData(List<?> list);
    void notificationMessage(String objName,String action,boolean isWorked);
}
