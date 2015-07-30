package com.example.list.shop.shoplist.ui.fragments;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.list.shop.shoplist.R;
import com.example.list.shop.shoplist.data.ProviderManager;
import com.example.list.shop.shoplist.data.models.ShopItem;
import com.example.list.shop.shoplist.ui.activities.MainActivity;
import com.example.list.shop.shoplist.ui.adapters.ShopListAdapter;

import static com.example.list.shop.shoplist.data.models.ShopItemDBModel.SHOP_LIST_CONTENT_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private ListView lvShopItems;

    private ShopListAdapter mAdapter;
    private static final int SHOP_LIST_LOADER = 0;
    private FloatingActionButton fab;
    private View vFooter;

    private AppCompatActivity mActivity;

    private static final String QUERY_STATE    = "query_state";
    private static final String ORDER_BY       = "order_by";
    private Loader<Cursor> mLoader;

    public ShopListFragment() {
        // Required empty public constructor
    }


    public static ShopListFragment newInstance(final String query, final String orderBy){
        ShopListFragment shopListFragment = new ShopListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(QUERY_STATE, query);
        bundle.putString(ORDER_BY, orderBy);
        shopListFragment.setArguments(bundle);
        return shopListFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mActivity = (AppCompatActivity) getActivity();
        View view   = inflater.inflate(R.layout.fragment_shop_list, container, false);
        vFooter     = inflater.inflate(R.layout.list_footer, container,false);
        findUI(view);
        initAdapter();
        mLoader = mActivity.getSupportLoaderManager().restartLoader(SHOP_LIST_LOADER, null, this);
        return view;
    }

    private void findUI(View view){
         lvShopItems = (ListView) view.findViewById(R.id.shop_list_lv_fragment_shop_list);
    }

    private void initAdapter(){
        mAdapter = new ShopListAdapter(getActivity(), null, false);
        lvShopItems.setAdapter(mAdapter);
        if (!((MainActivity)getActivity()).mTwoPane)
        lvShopItems.addFooterView(vFooter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String query = getArguments().getString(QUERY_STATE);
        String orderBy = getArguments().getString(ORDER_BY);
        Log.d("DoQuery", "" + query);

        return new CursorLoader(getActivity(),
                SHOP_LIST_CONTENT_URI,
                null,
                query,
                null,
                orderBy);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//        if (cursor != null && cursor.getCount() != 0){
            mAdapter.swapCursor(cursor);
//        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
//        mAdapter.swapCursor(null);
    }

}
