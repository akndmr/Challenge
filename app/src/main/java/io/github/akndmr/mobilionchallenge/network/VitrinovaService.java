package io.github.akndmr.mobilionchallenge.network;

import java.util.List;

import io.github.akndmr.mobilionchallenge.model.VitrinovaResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VitrinovaService {

    //Get movie details and cast info of a movie by id
    @GET("discover")
    Call<List<VitrinovaResponse>> getItems();

}
