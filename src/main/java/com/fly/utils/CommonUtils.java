package com.fly.utils;

import java.util.Random;

public class CommonUtils {

    public static int randomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    public static int randomModNum(int mod) {
        int randomNum = randomNum(99, 99999);
        return randomNum % mod;
    }

}
