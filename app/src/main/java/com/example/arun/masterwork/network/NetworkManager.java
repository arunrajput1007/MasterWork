package com.example.arun.masterwork.network;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arun.masterwork.R;
import com.example.arun.masterwork.adapter.MovieGridAdapter;
import com.example.arun.masterwork.adapter.Utility;
import com.example.arun.masterwork.constants.MOVIECONSTANT;
import com.example.arun.masterwork.models.MovieSet;
import com.example.arun.masterwork.models.Review;
import com.example.arun.masterwork.models.ReviewSet;
import com.example.arun.masterwork.models.Trailer;
import com.example.arun.masterwork.models.TrailerSet;
import com.example.arun.masterwork.service.IMovieService;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by arun on 20/12/15.
 */

public class NetworkManager {

    private final String TAG = "NetworkManager";
    private Retrofit retrofit = null;
    private IMovieService service = null;
    private Activity activity = null;
    private Context mContext = null;

    public NetworkManager(Context mContext) {
        this.mContext = mContext;
        this.activity = (Activity) mContext;
        retrofit = new Retrofit.Builder().baseUrl(MOVIECONSTANT.MOVIE_DB_BASE_URL)
                .addConverterFactory(GsonConverterFactory
                        .create()).build();
        service = retrofit.create(IMovieService.class);
    }

