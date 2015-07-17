package com.example.list.shop.shoplist.ui.fragments;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.list.shop.shoplist.R;
import com.example.list.shop.shoplist.data.ProviderManager;
import com.example.list.shop.shoplist.data.models.ShopItem;
import com.example.list.shop.shoplist.ui.adapters.ShopListAdapter;

import static com.example.list.shop.shoplist.data.models.ShopItemDBModel.SHOP_LIST_CONTENT_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {


    private ListView lvShopItems;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((Callback) getActivity())
                .onItemSelected(ProviderManager.cursorToModel((Cursor)parent.getItemAtPosition(position)));
    }

    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
         void onItemSelected(ShopItem shopItem);
    }

    private ShopListAdapter mAdapter;
    private static final int SHOP_LIST_LOADER = 0;
    public ShopListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        getActivity().getSupportLoaderManager().initLoader(SHOP_LIST_LOADER, null, this);
        findUI(view);
        initAdapter();
        setListeners();

        return view;
    }

    private void findUI(View view){
         lvShopItems = (ListView) view.findViewById(R.id.shop_list_lv_fragment_shop_list);
    }

    private void setListeners(){
        lvShopItems.setOnItemClickListener(this);
    }

    private void initAdapter(){
        mAdapter = new ShopListAdapter(getActivity(), null, false);
        lvShopItems.setAdapter(mAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(),
                SHOP_LIST_CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

}
