package com.example.mvvm_example.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.mvvm_example.Models.Joke;
import com.example.mvvm_example.Models.JokesApi;
import com.example.mvvm_example.Models.Value;
import com.example.mvvm_example.Room.AppDatatbase;

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
    static AppDatatbase appDatatbase;
    static Context context;
    MutableLiveData<List<Value>> mutableLiveData = new MutableLiveData<>();

    public static JokeRepository getInstance(Context context) {
        JokeRepository.context = context;
        appDatatbase = Room.databaseBuilder(context, AppDatatbase.class, "hey").allowMainThreadQueries().build();
        if (instance == null) {
            instance = new JokeRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Value>> getJokeValueList() {
        setJoke();
        mutableLiveData.setValue(appDatatbase.jokeValueDao().getAllJokes());
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
                emptyRoom();
                fillRoom(response);
                Log.i("Success", list.toString());
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                Log.i("ERROR", t.getMessage());
            }
        });

    }

    public void fillRoom(Response<Joke> response) {
        appDatatbase.jokeValueDao().insertJokes(response.body().getValue());
    }


    public void emptyRoom() {
        appDatatbase.jokeValueDao().emptyDatabse();
    }
}
