package com.fly.utils;

import java.util.Random;

public class CommonUtils {

    public static int sleepMillisecond(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

}
