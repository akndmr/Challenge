package io.github.akndmr.mobilliumchallenge.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.akndmr.mobilliumchallenge.R;
import io.github.akndmr.mobilliumchallenge.adapter.NewShopAdapter;
import io.github.akndmr.mobilliumchallenge.model.Shop_;
import io.github.akndmr.mobilliumchallenge.util.ConstantsUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class NewShopsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewAllNewShops;

    private ArrayList<Shop_> mNewShops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shops);
        setUpToolbar();

        // Bind views
        mRecyclerViewAllNewShops = findViewById(R.id.rv_all_new_shops);

        // Get intent data
        Intent intent = getIntent();
        if(intent.hasExtra(ConstantsUtil.EXTRA_ALL_NEW_SHOPS)){
            mNewShops = intent.getParcelableArrayListExtra(ConstantsUtil.EXTRA_ALL_NEW_SHOPS);
        }

        // Set up RecyclerView
        setUpRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar(){
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar_new_shops));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpRecyclerView(){
        NewShopAdapter editorShopAdapter = new NewShopAdapter(this, mNewShops, ConstantsUtil.TYPE_NEW_SHOPS_W_BUTTON);
        mRecyclerViewAllNewShops.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAllNewShops.setAdapter(editorShopAdapter);
    }
}
