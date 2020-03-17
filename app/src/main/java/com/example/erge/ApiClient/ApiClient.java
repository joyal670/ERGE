package com.example.erge.ApiClient;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String Base_url = "https://dev.sicsapp.com/erge/api/v1/";
    public static final String Home_page_Base_url = "http://api.walmartlabs.com/v1/";
    private static Retrofit retrofit = null;
    private static Retrofit Home_page_retrofit = null;

    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build();

    public static Retrofit getClient(Context context)
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
    public static Retrofit getHomeClient(Context context)
    {
        if(Home_page_retrofit == null)
        {
            Home_page_retrofit = new Retrofit.Builder()
                    .baseUrl(Home_page_Base_url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return Home_page_retrofit;
    }
}