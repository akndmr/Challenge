package io.github.akndmr.mobilliumchallenge.ui;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import java.util.List;
import java.util.Locale;

import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.akndmr.mobilliumchallenge.R;
import io.github.akndmr.mobilliumchallenge.adapter.SearchAdapter;
import io.github.akndmr.mobilliumchallenge.model.Product;

public class SearchResultsActivity extends AppCompatActivity {

    private static final int GRID_SPAN_COUNT = 2;

    private static final int VOICE_RECOGNIZER_REQUEST_CODE = 888;
    public static final String SEARCH_DATA_LIST = "search_data_list";
    public static final String IS_VOICE_SEARCH = "is_voice_search";
    private static final String TAG = SearchResultsActivity.class.getSimpleName();

    SearchView mSearchView;
    RecyclerView mRecyclerViewSearchResults;

    SearchAdapter mSearchAdapter;

    List<Product> mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setUpToolbar();

        // Bind views
        bindViews();

        // Set up RecyclerView
        setUpRecyclerView();

        // Set up SearchView
        setUpSearchView();

        // Set listeners
        setListeners();

        // Handle search intent
        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
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

    private void bindViews(){
        mSearchView                 = findViewById(R.id.searchview);
        mRecyclerViewSearchResults  = findViewById(R.id.rv_search_results);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case VOICE_RECOGNIZER_REQUEST_CODE: {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    String query = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
                    mSearchView.setQuery(query, false);
                    mSearchAdapter = new SearchAdapter(SearchResultsActivity.this, mProductList);
                    mSearchAdapter.getFilter().filter(query);
                    mRecyclerViewSearchResults.setAdapter(mSearchAdapter);
                }
                break;
            }
        }
    }

    private void setListeners(){
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(mSearchAdapter != null){
                    mSearchAdapter.getFilter().filter(s);
                    mRecyclerViewSearchResults.setAdapter(mSearchAdapter);
                }
                else if(mProductList != null && mProductList.size() > 0){
                    mSearchAdapter = new SearchAdapter(SearchResultsActivity.this, mProductList);
                    mSearchAdapter.getFilter().filter(s);
                    mRecyclerViewSearchResults.setAdapter(mSearchAdapter);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(mSearchAdapter != null){
                    mSearchAdapter.getFilter().filter(s);
                    mRecyclerViewSearchResults.setAdapter(mSearchAdapter);
                }
                else if(mProductList != null && mProductList.size() > 0){
                    mSearchAdapter = new SearchAdapter(SearchResultsActivity.this, mProductList);
                    mSearchAdapter.getFilter().filter(s);
                    mRecyclerViewSearchResults.setAdapter(mSearchAdapter);
                }
                return false;
            }
        });
    }

    // Get the intent, verify the action and get the query
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            if(intent.getAction().equals("com.google.android.gms.actions.SEARCH_ACTION")){
                Log.d("SEARCH_LOG", "SEARCH ACTION");
            }

            // Write query on SearhView text field
            mSearchView.setQuery(query, false);

            Bundle appData = intent.getBundleExtra(SearchManager.APP_DATA);

            if (appData != null) {
                mProductList = appData.getParcelableArrayList(SEARCH_DATA_LIST);
                mSearchAdapter = new SearchAdapter(SearchResultsActivity.this, mProductList);
                mSearchAdapter.getFilter().filter(query);
                mRecyclerViewSearchResults.setAdapter(mSearchAdapter);
            }
        }
        // It's intended to be a voice search
        else if(intent.hasExtra(IS_VOICE_SEARCH)){
            if(intent.getBooleanExtra(IS_VOICE_SEARCH, false)){

                // Get product list
                mProductList = intent.getParcelableArrayListExtra(SEARCH_DATA_LIST);

                // Start voice recognizer
                final Intent voiceRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                voiceRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, R.string.say_product_name);
                voiceRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                voiceRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1500);
                voiceRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                startActivityForResult(voiceRecognizerIntent, VOICE_RECOGNIZER_REQUEST_CODE);
            }
        }
        // It's intended to be a text search
        else{
            mSearchView.setFocusable(true);
            mSearchView.requestFocusFromTouch();
            mProductList = intent.getParcelableArrayListExtra(SEARCH_DATA_LIST);
        }
    }

    private void setUpSearchView(){
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(false);
        mSearchView.clearFocus();
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }

    private void setUpToolbar(){
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar_search));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Set up RecyclerView
    private void setUpRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, GRID_SPAN_COUNT);
        mRecyclerViewSearchResults.setLayoutManager(gridLayoutManager);
        mRecyclerViewSearchResults.addItemDecoration(divider);
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

}
