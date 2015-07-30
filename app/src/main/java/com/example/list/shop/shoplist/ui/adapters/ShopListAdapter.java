package com.example.list.shop.shoplist.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.list.shop.shoplist.Constants;
import com.example.list.shop.shoplist.R;
import com.example.list.shop.shoplist.data.ProviderManager;
import com.example.list.shop.shoplist.data.models.ShopItem;


/**
 * Created by Alex on 01.07.2015.
 */
public class ShopListAdapter extends CursorAdapter {
    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        void onItemSelected(ShopItem shopItem);
    }

    public ShopListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(final Context context, final Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        view.setTag(viewHolder);


        return view;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        final ShopItem model = ProviderManager.cursorToModel(cursor);
        viewHolder.setData(model);
        viewHolder.setListeners(context, model);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Callback) mContext)
                        .onItemSelected(model);
            }
        });
    }
    public static class ViewHolder{
        public TextView desc;
        public TextView title;
        public TextView remider;
        public RelativeLayout rlRemind;
        private CheckBox isDone;

        public ViewHolder(View view) {
            this.title      = (TextView) view.findViewById(R.id.tittle_tv_list_item);
            this.desc       = (TextView) view.findViewById(R.id.note_tv_list_item);
            this.remider = (TextView) view.findViewById(R.id.remind_tv_list_item);
            this.rlRemind   = (RelativeLayout) view.findViewById(R.id.remider_rl_list_item);
            this.isDone     = (CheckBox) view.findViewById(R.id.done_cb_list_item);
        }
        public void setData(ShopItem model) {
            if (model.getTitle() != null && !model.getTitle().isEmpty()){
                this.title.setText(model.getTitle());
                this.title.setVisibility(View.VISIBLE);
            } else {
                this.title.setVisibility(View.GONE);
            }
            if (model.getDescription() != null && !model.getDescription().isEmpty()){
                this.desc.setText(model.getDescription());
                this.desc.setVisibility(View.VISIBLE);
            } else {
                this.desc.setVisibility(View.GONE);
            }
            if (model.getTimeRemind() != 0){
                this.rlRemind.setVisibility(View.VISIBLE);
                this.remider.setText(DateFormat.format(Constants.DATE_REMIND_FORMAT,
                        model.getTimeRemind()).toString());
            } else {
                rlRemind.setVisibility(View.GONE);
            }
            this.isDone.setOnCheckedChangeListener(null);
            this.isDone.setChecked(model.isArchive());
        }
        public void setListeners(final Context context, final ShopItem model){

            this.isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    model.setIsArchive(isChecked);
                    ProviderManager.update(context, model);
                }
            });
        }
    }

}
