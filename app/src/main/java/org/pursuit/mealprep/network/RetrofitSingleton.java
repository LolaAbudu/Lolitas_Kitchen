package org.pursuit.mealprep.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static final String BASE_URL = "https://raw.githubusercontent.com/";

    private static Retrofit oneInstance;

    public static Retrofit getInstance(){
        if(oneInstance != null){
            return oneInstance;
        }
        oneInstance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return oneInstance;
    }

    private RetrofitSingleton(){}
}
