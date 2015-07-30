package com.example.list.shop.shoplist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.list.shop.shoplist.data.models.ShopItemDBModel;

import java.util.Date;

/**
 * Created by njaka on 7/15/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "shopListDB";
    static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ShopItemDBModel.DB_CREATE);
//        ContentValues cv = new ContentValues();
//        for (int i = 1; i <= 13; i++) {
//            cv.put(ShopItemDBModel.TITLE, "TITLE " + i);
//            cv.put(ShopItemDBModel.DESCRIPTION, "DESC " + i);
//            cv.put(ShopItemDBModel.DATE, new Date().getTime());
//
//            db.insert(ShopItemDBModel.TABLE_NAME, null, cv);
//        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}