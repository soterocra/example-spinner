package com.soterocra.spinner.helper.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    public static Retrofit getRetrofit(String url) {

        if (!url.substring(url.length()-1).equals("/")) {
            url = url + "/";
        }

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

}
