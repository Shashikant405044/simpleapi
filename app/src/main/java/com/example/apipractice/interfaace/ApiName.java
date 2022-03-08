package com.example.apipractice.interfaace;

import com.example.apipractice.model.PostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiName {

    @GET("/posts")
    Call<List<PostResponse>> getPostData();



}
