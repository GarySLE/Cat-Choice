package com.ys.zy.catchoice.model;

import com.ys.zy.catchoice.utils.RemarkUtil;

import java.util.HashMap;

/**
 * Created by Ys on 17/5/9.
 * Remarks
 */

public class Remarks {

    private HashMap<String, String> remarkMap;

    public Remarks(String... remarks) {
        this.remarkMap = new HashMap<>();
        for (int i = 0, c = remarks.length; i < c; i++) {
            remarkMap.put(RemarkUtil.getKeyOfRemark(i), remarks[i]);
        }
    }

    public Remarks(HashMap<String, String> remarkMap) {
        this.remarkMap = remarkMap;
    }

    public String getRemark(int index) {
        return remarkMap.get(RemarkUtil.getKeyOfRemark(index));
    }

    public String putRemark(int index, String text) {
        return remarkMap.put(RemarkUtil.getKeyOfRemark(index), text);
    }
}
