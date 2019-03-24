package io.github.akndmr.mobillionchallenge.callback;

import io.github.akndmr.mobillionchallenge.model.VitrinovaResponse;

/**
 * Created by Akın DEMİR on 22.03.2019.
 */
public interface OnFetchVitrinovaData {

    // Successful
    void onFetchCompleted();

    // Featured items/shops
    void onFetchFeaturedResponse(VitrinovaResponse response);

    // Products
    void onFetchProductResponse(VitrinovaResponse response);

    // Categories
    void onFetchCategoryResponse(VitrinovaResponse response);

    // Collections
    void onFetchCollectionResponse(VitrinovaResponse response);

    // Editor's choice shops
    void onFetchEditorsShopResponse(VitrinovaResponse response);

    // New shops
    void onFetchNewShopResponse(VitrinovaResponse response);

    // An error occurred while fetching movies
    void onFetchError(String error);
}
