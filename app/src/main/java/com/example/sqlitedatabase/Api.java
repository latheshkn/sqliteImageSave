package com.example.sqlitedatabase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("posts")
    Call<ArrayList<GetPostModel>> getPost(
    );
}
