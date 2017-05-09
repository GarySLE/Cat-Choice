package com.ys.zy.catchoice.utils;

import com.ys.zy.catchoice.db.GGColumns;

/**
 * Created by Ys on 17/5/9.
 * RemarkUtil
 */

public class RemarkUtil {

    public static String getKeyOfRemark(int i) {
        switch (i) {
            case 1:
                return GGColumns.REMARK_1;
            case 2:
                return GGColumns.REMARK_2;
            case 3:
                return GGColumns.REMARK_3;
            case 4:
                return GGColumns.REMARK_4;
            case 5:
                return GGColumns.REMARK_5;
            default:
                return null;
        }
    }
}
