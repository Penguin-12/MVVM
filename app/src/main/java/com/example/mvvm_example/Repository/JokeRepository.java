package com.example.mvvm_example.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_example.JokesApi;
import com.example.mvvm_example.Models.Joke;
import com.example.mvvm_example.Models.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokeRepository {
    static JokeRepository instance;
    Joke joke;
    List<Value> list = new ArrayList<>();


    public static JokeRepository getInstance() {
        if (instance == null) {
            instance = new JokeRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Value>> getJokeValueList() {
        setJoke();
        MutableLiveData<List<Value>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(list);
        return mutableLiveData;

    }

    private void setJoke() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(JokesApi.BASE_URl).addConverterFactory(GsonConverterFactory.create()).build();
        JokesApi jokesApi = retrofit.create(JokesApi.class);
        Call<Joke> call = jokesApi.getJoke();

        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                joke = response.body();
                list = joke.getValue();

                Log.i("Success", list.toString());
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                Log.i("ERROR", t.getMessage());
            }
        });

    }
}
