package com.example.mvvm_example.Models;


import java.util.List;

public class Joke {


    private String type;

    private List<Value> value = null;

    public String getType() {
        return type;
    }


    public List<Value> getValue() {
        return value;
    }


    public void setValue(List<Value> value) {
        this.value = value;
    }
}
