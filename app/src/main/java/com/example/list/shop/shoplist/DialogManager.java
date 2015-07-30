package com.example.list.shop.shoplist;

import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by njaka on 7/21/2015.
 */
public abstract class DialogManager {

    public static void openTimePickerDialog(final Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener){
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                context,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(context));
        timePickerDialog.show();

    }
}
