package com.example.deltatask3;

import android.media.session.MediaSession;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {
    @POST("/token/")
    @FormUrlEncoded
    //Call<Data> sendData(@Body User user);
    Call<Data> loginaccess(
            @Field("username") String username,
            @Field("password") String password
    );
    @GET("/users/me/debt/")
    Call<Integer> getDebt();
    @GET("/users/me/")
    Call<User> getAll();
    @POST("/signup/")
    @FormUrlEncoded
    Call<Data> signup(
            @Field("username") String username,
            @Field("image") String image,
            @Field("lent") int lent,
            @Field("debt") int debt,
            @Field("hashpass") String hashpass

            //@Body RequestBody requestBody
    );
    @GET("/users/me/transaction")
    Call<ArrayList<tdata>> getData();
    @POST("/mod/")
    @FormUrlEncoded
    Call<Void> send(
            @Field("debt") int debt,
            @Field("sname") String sname,
            @Field("sname2") String sname2
    );
    @POST("/append/")
    @FormUrlEncoded
    Call<Void> sink(
            @Field("spname") String spname,
            @Field("spperson") String spperson,
            @Field("username") String username,
            @Field("image") String image,
            @Field("name") String name,
            @Field("amount") float amount
    );
}
