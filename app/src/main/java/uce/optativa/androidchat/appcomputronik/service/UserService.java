package uce.optativa.androidchat.appcomputronik.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import uce.optativa.androidchat.appcomputronik.model.Users;

/**
 * Created by Alexis on 15/01/2017.
 */

public interface UserService {

    @GET("/user/")
    Call<List<Users>> getUsers();

    //@GET("user/{user_id}")
    //void getUser(@Path("user_id") String user);
    @GET("user/{user_id}")
    Call<Users> getUser(@Path("user_id") String user);


    @GET("user/{username}/{email}")
    Call<Users> getUserByEmailAndUserName(@Path("username") String username, @Path("email") String email);

    @GET("user/1")
    void getUser(Callback<List<Users>> callback);


    //Post en Retrofit

    @POST("userpost/")
    Call<Users> setUser(@Body Users user);

}
