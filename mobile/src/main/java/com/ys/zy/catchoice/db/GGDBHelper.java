package com.ys.zy.catchoice.db;

import android.content.Context;

/**
 * Created by Ys on 17/5/4.
 * GG DBHelper
 */

public class GGDBHelper extends DBHelper {

    public GGDBHelper(Context context, String name, int version) {
        super(context, name, version);
        mClazz = new Class[]{GGColumns.class};
    }
}
