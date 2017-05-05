package com.ys.zy.catchoice.Manager;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ys.zy.catchoice.constant.DataFlags;
import com.ys.zy.catchoice.content.ICellContent;
import com.ys.zy.catchoice.db.GGColumns;
import com.ys.zy.catchoice.db.SQLiteOperator;
import com.ys.zy.catchoice.multiple.MultiCell;

/**
 * Created by Ys on 17/5/5.
 * OptionManager
 */

public class OptionManager {

    public static void add(@NonNull SQLiteOperator operator, @NonNull MultiCell cell) {
        ContentValues optionValues = getOptionValues(cell.mContent);
        operator.insert(GGColumns.class, optionValues);
        operator.close();
    }

    public static int update(@NonNull SQLiteOperator operator, @NonNull MultiCell cell,
                             @NonNull String[] selection) {
        if (selection.length < 2) {
            throw new IllegalArgumentException("need 2 selection string!");
        }

        String where = getWhereString(selection);
        String[] selectionArgs = getSelectionArgs(selection);

        ContentValues optionValues = getOptionValues(cell.mContent);
        return operator.update(GGColumns.class, optionValues, where, selectionArgs);
    }

    public static void updateOrAdd(@NonNull SQLiteOperator operator, @NonNull MultiCell cell,
                                   @NonNull String[] selection) {
        if (selection.length < 2) {
            throw new IllegalArgumentException("need 2 selection string!");
        }

        String where = getWhereString(selection);
        String[] selectionArgs = getSelectionArgs(selection);

        ContentValues optionValues = getOptionValues(cell.mContent);
        operator.updateOrInsert(GGColumns.class, optionValues, where, selectionArgs);
    }

    private static String[] getSelectionArgs(String[] selection) {
        String[] selectionArgs = null;
        boolean hasTitle = !TextUtils.isEmpty(selection[0]);
        boolean hasImage = !TextUtils.isEmpty(selection[1]);
        if (hasTitle && hasImage) {
            selectionArgs = new String[]{selection[0], selection[1]};
        } else if (hasTitle) {
            selectionArgs = new String[]{selection[0]};
        } else if (hasImage) {
            selectionArgs = new String[]{selection[1]};
        }
        return selectionArgs;
    }

    private static String getWhereString(String[] selection) {
        StringBuilder where = new StringBuilder();
        boolean hasTitle = !TextUtils.isEmpty(selection[0]);
        boolean hasImage = !TextUtils.isEmpty(selection[1]);
        if (hasTitle) {
            where.append(GGColumns.TITLE + " = ?");
        }
        if (hasImage) {
            if (hasTitle) {
                where.append(" AND ");
            }
            where.append(GGColumns.URI_IMAGE + " = ?");
        }
        return where.toString();
    }

    private static ContentValues getOptionValues(@NonNull ICellContent content) {
        ContentValues values = new ContentValues();
        values.put(GGColumns.TITLE, content.getStringData(DataFlags.FLAG_TITLE));
        values.put(GGColumns.URI_IMAGE, content.getStringData(DataFlags.FLAG_IMAGE));
        values.put(GGColumns.STAR, content.getIntData(DataFlags.FLAG_STAR));
        values.put(GGColumns.ADDRESS, content.getStringData(DataFlags.FLAG_ADDRESS));
        return values;
    }
}
