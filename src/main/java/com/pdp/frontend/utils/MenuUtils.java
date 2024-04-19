package com.pdp.frontend.utils;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  11:34
 **/
public interface MenuUtils {
    String LOGIN = "[1] - SIGN IN\n[2] - SIGN UP\n[0] - EXIT";//-------------------
    String USER = "[1] - SEARCH\n[2] - CHAT\n[3] - GROUP\n[4] - CHANNEL\n[5] - MY GROUP\n[6] - MY CHANNEL\n[7] - SETTINGS\n[0] - BACK";
    String CHANNEL = "[1] - CREATE\n[2] - JOIN\n[3] - LEAVE\n[4] - CHANNELS\n[0] - BACK"; //------------------
    String GROUP = "[1] - CREATE\n[2] - JOIN\n[3] - LEAVE\n[4] - GROUPS\n[0] - BACK";
    String CHAT = "[1] - SEND\n[2] - EDIT\n[3] - DELETE\n[0] - BACK";
    String SETTINGS = "[1] - CHANGE PASSWORD\n[2] - TWO STEP VERIFICATION\n[0] - BACK";//------------------
    String MY_GROUP = "[1] - SEND\n[2] - EDIT\n[3] - DELETE\n[4] - SHOW USERS\n[0] - BACK";
    String MY_CHANNEL = "[1] - POST\n[2] - EDIT\n[3] - DELETE\n[4] - SHOW POST\n[0] - BACK";//-
    static void menu(String type) {
        System.out.println(type);
    }
}
