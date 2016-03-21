package com.example.arun.masterwork.DBHandler;

import android.content.Context;
import android.os.Bundle;

import com.example.arun.masterwork.asynctask.ImageDownloader;
import com.example.arun.masterwork.constants.IMAGECONSTANT;
import com.example.arun.masterwork.models.Movie;
import com.example.arun.masterwork.provider.movie.MovieColumns;
import com.example.arun.masterwork.provider.movie.MovieContentValues;
import com.example.arun.masterwork.provider.movie.MovieCursor;
import com.example.arun.masterwork.provider.movie.MovieSelection;

/**
 * Created by arun on 7/2/16.
 */
public class DbManager {

    private ImageDownloader imageDownloader = null;

    private Context mContext;

    public DbManager(Context mContext) {
        this.mContext = mContext;
    }

    public int addMovieToDb(Movie movie, String category) {
        MovieSelection movieSelection = new MovieSelection();
        movieSelection.movieNo(movie.getId()).and().category(category);
        String[] projection = {MovieColumns._ID, MovieColumns.MOVIE_NO, MovieColumns.CATEGORY};
        MovieCursor cursor = movieSelection.query(mContext.getContentResolver(), projection);
        if (cursor.moveToFirst())
            return -1;
        else {
            cursor.close();
            int id=insertMovieToDB(movie, category);
            downloadAndInsertImageInDb(IMAGECONSTANT.IMAGE_BASE_URL_POSTER_IMAGE + movie.getImagePath(),IMAGECONSTANT.IMAGE_TYPE_POSTER_DB,movie.getId());
            downloadAndInsertImageInDb(IMAGECONSTANT.IMAGE_BASE_URL_BACK_IMAGE + movie.getBackImage(),IMAGECONSTANT.IMAGE_TYPE_BACK_COVER_DB,movie.getId());
            return id;
        }
    }

    public int insertMovieToDB(Movie movie, String category) {
        MovieContentValues movieContentValues = new MovieContentValues();
        movieContentValues.putMovieNo(movie.getId()).putTitle(movie.getTitle()).putPosterPathUrl(movie.getImagePath()).putOverview(movie.getDescription())
                .putReleaseDate(movie.getReleaseDate()).putAdult(movie.isAdult()).putVoteAverage(movie.getRating()).putVoteCount(movie.getVoteCount())
                .putPopularity(movie.getPopularity()).putBackdropPathUrl(movie.getBackImage()).putCategory(category)
                .putPosterPathLocal(null).putBackdropPathLocal(null);
        String _id = movieContentValues.insert(mContext.getContentResolver()).getLastPathSegment();
        return Integer.parseInt(_id);
    }

    public void downloadAndInsertImageInDb(String imageUrl, String imageType, int movie_id) {
        Bundle imageDbBundle = new Bundle();
        imageDbBundle.putString("imageUrl", imageUrl);
        imageDbBundle.putString("imageType", imageType);
        imageDbBundle.putInt("movie_id", movie_id);
        new ImageDownloader(mContext).execute(imageDbBundle);
    }

    public int insertImageLocalPathToDb(String image_path_internal, String imageType, int movie_id) {
        MovieContentValues movieContentValues = new MovieContentValues();

        if (imageType.equals(IMAGECONSTANT.IMAGE_TYPE_POSTER_DB))
            movieContentValues.putPosterPathLocal(image_path_internal);
        else if (imageType.equals(IMAGECONSTANT.IMAGE_TYPE_BACK_COVER_DB))
            movieContentValues.putBackdropPathLocal(image_path_internal);

        MovieSelection movieSelection = new MovieSelection();
        movieSelection.movieNo(movie_id);
        int id=movieContentValues.update(mContext.getContentResolver(), movieSelection);
        return id;
    }

    public Movie getMovieFromDb(){
        MovieSelection movieSelection = new MovieSelection();
        //String[] projection=
        return null;
    }

    /*public int checkKeyAndInsertVideoToDb(Trailer trailer) {
        TrailerSelection trailerSelection = new TrailerSelection();
        trailerSelection.key(trailer.getKey());
        String[] projection = {TrailerColumns._ID, TrailerColumns.KEY};
        TrailerCursor cursor = trailerSelection.query(mContext.getContentResolver(), projection);
        if (cursor.moveToFirst()) {
            return -1;
        } else {
            cursor.close();
            return insertVideoToDb(trailer);
        }
    }

    public int insertVideoToDb(Trailer trailer) {
        TrailerContentValues trailerContentValues = new TrailerContentValues();
        trailerContentValues.putKey(trailer.getKey());
        trailerContentValues.putType(trailer.getType());
        trailerContentValues.putTrailerName(trailer.getVideoName());
        String _id = trailerContentValues.insert(mContext.getContentResolver()).getLastPathSegment();
        return Integer.parseInt(_id);
    }

    public int checkAndInsertReviewsToDb(Review review) {
        ReviewSelection reviewSelection = new ReviewSelection();
        reviewSelection.author(review.getAuthor()).and().content(review.getComments());
        String[] projection = {ReviewColumns._ID, ReviewColumns.AUTHOR, ReviewColumns.CONTENT};
        ReviewCursor cursor = reviewSelection.query(mContext.getContentResolver(), projection);
        if (cursor.moveToFirst()) {
            return -1;
        } else {
            cursor.close();
            return insertReviewToDb(review);
        }
    }

    public int insertReviewToDb(Review review) {
        ReviewContentValues reviewContentValues = new ReviewContentValues();
        reviewContentValues.putAuthor(review.getAuthor());
        reviewContentValues.putContent(review.getComments());
        String review_id = reviewContentValues.insert(mContext.getContentResolver()).getLastPathSegment();
        return Integer.parseInt(review_id);
    }

    public void insertMovieTrailerIds(int movie_id, int trailer_id) {
        MovieTrailerContentValues movieTrailerContentValues = new MovieTrailerContentValues();
        movieTrailerContentValues.putMovieId(movie_id).putTrailerId(trailer_id);
        movieTrailerContentValues.insert(mContext.getContentResolver());
    }

    public void insertMovieReviewIds(int movie_id, int review_id) {
        MovieReviewContentValues movieReviewContentValues = new MovieReviewContentValues();
        movieReviewContentValues.putMovieId(movie_id).putReviewId(review_id);
        movieReviewContentValues.insert(mContext.getContentResolver());
    }*/
}
