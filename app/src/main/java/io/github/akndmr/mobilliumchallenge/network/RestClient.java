package io.github.akndmr.mobilliumchallenge.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private final static String BASE_URL = "https://www.vitrinova.com/api/v2/";
    public final VitrinovaService mVitrinovaService;

    public RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mVitrinovaService = retrofit.create(VitrinovaService.class);
    }

     private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
}
