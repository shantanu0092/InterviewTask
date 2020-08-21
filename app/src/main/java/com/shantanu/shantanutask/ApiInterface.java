package com.shantanu.shantanutask;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("drycobb/api/Api/login")
    Call<LoginModel> login(@Field("mobile") String mobile, @Field("password") String password, @Field("token") String token);

}



