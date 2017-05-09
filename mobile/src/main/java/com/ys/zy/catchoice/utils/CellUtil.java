package com.ys.zy.catchoice.utils;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.ys.zy.catchoice.content.ICellContent;
import com.ys.zy.catchoice.content.ImageCellContent;
import com.ys.zy.catchoice.content.TextAndImageContent;
import com.ys.zy.catchoice.content.TextCellContent;
import com.ys.zy.catchoice.db.GGColumns;
import com.ys.zy.catchoice.model.ImageOption;
import com.ys.zy.catchoice.model.TextAndImageOption;
import com.ys.zy.catchoice.model.TextOption;

/**
 * Created by Ys on 17/5/9.
 * CellUtil
 */

public class CellUtil {

    @Nullable
    public static ICellContent getCellContentFromDatabase(Cursor c) {
        ICellContent cellContent = null;
        String title = c.getString(c.getColumnIndex(GGColumns.TITLE));
        String image = c.getString(c.getColumnIndex(GGColumns.URI_IMAGE));
        int star = c.getInt(c.getColumnIndex(GGColumns.STAR));
        boolean hasTitle = !TextUtils.isEmpty(title);
        boolean hasImage = !TextUtils.isEmpty(image);
        if (hasTitle && hasImage) {
            cellContent = new TextAndImageContent(new TextAndImageOption(title, image, star));
        } else if (hasTitle) {
            cellContent = new TextCellContent(new TextOption(title, star));
        } else if (hasImage) {
            cellContent = new ImageCellContent(new ImageOption(image, star));
        }
        return cellContent;
    }
}
