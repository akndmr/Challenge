package io.github.akndmr.mobilionchallenge.repository;

import java.util.List;

import io.github.akndmr.mobilionchallenge.callback.OnFetchVitrinovaData;
import io.github.akndmr.mobilionchallenge.model.VitrinovaResponse;
import io.github.akndmr.mobilionchallenge.network.RestClient;
import io.github.akndmr.mobilionchallenge.network.VitrinovaService;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Akın DEMİR on 22.03.2019.
 */
public class VitrinRepository {

    private VitrinovaService mVitrinovaService = new RestClient().mVitrinovaService;

    private static VitrinRepository instance;

    private VitrinRepository(){}

    public static VitrinRepository getInstance(){

        if(instance == null){
            instance = new VitrinRepository();
        }
        return instance;
    }

    public void discoverVitrinova(final OnFetchVitrinovaData callback){

        Call<List<VitrinovaResponse>> call = mVitrinovaService.getItems();

        call.enqueue(new Callback<List<VitrinovaResponse>>() {
            @Override
            public void onResponse(Call<List<VitrinovaResponse>> call, retrofit2.Response<List<VitrinovaResponse>> response) {

                List<VitrinovaResponse> responseList = response.body();

                // Get response items
                if(responseList != null)
                {
                    callback.onFetchCompleted();
                    callback.onFetchFeaturedResponse(responseList.get(0));
                    callback.onFetchProductResponse(responseList.get(1));
                    callback.onFetchCategoryResponse(responseList.get(2));
                    callback.onFetchCollectionResponse(responseList.get(3));
                    callback.onFetchEditorsShopResponse(responseList.get(4));
                    callback.onFetchNewShopResponse(responseList.get(5));
                }
            }

            @Override
            public void onFailure(Call<List<VitrinovaResponse>> call, Throwable t) {
                callback.onFetchError(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
