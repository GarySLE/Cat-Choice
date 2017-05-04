package com.ys.zy.catchoice.db;

import android.provider.BaseColumns;

/**
 * Created by Ys on 17/5/4.
 * Main Database Table
 */

public class GGColumns implements BaseColumns {

    @Column
    public static final String TITLE = "title";

    @Column
    public static final String URI_IMAGE = "uri_image";

    @Column
    public static final String ADDRESS = "address";
}
