package com.example.androidshaper.doodleinctestapplication.service;

import com.example.androidshaper.doodleinctestapplication.model.MainDataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OurRetrofitClient
{
    @GET("get_categories")
    Call<MainDataModel> getObject();
}
