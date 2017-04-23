package com.ys.zy.catchoice.utils;

import java.util.Random;

/**
 * Created by Ys on 17/1/16.
 * NumberUtil
 */

public class NumberUtil {

    public static int randomInt(int start, int end) {
        Random random = new Random();
        int i = random.nextInt(end);
        if (i < start)
            return i + start;
        else
            return i;
    }
}
