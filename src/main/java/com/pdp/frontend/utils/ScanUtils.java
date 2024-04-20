package com.pdp.frontend.utils;

import java.util.Scanner;
/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  11:30
 **/
public interface ScanUtils {
    Scanner strScan = new Scanner(System.in);
    Scanner numScan = new Scanner(System.in);
    static String scanStr(String message) {
        System.out.print(message+"-> ");
        return strScan.nextLine();
    }
    static String scanStr() {
        System.out.print("->");
        return strScan.nextLine();
    }
    static int scanInt(String message) {
        System.out.print(message+"-> ");
        return numScan.nextInt();
    }
    static int scanInt() {
        System.out.print("-> ");
        return numScan.nextInt();
    }
}
