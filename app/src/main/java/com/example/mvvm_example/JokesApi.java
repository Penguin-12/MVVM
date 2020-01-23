package com.example.mvvm_example;

import com.example.mvvm_example.Models.Joke;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokesApi {

    String BASE_URl = "http://api.icndb.com/jokes/";

    @GET("random")
    Call<Joke> getJoke();

}
