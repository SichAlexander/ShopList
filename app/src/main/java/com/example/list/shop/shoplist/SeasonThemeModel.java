package com.example.list.shop.shoplist;

import android.graphics.drawable.Drawable;

/**
 * Created by njaka on 7/17/2015.
 */
public class SeasonThemeModel {
    private int primaryColor;
    private int primaryDarkColor;
    private int colorAccent;
    private Drawable monthImage;

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public int getPrimaryDarkColor() {
        return primaryDarkColor;
    }

    public void setPrimaryDarkColor(int primaryDarkColor) {
        this.primaryDarkColor = primaryDarkColor;
    }

    public int getColorAccent() {
        return colorAccent;
    }

    public void setColorAccent(int colorAccent) {
        this.colorAccent = colorAccent;
    }

    public Drawable getMonthImage() {
        return monthImage;
    }

    public void setMonthImage(Drawable monthImage) {
        this.monthImage = monthImage;
    }
}
