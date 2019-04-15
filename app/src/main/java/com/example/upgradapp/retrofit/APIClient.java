package com.example.upgradapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static APIClient mInstance;
    private Retrofit retrofit;



    private APIClient(){


        String BASE_URL = "https://api.stackexchange.com/";
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }

    public static synchronized APIClient getmInstance(){
        if(mInstance==null){
            mInstance = new APIClient();
        }
        return mInstance;
    }

    public ApiInterface getApi(){

        return retrofit.create(ApiInterface.class);
    }

}
