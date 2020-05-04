package com.example.myapplication.services;


import com.example.myapplication.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @GET("/hello")
    Call<String> helloUser(@Query("name") String nameVar, @Query("surname") String surnameVar);
    @GET("/greeting/{firstname}/{lastname}")
    Call<User> greetingUser(@Path("firstname") String nameVar,
                            @Path("lastname") String surnameVar);
}
