package com.fly.utils;

import java.util.Random;

public class CommonUtils {

    /**
     * 获取范围内随机数
     * @param min
     * @param max
     * @return
     */
    public static int randomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 获取指定取模随机数
     * @param modNum
     * @return
     */
    public static int randomModNum(int modNum) {
        int random = randomNum(99, 99999);
        return random % modNum;
    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++) {
            System.out.println(randomModNum(3));
        }
    }

}
