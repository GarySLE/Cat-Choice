package com.ys.zy.catchoice.content;

import java.io.Serializable;

/**
 * Created by Ys on 17/1/2.
 * ICellContent
 */

public interface ICellContent extends Serializable {

    /**
     * 获取String数据
     * @param flag use DataFlags in constant package
     * @return String
     */
    String getStringData(int flag);

    /**
     * 获取Int数据
     * @param flag use DataFlags in constant package
     * @return int
     */
    int getIntData(int flag);
}
