package com.example.mvvm_example.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_example.Models.Value;
import com.example.mvvm_example.Repository.JokeRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    MutableLiveData<List<Value>> mutableLiveData;
    JokeRepository jokeRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }

        jokeRepository = JokeRepository.getInstance();
        mutableLiveData = jokeRepository.getJokeValueList();
    }


    public LiveData<List<Value>> getMutableLiveData() {
        return mutableLiveData;
    }
}
