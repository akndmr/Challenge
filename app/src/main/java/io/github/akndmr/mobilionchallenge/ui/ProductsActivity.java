package io.github.akndmr.mobilionchallenge.ui;

import android.content.Intent;
import android.graphics.Rect;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import io.github.akndmr.mobilionchallenge.R;
import io.github.akndmr.mobilionchallenge.adapter.ProductAdapter;
import io.github.akndmr.mobilionchallenge.model.Product;
import io.github.akndmr.mobilionchallenge.util.ConstantsUtil;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewAllProducts;

    private ArrayList<Product> mProducts;

    private static final int GRID_SPAN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        setUpToolbar();

        // Bind views
        mRecyclerViewAllProducts = findViewById(R.id.rv_all_products);

        // Get intent data
        Intent intent = getIntent();
        if(intent.hasExtra(ConstantsUtil.EXTRA_ALL_PRODUCTS)){
            mProducts = intent.getParcelableArrayListExtra(ConstantsUtil.EXTRA_ALL_PRODUCTS);
        }

        // Set up RecyclerView
        setUpRecyclerView();
    }

    // Remove extra space in grid layout
    RecyclerView.ItemDecoration divider = new RecyclerView.ItemDecoration(){
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = 0;
            outRect.right = 0;
            outRect.bottom = 0;

            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = 0;
            } else {
                outRect.top = 0;
            }
        }
    };

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
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar_products));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, GRID_SPAN_COUNT);
        ProductAdapter productAdapter = new ProductAdapter(this, mProducts, ConstantsUtil.ORIENTATION_V);
        mRecyclerViewAllProducts.setLayoutManager(gridLayoutManager);

        mRecyclerViewAllProducts.addItemDecoration(divider);
        mRecyclerViewAllProducts.setAdapter(productAdapter);
    }
}
