package io.github.akndmr.mobillionchallenge.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.akndmr.mobillionchallenge.R;
import io.github.akndmr.mobillionchallenge.adapter.EditorShopVAdapter;
import io.github.akndmr.mobillionchallenge.model.Shop_;
import io.github.akndmr.mobillionchallenge.util.ConstantsUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class EditorShopsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewAllEditorShops;

    private ArrayList<Shop_> mEditorShops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_shops);
        setUpToolbar();

        // Bind views
        mRecyclerViewAllEditorShops = findViewById(R.id.rv_all_editor_shops);

        // Get intent data
        Intent intent = getIntent();
        if(intent.hasExtra(ConstantsUtil.EXTRA_ALL_EDITOR_SHOPS)){
            mEditorShops = intent.getParcelableArrayListExtra(ConstantsUtil.EXTRA_ALL_EDITOR_SHOPS);
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
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar_editor_shops));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpRecyclerView(){
        EditorShopVAdapter editorShopAdapter = new EditorShopVAdapter(this, mEditorShops);
        mRecyclerViewAllEditorShops.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAllEditorShops.setAdapter(editorShopAdapter);
    }
}
