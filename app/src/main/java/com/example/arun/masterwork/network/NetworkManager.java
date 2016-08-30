package com.example.arun.masterwork.network;

import com.example.arun.masterwork.constants.MovieConstant;
import com.example.arun.masterwork.service.IMovieService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by arun on 20/12/15.
 */

public class NetworkManager {

    private static IMovieService service = null;
    private static NetworkManager networkManager = null;
    private final String TAG = "NetworkManager";

    private NetworkManager() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MovieConstant.MOVIE_DB_BASE_URL)
                .addConverterFactory(GsonConverterFactory
                        .create()).build();
        service = retrofit.create(IMovieService.class);
    }

    public static synchronized IMovieService getService() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }
        return service;
    }
}