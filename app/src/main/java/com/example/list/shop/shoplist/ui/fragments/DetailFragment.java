package com.example.list.shop.shoplist.ui.fragments;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.list.shop.shoplist.AlarmReceiver;
import com.example.list.shop.shoplist.Constants;
import com.example.list.shop.shoplist.DialogManager;
import com.example.list.shop.shoplist.R;
import com.example.list.shop.shoplist.data.ProviderManager;
import com.example.list.shop.shoplist.data.models.ShopItem;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {


    private ShopItem mShopItem;
    private Activity mActivity;

    private EditText etTitle;
    private EditText etNote;
    private TextView tvRemind;
    private TextView tvDate;
    private ImageView ivRemind;

    Calendar calendar = Calendar.getInstance();

    public static DetailFragment newInstance(final ShopItem shopItem){
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.PARAM_SHOP_ITEM, shopItem);
        fragment.setArguments(bundle);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        if (getArguments() != null && getArguments().containsKey(Constants.PARAM_SHOP_ITEM)) {
            mShopItem = (ShopItem) getArguments().getSerializable(Constants.PARAM_SHOP_ITEM);
        }
        else {
            mShopItem = new ShopItem();
            mShopItem.setDate(0);
            mShopItem.setTimeRemind(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View veiw =  inflater.inflate(R.layout.fragment_detail, container, false);
        findUI(veiw);
        showData(mShopItem);
        setListeners();

        return veiw;
    }


    private void setListeners() {
        tvRemind.setOnClickListener(this);
    }

    private void showData(ShopItem shopItem) {
        if (mShopItem != null){
            if (isValid(shopItem.getTitle())) etTitle.setText(shopItem.getTitle());
            if (isValid(shopItem.getDescription())) etNote.setText(shopItem.getDescription());
            if (isValid(shopItem.getDate()))tvDate.setText(DateFormat.format(Constants.DATE_DAY_FORMAT,
                    shopItem.getDate()).toString());
            if (isValid(shopItem.getTimeRemind()))tvRemind.setText(DateFormat.format(Constants.DATE_REMIND_FORMAT,
                    shopItem.getTimeRemind()).toString());

        }
    }

    private void insertDataInModel() {
        if (mShopItem != null) {
             mShopItem.setTitle(etTitle.getText().toString());
             mShopItem.setDescription(etNote.getText().toString());
             mShopItem.setDate(new Date().getTime());
            saveData();
        }
    }

    private void saveData() {
        if (isValid(mShopItem.getDescription()) || isValid(mShopItem.getTitle())) {
            if (getArguments() != null && getArguments().containsKey(Constants.PARAM_SHOP_ITEM)) {
                ProviderManager.update(mActivity, mShopItem);
            } else {
                ProviderManager.insert(mActivity, mShopItem);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        insertDataInModel();
    }

    private void findUI(View veiw) {
        etTitle         = (EditText) veiw.findViewById(R.id.title_et_fragment_detail);
        etNote          = (EditText) veiw.findViewById(R.id.note_et_fragment_detail);
        tvDate          = (TextView) veiw.findViewById(R.id.date_tv_fragment_detail);
        tvRemind        = (TextView) veiw.findViewById(R.id.remind_tv_fragment_detail);
        ivRemind        = (ImageView) veiw.findViewById(R.id.remind_iv_fragment_detail);

    }

    private boolean isValid(String text){
        return  (text != null && !text.isEmpty());
    }

    private boolean isValid(Long value){
        return  (value != null && value != 0);

    }    private boolean isValid(EditText editText){
        return  (editText != null && !editText.getText().toString().isEmpty());
    }

    private void setAlarm(Calendar targetCal){


        Intent intent = new Intent(mActivity, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mActivity, 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)mActivity.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.remind_tv_fragment_detail:
                DialogManager.openTimePickerDialog(mActivity, this);
                break;
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calSet = (Calendar) calendar.clone();

        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calSet.set(Calendar.MINUTE, minute);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        if(calSet.compareTo(calendar) <= 0){
            //Today Set time passed, count to tomorrow
            calSet.add(Calendar.DATE, 1);
        }
        saveTimeRemind(calSet);
        setAlarm(calSet);
    }

    private void saveTimeRemind(Calendar targetCal){
        tvRemind.setText(targetCal.getTime() + "");
        mShopItem.setTimeRemind(targetCal.getTimeInMillis());
        showData(mShopItem);
    }
}
