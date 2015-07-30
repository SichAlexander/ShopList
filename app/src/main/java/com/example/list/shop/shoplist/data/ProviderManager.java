package com.example.list.shop.shoplist.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.example.list.shop.shoplist.data.models.ShopItem;
import com.example.list.shop.shoplist.data.models.ShopItemDBModel;

import java.util.Date;

import static com.example.list.shop.shoplist.data.models.ShopItemDBModel.DATE;
import static com.example.list.shop.shoplist.data.models.ShopItemDBModel.DESCRIPTION;
import static com.example.list.shop.shoplist.data.models.ShopItemDBModel.IS_ARCHIVE;
import static com.example.list.shop.shoplist.data.models.ShopItemDBModel.IS_DELEETE;
import static com.example.list.shop.shoplist.data.models.ShopItemDBModel.SHOP_LIST_CONTENT_URI;
import static com.example.list.shop.shoplist.data.models.ShopItemDBModel.TIME_REMIND;
import static com.example.list.shop.shoplist.data.models.ShopItemDBModel.TITLE;

/**
 * Created by njaka on 7/17/2015.
 */
public abstract class ProviderManager {
    private static String LOG_TAG = "ProviderManager";

    public static void insert(final Context context, final ShopItem shopItem) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, shopItem.getTitle());
        cv.put(DESCRIPTION, shopItem.getDescription());
        cv.put(DATE, new Date().getTime());
        cv.put(TIME_REMIND, shopItem.getTimeRemind());
        cv.put(IS_ARCHIVE, shopItem.isArchive());
        cv.put(IS_DELEETE, shopItem.isDelete());
        Uri newUri = context.getContentResolver().insert(SHOP_LIST_CONTENT_URI, cv);
        Log.d(LOG_TAG, "insert, result Uri : " + newUri.toString());
    }

    public static void update(final Context context, final ShopItem shopItem) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, shopItem.getTitle());
        cv.put(DESCRIPTION, shopItem.getDescription());
        cv.put(DATE, new Date().getTime());
        cv.put(TIME_REMIND, shopItem.getTimeRemind());
        cv.put(IS_ARCHIVE, shopItem.isArchive());
        cv.put(IS_DELEETE, shopItem.isDelete());
        Uri uri = ContentUris.withAppendedId(SHOP_LIST_CONTENT_URI, shopItem.getCurrentId());
        int cnt = context.getContentResolver().update(uri, cv, null, null);
        Log.d(LOG_TAG, "update, count = " + cnt);
    }

    public static void  delete(final Context context, final ShopItem shopItem) {
        Uri uri = ContentUris.withAppendedId(SHOP_LIST_CONTENT_URI, shopItem.getCurrentId());
        int cnt = context.getContentResolver().delete(uri, null, null);
        Log.d(LOG_TAG, "delete, count = " + cnt);
    }
    public static ShopItem cursorToModel(final Cursor cursor){
        ShopItem shopItem = new ShopItem();
        shopItem.setCurrentId(cursor.getInt(ShopItemDBModel.COL_ID));
        shopItem.setTitle(cursor.getString(ShopItemDBModel.COL_TITLE));
        shopItem.setDescription(cursor.getString(ShopItemDBModel.COL_DESCRIPTION));
        shopItem.setDate(cursor.getLong(ShopItemDBModel.COL_DATE));
        shopItem.setTimeRemind(cursor.getLong(ShopItemDBModel.COL_TIME_REMIND));
        shopItem.setIsArchive(cursor.getInt(ShopItemDBModel.COL_IS_ARCHIVE) == 1);
        shopItem.setIsDelete(cursor.getInt(ShopItemDBModel.COL_IS_DELETE) == 1);
        return shopItem;
    }

}
