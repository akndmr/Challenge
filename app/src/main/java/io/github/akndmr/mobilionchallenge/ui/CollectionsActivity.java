package io.github.akndmr.mobilionchallenge.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.akndmr.mobilionchallenge.R;
import io.github.akndmr.mobilionchallenge.adapter.CollectionAdapter;
import io.github.akndmr.mobilionchallenge.model.Collection;
import io.github.akndmr.mobilionchallenge.util.ConstantsUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class CollectionsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewAllCollections;

    private ArrayList<Collection> mCollections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);
        setUpToolbar();

        // Bind views
        mRecyclerViewAllCollections = findViewById(R.id.rv_all_collections);

        // Get intent data
        Intent intent = getIntent();
        if(intent.hasExtra(ConstantsUtil.EXTRA_ALL_COLLECTIONS)){
            mCollections = intent.getParcelableArrayListExtra(ConstantsUtil.EXTRA_ALL_COLLECTIONS);
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
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar_collections));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpRecyclerView(){
        CollectionAdapter collectionAdapter = new CollectionAdapter(this, mCollections, ConstantsUtil.TYPE_COLLECTIONS_V);
        mRecyclerViewAllCollections.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAllCollections.setAdapter(collectionAdapter);
    }
}
