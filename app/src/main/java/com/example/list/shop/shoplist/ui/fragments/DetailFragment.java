package com.example.list.shop.shoplist.ui.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.list.shop.shoplist.Constants;
import com.example.list.shop.shoplist.R;
import com.example.list.shop.shoplist.data.models.ShopItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    private ShopItem mShopItem;
    private Activity mActivity;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


}
