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

    @Column(type = Column.Type.INTEGER)
    public static final String STAR = "star";

    @Column
    public static final String ADDRESS = "address";

    @Column
    public static final String REMARK_1 = "remark_1";

    @Column
    public static final String REMARK_2 = "remark_2";

    @Column
    public static final String REMARK_3 = "remark_3";

    @Column
    public static final String REMARK_4 = "remark_4";

    @Column
    public static final String REMARK_5 = "remark_5";
}
