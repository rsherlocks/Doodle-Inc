package com.example.androidshaper.doodleinctestapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category
{
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("subcatg")
    @Expose
    private List<Subcatg> subcatg = null;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Subcatg> getSubcatg() {
        return subcatg;
    }

    public void setSubcatg(List<Subcatg> subcatg) {
        this.subcatg = subcatg;
    }
}
