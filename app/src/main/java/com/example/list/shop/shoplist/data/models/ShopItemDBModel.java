package com.example.list.shop.shoplist.data.models;

import android.net.Uri;

/**
 * Created by njaka on 7/15/2015.
 */
public class ShopItemDBModel {
    public static final String TABLE_NAME = "shopList";

    public static final String _ID                 = "_id";
    public static final String TITLE               = "title";
    public static final String DESCRIPTION         = "description";
    public static final String DATE                = "date";
    public static final String TIME_REMIND         = "time_remind";
    public static final String IS_ARCHIVE          = "is_archive";
    public static final String IS_DELEETE          = "is_delete";

    public static final int COL_TITLE              = 1;
    public static final int COL_DESCRIPTION        = 2;
    public static final int COL_DATE               = 3;
    public static final int COL_TIME_REMIND        = 4;
    public static final int COL_IS_ARCHIVE         = 5;
    public static final int COL_IS_DELETE          = 6;




    public static final String DB_CREATE = "create table " + TABLE_NAME + "("
            + _ID + " integer primary key autoincrement, "
            + TITLE + " text, " + DESCRIPTION + " text,"  + DATE + " int, " + TIME_REMIND + " int, " + IS_ARCHIVE + " int, " + IS_DELEETE + " int" + ");";

    public static final String AUTHORITY = "com.example.list.shop.shoplist";

    public static final String SHOP_LIST_PATH = "shopList";

    public static final Uri SHOP_LIST_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + SHOP_LIST_PATH);

    public static final String CONTACT_SHOP_LIST_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + SHOP_LIST_PATH;

    public static final String CONTACT_SHOP_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + SHOP_LIST_PATH;

    public static final int URI_SHOP_ITEMS = 1;

    public static final int URI_SHOP_ITEM = 2;
}