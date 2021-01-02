package com.example.demo.callAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {

    public static class RetrofitAPIClient {

        private static Retrofit retrofit = null;

        public static Retrofit getclient(){

            if (retrofit == null){
                retrofit = new  Retrofit.Builder().baseUrl("http://movieService:8081/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }

}
