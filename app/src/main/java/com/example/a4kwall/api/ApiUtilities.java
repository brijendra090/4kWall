package com.example.a4kwall.api;


import com.example.a4kwall.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static Retrofit retrofit = null;
    public static final String API ="563492ad6f9170000100000120f272b442f94847a0c10cfc1d2955df";

    public static ApiInterface getApiInterface()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
