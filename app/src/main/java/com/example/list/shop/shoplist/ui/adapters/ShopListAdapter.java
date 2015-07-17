package com.example.list.shop.shoplist.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.list.shop.shoplist.R;
import com.example.list.shop.shoplist.data.ProviderManager;
import com.example.list.shop.shoplist.data.models.ShopItem;


/**
 * Created by Alex on 01.07.2015.
 */
public class ShopListAdapter extends CursorAdapter {


    public ShopListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        final ShopItem model = ProviderManager.cursorToModel(cursor);
        viewHolder.title.setText(model.getTitle());
        viewHolder.desc.setText(model.getDescription());

    }
    public static class ViewHolder{
        public TextView desc;
        public TextView title;

        public ViewHolder(View view) {
          this.desc   = (TextView) view.findViewById(R.id.title_tv_list_item);
          this.title  = (TextView) view.findViewById(R.id.note_tv_list_item);
        }
    }
}
