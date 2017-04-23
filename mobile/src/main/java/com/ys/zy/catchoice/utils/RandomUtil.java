package com.ys.zy.catchoice.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ys on 17/1/16.
 * RandomUtil
 */

public class RandomUtil {

    /**
     * 状态转移随机[start, end)
     * @param start 随机数最小随机区间
     * @param end 随机数最大随机区间
     * @param count (end - start) > count
     * @return int
     */
    public static int[] stateTransitionRandom(int start, int end, int count) {
        int[] state = new int[end + 1];
        for (int i = 0; i < count; i++) {
            int random = NumberUtil.randomInt(start, end);
            Log.e("stateRandom[" + i + "]:", random + "");
            if (state[random] == 0) {
                state[random] = random == end ? start : (random + 1);
            } else {
                // state transition
                int number = random;
                do {
                    number = state[number];
                } while (state[number] != 0);
                state[number] = number == end ? start : (number + 1);
            }
        }
        for (int i = 0; i < state.length; i++) {
            Log.e("state[" + i + "]:", state[i] + "");
        }
        return state;
    }

    /**
     * 迭代Floyd随机
     * @param start 随机数最小随机区间
     * @param end 随机数最大随机区间
     * @param count (end - count) >= start
     * @return List
     */
    public static List<Integer> iterationFloydRandom(int start, int end, int count) {
        List<Integer> list = new ArrayList<>();
        for (int i = end - count + 1; i < end; i++) {
            int random = NumberUtil.randomInt(start, i);
            if (list.contains(random)) {
                list.add(i);
            } else {
                list.add(random);
            }
        }
        for (int i = 0, c = list.size(); i < c; i++) {
            Log.e("random[" + i + "]", list.get(i) + "");
        }
        return list;
    }
}
