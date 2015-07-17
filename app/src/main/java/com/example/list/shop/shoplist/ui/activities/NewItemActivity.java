package com.example.list.shop.shoplist.ui.activities;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.list.shop.shoplist.R;
import com.example.list.shop.shoplist.SeasonColorManager;
import com.example.list.shop.shoplist.ui.fragments.DetailFragment;

public class NewItemActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SeasonColorManager.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        findUI();
        mToolbar.setBackgroundColor(SeasonColorManager.getThemeModel(this).getPrimaryColor());
        initMainToolbar();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_container_fl_activity_new_item, new DetailFragment(), null)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.homeAsUp) {
            finish();
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
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void findUI() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_activity_new_item);
    }
}
