package com.example.arun.masterwork.network;

import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.arun.masterwork.constants.MovieConstant;
import com.example.arun.masterwork.models.Movie;
import com.example.arun.masterwork.models.MovieSet;
import com.example.arun.masterwork.service.IMovieService;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by arun on 15/5/16.
 */
public class MovieDataFetcher {

    private final String TAG = "MovieDataFetcher";
    private IMovieService service = null;
    private Context mContext = null;
    private ContentResolver resolver = null;

    public MovieDataFetcher(@NonNull Context mContext) {
        this.mContext = mContext;
        resolver = mContext.getContentResolver();
        service = NetworkManager.getService();
    }

    public List<Movie> fetchMovies(@NonNull final String category) {

        if (category.equals(MovieConstant.MOVIE_CATEGORY_POPULAR) || category.equals(MovieConstant.MOVIE_CATEGORY_TOP_RATED)) {
            String url = null;

            if (category.equals(MovieConstant.MOVIE_CATEGORY_POPULAR)) {
                url = MovieConstant.POPULAR_MOVIES_URL;
            } else if (category.equals(MovieConstant.MOVIE_CATEGORY_TOP_RATED)) {
                url = MovieConstant.TOP_RATED_MOVIES_URL;
            }

            Call<MovieSet> movieCall = service.getMovies(url, MovieConstant.MOVIE_DB_API_KEY);
            try {
                Response<MovieSet> response = movieCall.execute();
                List<Movie> movieList = response.body().getMovieList();

                if (response.isSuccess() && movieList.size() > 0) {
                    return movieList;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }/*
            movieCall.enqueue(new Callback<MovieSet>() {
                @Override
                public void onResponse(Response<MovieSet> response, Retrofit retrofit) {
                    List<Movie> movieList = response.body().getMovieList();

                    if (response.isSuccess() && movieList.size() > 0) {

                        for (final Movie movie : movieList) {
                            int _id = new MovieContentValues().insertAll(resolver, movie);
                            if (_id != -1) {
                                new MovieCategoryContentValues().putMovieId(_id).putCategory(category).insert(resolver);
                                fetchMovieTrailer(movie.getId(), _id); //thread
                                fetchMovieReviews(movie.getId(), _id); //thread
                                saveMovieImageInDB(movie, _id);        //thread
                            }
                        }
                        Utility.mainRecyclerAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });*/
        }
        return null;
    }

    /*private void fetchMovieTrailer(int movieId, final int _id) {
        Call<TrailerSet> trailerSetCall = service.getTrailer(movieId);
        trailerSetCall.enqueue(new Callback<TrailerSet>() {
            @Override
            public void onResponse(@NonNull Response<TrailerSet> response, Retrofit retrofit) {
                ArrayList<Trailer> trailerList = response.body().getTrailerList();
                if (response.isSuccess() && trailerList.size() > 0) {
                    for (Trailer trailer : trailerList) {
                        new TrailerContentValues().putMovieId(_id).insertAll(resolver, trailer);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void fetchMovieReviews(final int movieId, final int _id) {
        Call<ReviewSet> reviewSetCall = service.getReviews(movieId);
        reviewSetCall.enqueue(new Callback<ReviewSet>() {
            @Override
            public void onResponse(@NonNull Response<ReviewSet> response, Retrofit retrofit) {
                if (response.body() != null) {
                    ArrayList<Review> reviewList = response.body().getReviewList();
                    if (response.isSuccess() && reviewList.size() > 0) {
                        for (Review review : reviewList) {
                            new ReviewContentValues().putMovieId(_id).insertAll(resolver, review);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }*/
}
