package com.example.chatsage;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

//making a GET request getting the data from the api
public interface RetrofitAPI {
    @GET
        //after getting data store in msg modal
    Call<MsgModal> getMessage(@Url String url);
}
//https://github.com/gangulwar/Android-ChatBot/blob/main/app/src/main/java/com/gangulwar/layouttest/MainActivity.java