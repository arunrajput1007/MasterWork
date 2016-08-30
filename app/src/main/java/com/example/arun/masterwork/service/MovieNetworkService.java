package com.example.arun.masterwork.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.example.arun.masterwork.adapter.Utility;
import com.example.arun.masterwork.constants.MovieConstant;
import com.example.arun.masterwork.network.MovieDataFetcher;

/**
 * Created by arun on 2/6/16.
 */

public class MovieNetworkService extends IntentService {

    private final String TAG = "MovieNetworkService";
    private Context mContext = null;

    public MovieNetworkService() {
        super("MovieNetworkService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mContext = getApplicationContext();
        MovieDataFetcher fetcher = new MovieDataFetcher(mContext);
        Utility utility = new Utility(mContext);
        if (intent.getStringExtra(MovieConstant.INTENT_DIFFERENTIATOR).equals(MovieConstant.MOVIE_CATEGORY_POPULAR))
            utility.insertMovieListInDb(fetcher.fetchMovies(MovieConstant.POPULAR_MOVIES_URL), MovieConstant.MOVIE_CATEGORY_POPULAR);
        else if (intent.getStringExtra(MovieConstant.INTENT_DIFFERENTIATOR).equals(MovieConstant.MOVIE_CATEGORY_TOP_RATED))
            utility.insertMovieListInDb(fetcher.fetchMovies(MovieConstant.TOP_RATED_MOVIES_URL), MovieConstant.MOVIE_CATEGORY_TOP_RATED);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
