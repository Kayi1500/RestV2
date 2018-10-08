package com.example.lenovo.restaurangapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by lenovo on 2018-10-08.
 */

public interface API {
    String base_url = "http://www.json-generator.com/";
    @GET
    Call<ResponseBody> getData(@Url String link);
}