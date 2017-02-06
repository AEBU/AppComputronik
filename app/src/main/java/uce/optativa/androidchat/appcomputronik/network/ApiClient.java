package uce.optativa.androidchat.appcomputronik.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static uce.optativa.androidchat.appcomputronik.constants.Constants.url;

/**
 * Created by Alexis on 21/01/2017.
 */

public class ApiClient {
    private static Retrofit retrofit= null;

    public  static Retrofit getRetrofit(){

        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


        }
        return retrofit;
    }


}
