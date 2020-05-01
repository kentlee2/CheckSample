package com.example.myapplication;


public class ClickUtil {

    private static long sLastClickTime;

    public static boolean canClick() {
        long curTime = System.currentTimeMillis();
        if (curTime - sLastClickTime < 800) {
            return false;
        }
        sLastClickTime = curTime;
        return true;
    }

}
