package com.example.list.shop.shoplist;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by njaka on 7/16/2015.
 */
public abstract class SeasonColorManager {

    public static SeasonThemeModel getThemeModel(final Context context) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        SeasonThemeModel themeModel = new SeasonThemeModel();
        switch (calendar.get(Calendar.MONTH)){
            case 0:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.january_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.january_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.january_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.january));
                break;
            case 1:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.february_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.february_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.february_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_02_february));
                break;
            case 2:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.march_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.march_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.march_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_03_march));
                break;
            case 3:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.april_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.april_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.april_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_04_april));
                break;
            case 4:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.may_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.may_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.may_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_05_may));
                break;
            case 5:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.june_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.june_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.june_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_06_june));
                break;

            case 6:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.july_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.july_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.july_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_07_july));
                break;
            case 7:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.august_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.august_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.august_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_08_august));
                break;
            case 8:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.september_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.september_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.september_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_09_september));
                break;
            case 9:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.october_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.october_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.october_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_10_october));
                break;
            case 10:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.november_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.november_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.november_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_11_november));
                break;
            case 11:
                themeModel.setPrimaryColor(context.getResources().getColor(R.color.december_colorPrimary));
                themeModel.setPrimaryDarkColor(context.getResources().getColor(R.color.december_colorPrimaryDark));
                themeModel.setColorAccent(context.getResources().getColor(R.color.december_colorAccent));
                themeModel.setMonthImage(context.getResources().getDrawable(R.drawable.bkg_12_december));
                break;
        }
        return themeModel;
    }


    public static void setTheme(Context context){
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        switch (calendar.get(Calendar.MONTH)){
            case 0:
                context.setTheme(R.style.JanuaryAppTheme);
                break;
            case 1:
                context.setTheme(R.style.FebruaryAppTheme);
                break;
            case 2:
                context.setTheme(R.style.MarchAppTheme);
                break;
            case 3:
                context.setTheme(R.style.AprilAppTheme);
                break;
            case 4:
                context.setTheme(R.style.MayAppTheme);
                break;
            case 5:
                context.setTheme(R.style.JuneAppTheme);
                break;
            case 6:
                context.setTheme(R.style.JulyAppTheme);
                break;
            case 7:
                context.setTheme(R.style.AugustAppTheme);
                break;
            case 8:
                context.setTheme(R.style.SeptemberAppTheme);
                break;
            case 9:
                context.setTheme(R.style.OctoberAppTheme);
                break;
            case 10:
                context.setTheme(R.style.NovemberAppTheme);
                break;
            case 11:
                context.setTheme(R.style.DecemberAppTheme);
                break;
        }
    }


}
