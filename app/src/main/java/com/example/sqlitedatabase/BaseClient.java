package com.example.sqlitedatabase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClient {
    private static String Base_url = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            return retrofit = new Retrofit.Builder().baseUrl(Base_url).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }else{
            return retrofit;
        }
    }
}
