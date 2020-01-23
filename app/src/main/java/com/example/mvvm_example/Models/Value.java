package com.example.mvvm_example.Models;

import java.util.List;

public class Value {

    private Integer id;
    private String joke;
    private List<Object> categories = null;

    public Value(String joke) {
        this.joke = joke;
    }

    public Integer getId() {
        return id;
    }

    public String getJoke() {

        return joke;
    }

    public List<Object> getCategories() {
        return categories;
    }


}