    public void fetchMovies(String category_url, final MovieGridAdapter adapter) {
        Call<MovieSet> movieCall = service.getMovies(category_url, MOVIECONSTANT.MOVIE_DB_API_KEY);
        movieCall.enqueue(new Callback<MovieSet>() {
            @Override
            public void onResponse(Response<MovieSet> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body().getMovieList().size() > 0) {
                    adapter.updateData(response.body().getMovieList());

                    /*dbManager = new DbManager(mContext);
                    ListIterator<Movie> movieListIterator = response.body().getMovieList().listIterator();

                    while (movieListIterator.hasNext()) {
                        Movie movie = movieListIterator.next();
                        int movie_id=0;
                        if(category_url.equals(MOVIECONSTANT.POPULAR_MOVIES_URL)) {
                            movie_id = dbManager.checkAndInsertMovieInDb(movie, MOVIECONSTANT.MOVIE_TYPE_POPULAR_IN_COLUMN);
                        }
                        else if(category_url.equals(MOVIECONSTANT.TOP_RATED_MOVIES_URL)) {
                            movie_id = dbManager.checkAndInsertMovieInDb(movie, MOVIECONSTANT.MOVIE_TYPE_TOP_RATED_IN_COLUMN);
                        }
                        if (movie_id != -1) {
                            fetchMovieTrailer(movie.getId(),movie_id);
                            fetchMovieReviews(movie.getId(),movie_id);
                            dbManager.downloadCheckAndInsertImageInDb(IMAGECONSTANT.IMAGE_BASE_URL+movie.getImagePath(), IMAGECONSTANT.IMAGE_TYPE_POSTER,movie_id);
                            dbManager.downloadCheckAndInsertImageInDb(IMAGECONSTANT.IMAGE_BASE_URL+movie.getBackImage(), IMAGECONSTANT.IMAGE_TYPE_BACK_COVER,movie_id);
                        }
                    }*/
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void fetchMovieTrailer(int movieId) {
        Call<TrailerSet> trailerSetCall = service.getTrailer(movieId, MOVIECONSTANT.MOVIE_DB_API_KEY);
        trailerSetCall.enqueue(new Callback<TrailerSet>() {
            @Override
            public void onResponse(Response<TrailerSet> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body().getTrailerList().size() > 0) {
                    ArrayList<Trailer> trailerList = response.body().getTrailerList();
                    Utility.setFirstTrailer(trailerList.get(0).getKey());

                    LinearLayout trailerLinear = (LinearLayout) activity.findViewById(R.id.trailer_layout);
                    LinearLayout trailerRowItem = null;

                    TextView trailerText = null;
                    ImageView playImage = null;

                    LinearLayout.LayoutParams trailerRowParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.HORIZONTAL);

                    LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

                    for (int i = 0; i < trailerList.size(); i++) {
                        final Trailer trailer = trailerList.get(i);
                        trailerRowItem = new LinearLayout(mContext);
                        trailerRowItem.setLayoutParams(trailerRowParams);
                        trailerRowItem.setPadding(0,0,0,8);

                        trailerText = new TextView(mContext);
                        playImage = new ImageView(mContext);

                        playImage.setLayoutParams(imageViewParams);
                        playImage.setPadding(0,0,16,0);
                        playImage.setImageResource(R.drawable.ic_play);
                        playImage.setAdjustViewBounds(true);

                        trailerText.setLayoutParams(textViewParams);
                        trailerText.setTextAppearance(mContext,android.R.style.TextAppearance_Material_Medium);
                        trailerText.setGravity(Gravity.CENTER_VERTICAL);
                        trailerText.setText(trailer.getVideoName());

                        trailerRowItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer.getKey()));
                                    mContext.startActivity(intent);
                                } catch (ActivityNotFoundException ex) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey()));
                                    mContext.startActivity(intent);
                                }
                            }
                        });

                        trailerRowItem.addView(playImage);
                        trailerRowItem.addView(trailerText);
                        trailerLinear.addView(trailerRowItem);
                    }
                    /*dbManager = new DbManager(mContext);
                    ListIterator<Trailer> trailerListIterator = response.body().getTrailerList().listIterator();

                    while(trailerListIterator.hasNext()){
                        Trailer trailer = trailerListIterator.next();
                        int trailer_id=dbManager.checkKeyAndInsertVideoToDb(trailer);
                        if(trailer_id != -1){
                            dbManager.insertMovieTrailerIds(movie_id,trailer_id);
                            //get youtube trailer images and save path in db
                        }
                    }*/
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void fetchMovieReviews(int movieId) {
        Call<ReviewSet> reviewSetCall = service.getReviews(movieId, MOVIECONSTANT.MOVIE_DB_API_KEY);
        reviewSetCall.enqueue(new Callback<ReviewSet>() {
            @Override
            public void onResponse(Response<ReviewSet> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body().getReviewList().size() > 0) {
                    ArrayList<Review> reviewList = response.body().getReviewList();

                    LinearLayout reviewLinear = (LinearLayout) activity.findViewById(R.id.review_layout);

                    TextView author = null;
                    TextView comments = null;

                    LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    for (int i = 0; i < reviewList.size(); i++) {
                        Review review = reviewList.get(i);

                        author = new TextView(mContext);
                        comments = new TextView(mContext);

                        author.setLayoutParams(textViewParams);
                        comments.setLayoutParams(textViewParams);
                        author.setPadding(0,0,0,8);
                        comments.setPadding(0,0,0,16);

                        author.setGravity(Gravity.CENTER_VERTICAL);
                        author.setTextAppearance(mContext,android.R.style.TextAppearance_Material_Title);

                        comments.setGravity(Gravity.CENTER_VERTICAL);
                        comments.setTextAppearance(mContext,android.R.style.TextAppearance_Material_Small);

                        author.setText(review.getAuthor());
                        comments.setText(review.getComments());

                        reviewLinear.addView(author);
                        reviewLinear.addView(comments);
                    }
                        //adapter.updateData(response.body().getReviewList());
                    /*dbManager = new DbManager(mContext);
                    ListIterator<Review> reviewListIterator = response.body().getReviewList().listIterator();

                    while (reviewListIterator.hasNext()){
                        Review review = reviewListIterator.next();
                        int review_id = dbManager.checkAndInsertReviewsToDb(review);
                        if(review_id != -1){
                            dbManager.insertMovieReviewIds(movie_id,review_id);
                        }
                    }*/

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}