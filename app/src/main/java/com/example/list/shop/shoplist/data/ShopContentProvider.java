package com.example.list.shop.shoplist.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.example.list.shop.shoplist.data.models.ShopItemDBModel;

/**
 * Created by njaka on 7/15/2015.
 */

public class ShopContentProvider extends ContentProvider {
    final String LOG_TAG = "myLogs";


    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ShopItemDBModel.AUTHORITY, ShopItemDBModel.SHOP_LIST_PATH, ShopItemDBModel.URI_SHOP_ITEMS);
        uriMatcher.addURI(ShopItemDBModel.AUTHORITY, ShopItemDBModel.SHOP_LIST_PATH + "/#", ShopItemDBModel.URI_SHOP_ITEM);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    // чтение
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, "query, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case ShopItemDBModel.URI_SHOP_ITEMS: // общий Uri
                Log.d(LOG_TAG, "URI_SHOP_ITEMS");
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = ShopItemDBModel._ID + " DESC";
                }
                break;
            case ShopItemDBModel.URI_SHOP_ITEM: // Uri с ID
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_SHOP_ITEM, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = ShopItemDBModel._ID + " = " + id;
                } else {
                    selection = selection + " AND " + ShopItemDBModel._ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(ShopItemDBModel.TABLE_NAME, projection, selection,
                selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(),
                ShopItemDBModel.SHOP_LIST_CONTENT_URI);
        return cursor;
    }

    public Uri insert(Uri uri, ContentValues values) {
        Log.d(LOG_TAG, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != ShopItemDBModel.URI_SHOP_ITEMS)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(ShopItemDBModel.TABLE_NAME, null, values);
        Uri resultUri = ContentUris.withAppendedId(ShopItemDBModel.SHOP_LIST_CONTENT_URI, rowID);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(LOG_TAG, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case ShopItemDBModel.URI_SHOP_ITEMS:
                Log.d(LOG_TAG, "URI_SHOP_ITEMS");
                break;
            case ShopItemDBModel.URI_SHOP_ITEM:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_SHOP_ITEM, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = ShopItemDBModel._ID + " = " + id;
                } else {
                    selection = selection + " AND " + ShopItemDBModel._ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(ShopItemDBModel.TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(LOG_TAG, "update, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case ShopItemDBModel.URI_SHOP_ITEMS:
                Log.d(LOG_TAG, "URI_SHOP_ITEMS");

                break;
            case ShopItemDBModel.URI_SHOP_ITEM:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_SHOP_ITEM, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = ShopItemDBModel._ID + " = " + id;
                } else {
                    selection = selection + " AND " + ShopItemDBModel._ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(ShopItemDBModel.TABLE_NAME, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public String getType(Uri uri) {
        Log.d(LOG_TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case ShopItemDBModel.URI_SHOP_ITEMS:
                return ShopItemDBModel.CONTACT_SHOP_LIST_TYPE;
            case ShopItemDBModel.URI_SHOP_ITEM:
                return ShopItemDBModel.CONTACT_SHOP_ITEM_TYPE;
        }
        return null;
    }


}
