package com.example.mvvm_example.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_example.Adapters.CustomAdapter;
import com.example.mvvm_example.Models.Value;
import com.example.mvvm_example.R;
import com.example.mvvm_example.ViewModel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CustomAdapter customAdapter;
    MainActivityViewModel mainActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainActivityViewModel = ViewModelProviders.of(this)
                .get(MainActivityViewModel.class);
        mainActivityViewModel.init();
        mainActivityViewModel.getMutableLiveData().observe(this, new Observer<List<Value>>() {
            @Override
            public void onChanged(List<Value> jokes) {
                customAdapter.notifyDataSetChanged();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        customAdapter = new CustomAdapter(this, mainActivityViewModel.getMutableLiveData().getValue());
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
