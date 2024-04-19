package com.pdp.frontend.utils;

import com.pdp.backend.model.support.Displayable;

import static com.pdp.frontend.notification.NotificationServiceImpl.*;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  13:40
 **/
public interface ListUtils {
    static void showItemWithIndex(List<? extends Displayable> list) {
        for (int i = 0; i < list.size(); i++) {
            Displayable item = list.get(i);
            System.out.println((i + 1) + " - " + item.getName());
        }
    }
    static  void showListClearly(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(((i + 1) +" "+list.get(i)));
        }
        System.out.println(ANSI_GREEN + "----------------------------" + ANSI_RESET);
    }
    static <E> E getFromList(List<E> list,int index){
        if (index >= 1 && index <= list.size()) return list.get(index - 1);
        return null;
    }
    static boolean checkDataForNotNull(List<?> list) {
        return list != null && !list.isEmpty();
    }
}
