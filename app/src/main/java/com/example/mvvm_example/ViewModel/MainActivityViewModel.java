package com.example.mvvm_example.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_example.Models.Value;
import com.example.mvvm_example.Repository.JokeRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    Context context;
    MutableLiveData<List<Value>> mutableLiveData;
    JokeRepository jokeRepository;

    public void init(Context context) {
        this.context = context;
        if (mutableLiveData != null) {
            return;
        }

        jokeRepository = JokeRepository.getInstance(context);
        mutableLiveData = jokeRepository.getJokeValueList();
    }


    public LiveData<List<Value>> getMutableLiveData() {
        return mutableLiveData;
    }
}
