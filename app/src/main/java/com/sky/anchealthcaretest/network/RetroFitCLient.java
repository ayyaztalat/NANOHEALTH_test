package com.sky.anchealthcaretest.network;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitCLient {

    private static final String str = "https://fakestoreapi.com/";
    private static APIService INSTANCE;

    public static APIService with() {
        if (INSTANCE == null) {
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(str)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(getUnsafeOkHttpClient(TAG))
                    .build()
                    .create(APIService.class);
        }
        return INSTANCE;
    }

    private static HttpLoggingInterceptor interceptor(String tag) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.e(tag, "log: " + message));
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }



}
