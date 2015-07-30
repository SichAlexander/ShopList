package com.example.list.shop.shoplist.ui.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.list.shop.shoplist.Constants;
import com.example.list.shop.shoplist.R;
import com.example.list.shop.shoplist.SeasonColorManager;
import com.example.list.shop.shoplist.SeasonThemeModel;
import com.example.list.shop.shoplist.data.models.ShopItem;
import com.example.list.shop.shoplist.data.models.ShopItemDBModel;
import com.example.list.shop.shoplist.ui.adapters.ShopListAdapter;
import com.example.list.shop.shoplist.ui.fragments.DetailFragment;
import com.example.list.shop.shoplist.ui.fragments.ShopListFragment;

import java.util.Calendar;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener,ShopListAdapter.Callback {

    private static final int SHOP_LIST_LOADER = 0;
    private static Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private static final String DETAIL_FRAGMENT_TAG = "DF_TAG";
    private static final String MAIN_FRAGMENT_TAG = "MAIN_TAG";
    private final String QUERY_IS_ARCHIVE   = ShopItemDBModel.IS_ARCHIVE + " = 1";
    private final String QUERY_IS_DELETE    = ShopItemDBModel.IS_DELEETE + " = 1";
    private final String QUERY_REMINDERS    = ShopItemDBModel.TIME_REMIND + " > 0";


    private final String ORDER_BY_ID        = ShopItemDBModel._ID;
    private final String ORDER_BY_ARCHIVE   = ShopItemDBModel._ID + ", " +ShopItemDBModel.IS_ARCHIVE + " ASC";
    private final String ORDER_BY_DELETE    = ShopItemDBModel._ID + ", " +ShopItemDBModel.IS_DELEETE;



    private SeasonThemeModel mThemeModel;
    public boolean mTwoPane;
    private LinearLayout mNavigationHeader;
    private FloatingActionButton mFloatingActionButton;
    private NavigationView mNavigationView;

    private TextView mHeaderMonth;
    private TextView mHeaderDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SeasonColorManager.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findUI();
        setColorTheme();
        initMainToolbar();
        showFragments(savedInstanceState);
        showShopListFragment(null,null);
        setListeners();
        setHeaderDate();
    }

    private void setHeaderDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            String dateString = DateFormat.format(Constants.DATE_FULL_FORMAT, calendar.getTimeInMillis()).toString();
            Log.d("date", "" + dateString);
        mHeaderDay.setText(DateFormat.format(Constants.DATE_DAY_FORMAT, calendar.getTimeInMillis()).toString());
        mHeaderMonth.setText(DateFormat.format(Constants.DATE_MONTH_FORMAT, calendar.getTimeInMillis()).toString());
    }

    private void setColorTheme() {
        mThemeModel = SeasonColorManager.getThemeModel(this);
        mToolbar.setBackgroundColor(mThemeModel.getPrimaryColor());
        mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(mThemeModel.getColorAccent()));
        mNavigationHeader.setBackground(mThemeModel.getMonthImage());
    }

    private void showFragments(Bundle savedInstanceState) {
        showShopListFragment(null,null);

        if (findViewById(R.id.detail_container_fl_main_activity) != null){
            mTwoPane = true;
            showDetailFragment(savedInstanceState);
        } else {
            mTwoPane = false;
        }
    }

    private void showDetailFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container_fl_main_activity, new DetailFragment(), DETAIL_FRAGMENT_TAG)
                    .commit();
        }
    }

    private void showShopListFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container_fl_main_activity, ShopListFragment.newInstance(null, null), MAIN_FRAGMENT_TAG)
                    .commit();
        }
    }
    private void showShopListFragment(final String query, final String orderBy) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container_fl_main_activity, ShopListFragment.newInstance(query, orderBy), null)
                    .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void initMainToolbar() {
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
    }


    private void openNewItemActivity(){
        Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
        startActivity(intent);

    }

    private void findUI(){
        mNavigationView             = (NavigationView) findViewById(R.id.navigation_view_activity_main);
        mToolbar                    = (Toolbar) findViewById(R.id.toolbar_activity_new_item);
        mFloatingActionButton       = (FloatingActionButton) findViewById(R.id.new_shop_item_fab_main_activity);
        mDrawerLayout               = (DrawerLayout) findViewById(R.id.drawer_layout_main_activity);
        findHeaderUI(mDrawerLayout);
    }

    private void findHeaderUI(DrawerLayout layout) {
        mNavigationHeader           = (LinearLayout) layout.findViewById(R.id.background_header_ll_nav_header);
        mHeaderMonth                = (TextView) layout.findViewById(R.id.month_tv_navigation_header);
        mHeaderDay                  = (TextView) layout.findViewById(R.id.day_tv_navigation_header);
    }


    private void setListeners(){
        mFloatingActionButton.setOnClickListener(this);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.new_shop_item_fab_main_activity:
                openNewItemActivity();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        switch (menuItem.getItemId()){
            case R.id.nav_shop_list:
                Toast.makeText(MainActivity.this, " nav_shop_list ", Toast.LENGTH_SHORT).show();
                showShopListFragment(null, ORDER_BY_ARCHIVE);
                break;
            case R.id.nav_archive:
                Toast.makeText(MainActivity.this, " nav_archive ", Toast.LENGTH_SHORT).show();
                showShopListFragment(QUERY_IS_ARCHIVE, ORDER_BY_ID);
                break;
            case R.id.nav_remiders:
                Toast.makeText(MainActivity.this, " nav_remiders ", Toast.LENGTH_SHORT).show();
                showShopListFragment(QUERY_REMINDERS, ORDER_BY_ARCHIVE);
                break;
            case R.id.nav_deleted:
                Toast.makeText(MainActivity.this, " nav_deleted ", Toast.LENGTH_SHORT).show();
                showShopListFragment(QUERY_IS_DELETE, ORDER_BY_ID);

                break;
        }
        mDrawerLayout.closeDrawers();

        return true;
    }

    @Override
    public void onItemSelected(ShopItem shopItem) {
        if (mTwoPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container_fl_main_activity, DetailFragment.newInstance(shopItem))
                    .commit();
        } else {
            Intent intent = new Intent(this, NewItemActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.PARAM_SHOP_ITEM, shopItem);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
