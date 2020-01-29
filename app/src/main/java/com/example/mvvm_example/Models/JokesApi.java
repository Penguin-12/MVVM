package com.example.mvvm_example.Models;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokesApi {

    String BASE_URl = "http://api.icndb.com/jokes/";

    @GET("random/100")
    Call<Joke> getJoke();

}
