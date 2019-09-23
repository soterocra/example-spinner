package com.soterocra.spinner.api;

import com.soterocra.spinner.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users")
    Call<List<User>> getAllUsers();

}
