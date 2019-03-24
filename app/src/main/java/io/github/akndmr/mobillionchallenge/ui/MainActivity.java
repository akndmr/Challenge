package io.github.akndmr.mobillionchallenge.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.github.akndmr.mobillionchallenge.R;
import io.github.akndmr.mobillionchallenge.adapter.CategoryAdapter;
import io.github.akndmr.mobillionchallenge.adapter.CollectionAdapter;
import io.github.akndmr.mobillionchallenge.adapter.EditorShopAdapter;
import io.github.akndmr.mobillionchallenge.adapter.FeaturedPagerAdapter;
import io.github.akndmr.mobillionchallenge.adapter.NewShopAdapter;
import io.github.akndmr.mobillionchallenge.adapter.ProductAdapter;
import io.github.akndmr.mobillionchallenge.callback.OnFetchVitrinovaData;
import io.github.akndmr.mobillionchallenge.model.Category_;
import io.github.akndmr.mobillionchallenge.model.Collection;
import io.github.akndmr.mobillionchallenge.model.Featured;
import io.github.akndmr.mobillionchallenge.model.Product;
import io.github.akndmr.mobillionchallenge.model.Shop_;
import io.github.akndmr.mobillionchallenge.model.VitrinovaResponse;
import io.github.akndmr.mobillionchallenge.network.VitrinovaService;
import io.github.akndmr.mobillionchallenge.repository.VitrinRepository;
import io.github.akndmr.mobillionchallenge.util.ConstantsUtil;
import io.github.akndmr.mobillionchallenge.util.DialogUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener{

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String INDICATOR = "●";

    private VitrinovaService vitrinovaService;

    private Toolbar mToolbar;
    private SearchView mSearchView;
    private ViewPager mViewPagerFeatured;
    private RecyclerView mRecyclerViewProducts, mRecyclerViewCategories, mRecyclerViewCollections,
            mRecyclerViewEditorShops, mRecyclerViewNewShops;
    private TextView mTextViewProductTitle, mTextViewAllProducts, mTextViewCategoryTitle,
            mTextViewCollectionTitle, mTextViewAllCollections, mTextViewEditorShopTitle,
            mTextViewAllEditorShops, mTextViewNewShopTitle, mTextViewAllNewShops,
            mTextViewToolbarSearch;
    private LinearLayout mLayoutIndicatorContainer;
    private ImageView mImageViewEditorShopBackground;
    private View mContainerContents, mContainerProgress;

    private ArrayList<Featured> mFeaturedList;
    private ArrayList<Product> mProductList;
    private ArrayList<Category_> mCategoryList;
    private ArrayList<Collection> mCollectionList;
    private ArrayList<Shop_> mEditorShopList;
    private ArrayList<Shop_> mNewShopList;

    private ProductAdapter mProductAdapter;
    private CategoryAdapter mCategoryAdapter;
    private CollectionAdapter mCollectionAdapter;
    private EditorShopAdapter mEditorShopAdapter;
    private NewShopAdapter  mNewShopAdapter;


    private TextView [] mViewPagerIndicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views
        bindViews();

        // Set up toolbar
        setUpToolbar();

        // Set up searchview
     //   setUpSearhView();

        // API call - fetch data
        discoverVitrinova();

        // Set onclick listeners
        setListeners();

        // Set snap on scroll of stores
        addOnShopScrollListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_products_all:
                Intent intentProducts = new Intent(MainActivity.this, ProductsActivity.class);
                intentProducts.putParcelableArrayListExtra(ConstantsUtil.EXTRA_ALL_PRODUCTS, mProductList);
                startActivity(intentProducts);
                break;
            case R.id.tv_all_collections:
                Intent intentCollections = new Intent(MainActivity.this, CollectionsActivity.class);
                intentCollections.putParcelableArrayListExtra(ConstantsUtil.EXTRA_ALL_COLLECTIONS, mCollectionList);
                startActivity(intentCollections);
                break;
            case R.id.tv_all_editor_shops:
                Intent intentEditorShops = new Intent(MainActivity.this, EditorShopsActivity.class);
                intentEditorShops.putParcelableArrayListExtra(ConstantsUtil.EXTRA_ALL_EDITOR_SHOPS, mEditorShopList);
                startActivity(intentEditorShops);
                break;
            case R.id.tv_all_new_shops:
                Intent intentNewShops = new Intent(MainActivity.this, NewShopsActivity.class);
                intentNewShops.putParcelableArrayListExtra(ConstantsUtil.EXTRA_ALL_NEW_SHOPS, mNewShopList);
                startActivity(intentNewShops);
                break;
            case R.id.tv_toolbar_search_products:
                Intent searchIntent = new Intent(MainActivity.this, SearchResultsActivity.class);
                searchIntent.putExtra(SearchResultsActivity.SEARCH_DATA_LIST, mProductList);
                startActivity(searchIntent);
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for(int i=0; i < mFeaturedList.size(); i++){
            mViewPagerIndicators[i].setTextSize(14);
            mViewPagerIndicators[i].setTextColor(ContextCompat.getColor(this, R.color.colorLightText));
        }
        activateViewPagerIndicator(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vitrin_fake_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_mic:
                startActivityYo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void bindViews(){
        mToolbar                        = findViewById(R.id.toolbar_main);
        mViewPagerFeatured              = findViewById(R.id.vp_featured);
        mRecyclerViewProducts           = findViewById(R.id.rv_products_list);
        mTextViewProductTitle           = findViewById(R.id.tv_products_title);
        mTextViewAllProducts            = findViewById(R.id.tv_products_all);
        mRecyclerViewCategories         = findViewById(R.id.rv_categories);
        mTextViewCategoryTitle          = findViewById(R.id.tv_category_title);
        mRecyclerViewCollections        = findViewById(R.id.rv_collections);
        mTextViewCollectionTitle        = findViewById(R.id.tv_collection_title);
        mTextViewAllCollections         = findViewById(R.id.tv_all_collections);
        mLayoutIndicatorContainer       = findViewById(R.id.ll_page_indicator_container);
        mRecyclerViewEditorShops        = findViewById(R.id.rv_editor_shops);
        mTextViewEditorShopTitle        = findViewById(R.id.tv_editor_shop_title);
        mImageViewEditorShopBackground  = findViewById(R.id.iv_editor_shop_background);
        mTextViewAllEditorShops         = findViewById(R.id.tv_all_editor_shops);
        mRecyclerViewNewShops           = findViewById(R.id.rv_new_shops);
        mTextViewNewShopTitle           = findViewById(R.id.tv_new_shop_title);
        mTextViewAllNewShops            = findViewById(R.id.tv_all_new_shops);
        mTextViewToolbarSearch          = findViewById(R.id.tv_toolbar_search_products);
        mContainerContents              = findViewById(R.id.contents_activity_main_container);
        mContainerProgress              = findViewById(R.id.loading_container);
    }

    private void setUpToolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.ic_menu);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showDialogWith(MainActivity.this);
            }
        });
    }

    private void setUpSearhView(){
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(false);
        mSearchView.clearFocus();
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
    }

    private void setListeners(){
        mTextViewAllProducts.setOnClickListener(this);
        mTextViewAllCollections.setOnClickListener(this);
        mTextViewAllEditorShops.setOnClickListener(this);
        mTextViewAllNewShops.setOnClickListener(this);
        mViewPagerFeatured.addOnPageChangeListener(this);
        mTextViewToolbarSearch.setOnClickListener(this);
    }

    private void discoverVitrinova(){
        final VitrinRepository repository = VitrinRepository.getInstance();

        mContainerProgress.setVisibility(View.VISIBLE);

        repository.discoverVitrinova(new OnFetchVitrinovaData() {

            @Override
            public void onFetchCompleted() {
                mContainerProgress.setVisibility(View.GONE);
                mContainerContents.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFetchFeaturedResponse(VitrinovaResponse response) {
                mFeaturedList = (ArrayList<Featured>) response.getFeatured();
                setUpViewPager(mFeaturedList);
            }

            @Override
            public void onFetchProductResponse(VitrinovaResponse response) {
                mProductList = (ArrayList<Product>) response.getProducts();
                mProductAdapter = new ProductAdapter(MainActivity.this, mProductList, ConstantsUtil.ORIENTATION_H);
                LinearLayoutManager linearLayoutManagerProduct = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerViewProducts.setLayoutManager(linearLayoutManagerProduct);
                mRecyclerViewProducts.setAdapter(mProductAdapter);
                mTextViewProductTitle.setText(response.getTitle());
                mTextViewAllProducts.setText(getString(R.string.all_products));
            }

            @Override
            public void onFetchCategoryResponse(VitrinovaResponse response) {
                mCategoryList = (ArrayList<Category_>) response.getCategories();
                mCategoryAdapter = new CategoryAdapter(MainActivity.this, mCategoryList);
                LinearLayoutManager linearLayoutManagerCategory = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerViewCategories.setLayoutManager(linearLayoutManagerCategory);
                mRecyclerViewCategories.setAdapter(mCategoryAdapter);
                mTextViewCategoryTitle.setText(response.getTitle());
            }

            @Override
            public void onFetchCollectionResponse(VitrinovaResponse response) {
                mCollectionList = (ArrayList<Collection>) response.getCollections();
                mCollectionAdapter = new CollectionAdapter(MainActivity.this, mCollectionList, ConstantsUtil.TYPE_COLLECTIONS);
                LinearLayoutManager linearLayoutManagerCollection = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerViewCollections.setLayoutManager(linearLayoutManagerCollection);
                mRecyclerViewCollections.setAdapter(mCollectionAdapter);
                mTextViewCollectionTitle.setText(response.getTitle());
            }

            @Override
            public void onFetchEditorsShopResponse(VitrinovaResponse response) {
                mEditorShopList = (ArrayList<Shop_>) response.getShops();
                mEditorShopAdapter = new EditorShopAdapter(MainActivity.this, mEditorShopList);
                LinearLayoutManager linearLayoutManagerEditorShop = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                new LinearSnapHelper().attachToRecyclerView(mRecyclerViewEditorShops);
                mRecyclerViewEditorShops.setLayoutManager(linearLayoutManagerEditorShop);
                mRecyclerViewEditorShops.setAdapter(mEditorShopAdapter);
                mTextViewEditorShopTitle.setText(response.getTitle());

                String coverImageUrl = mEditorShopList.get(0).getCover().getMedium().getUrl();
                Picasso.get()
                        .load(coverImageUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(mImageViewEditorShopBackground);

                applyGrayFilter(mImageViewEditorShopBackground);
            }

            @Override
            public void onFetchNewShopResponse(VitrinovaResponse response) {
                mNewShopList = (ArrayList<Shop_>)response.getShops();
                mNewShopAdapter = new NewShopAdapter(MainActivity.this, mNewShopList, ConstantsUtil.TYPE_NEW_SHOPS_NO_BUTTON);
                LinearLayoutManager linearLayoutManagerNewShop = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                new LinearSnapHelper().attachToRecyclerView(mRecyclerViewNewShops);
                mRecyclerViewNewShops.setLayoutManager(linearLayoutManagerNewShop);
                mRecyclerViewNewShops.setAdapter(mNewShopAdapter);
                mTextViewNewShopTitle.setText(response.getTitle());
            }

            @Override
            public void onFetchError(String error) {
                Log.e(TAG, error);
            }
        });
    }


    // Set up viewpager & pager adapter for featured items
    private void setUpViewPager(ArrayList<Featured> featuredList){
        // Instantiate pager adapter
        FeaturedPagerAdapter featuredPagerAdapter = new FeaturedPagerAdapter(MainActivity.this, featuredList);

        // Set viewpager's adapter
        mViewPagerFeatured.setAdapter(featuredPagerAdapter);
        mViewPagerFeatured.setOffscreenPageLimit(2);
        mViewPagerFeatured.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float position) {
                if(position >= -1 && position <= 1){
                    view.findViewById(R.id.iv_featured_image).setTranslationX(-position * view.getWidth() / 2);
                } else {
                    view.setAlpha(1);
                }
            }
        });
        setUpViewPagerIndicators(featuredList.size());
        activateViewPagerIndicator(0);
    }

    private void startActivityYo(){
        Intent searchIntent = new Intent(MainActivity.this, SearchResultsActivity.class);
        searchIntent.putExtra(SearchResultsActivity.IS_VOICE_SEARCH, true);
        searchIntent.putParcelableArrayListExtra(SearchResultsActivity.SEARCH_DATA_LIST, mProductList);
        startActivity(searchIntent);
    }


    /**
     * Snaps items of RecyclerView on scroll and changes background image of container
     * according to visible snapped item
     */
    private void addOnShopScrollListener(){

        mRecyclerViewEditorShops.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){

                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    SnapHelper snapHelper = new LinearSnapHelper();
                    View snapView = snapHelper.findSnapView(layoutManager);
                    int snapPosition = layoutManager.getPosition(snapView);

                    Log.d("POSITION", "First item: " + String.valueOf(snapPosition));

                    Picasso.get()
                            .load(mEditorShopList.get(snapPosition).getCover().getMedium().getUrl())
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(mImageViewEditorShopBackground);

                   applyGrayFilter(mImageViewEditorShopBackground);

                }
            }
        });
    }


    /**
     * Saturation value 0 maps the color to gray-scale
     * @param imageview :Imageview to apply gray-scale filter on
     */
    private void applyGrayFilter(ImageView imageview){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageview.setColorFilter(filter);
    }


    /**
     * Adds indicators per page for ViewPager
     * @param pageCount - Number of pages
     */
    private void setUpViewPagerIndicators(int pageCount){

        mViewPagerIndicators = new TextView[pageCount];

        for(int i=0; i < mViewPagerIndicators.length; i++){
            mViewPagerIndicators[i] = new TextView(this);
            mViewPagerIndicators[i].setText(INDICATOR);

            mViewPagerIndicators[i].setTextSize(14);
            mViewPagerIndicators[i].setTextColor(ContextCompat.getColor(this, R.color.colorLightText));

            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(8,0,8,8);

            mLayoutIndicatorContainer.addView(mViewPagerIndicators[i], layoutParams);
        }
    }

    /**
     * Style active page indicator to differentiate it from inactive pages
     * @param position - Position of the active page
     */
    private void activateViewPagerIndicator(int position){
        mViewPagerIndicators[position].setTextSize(20);
        mViewPagerIndicators[position].setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
    }
}
