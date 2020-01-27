
package com.example.mvvm_example.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Value {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("joke")
    @Expose
    private String joke;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;

    public Integer getId() {
        return id;
    }


    public String getJoke() {
        return joke;
    }


    public List<String> getCategories() {
        return categories;
    }


}
