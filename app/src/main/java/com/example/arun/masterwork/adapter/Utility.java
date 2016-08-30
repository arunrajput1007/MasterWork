package com.example.arun.masterwork.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.util.Log;

import com.example.arun.masterwork.R;
import com.example.arun.masterwork.constants.IMAGECONSTANT;
import com.example.arun.masterwork.models.Movie;
import com.example.arun.masterwork.provider.movie.MovieColumns;
import com.example.arun.masterwork.provider.movie.MovieContentValues;
import com.example.arun.masterwork.provider.movie.MovieSelection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by arun on 19/3/16.
 */
public class Utility {

    public static MainRecyclerAdapter mainRecyclerAdapter = null;
    private Context mContext = null;
    private String TAG = "Utility";

    public Utility(Context mContext) {
        this.mContext = mContext;
    }

    public static int getTitleBarColor(Bitmap bitmap) {
        int color = R.color.colorPrimary;
        if (bitmap != null) {
            Palette p = Palette.from(bitmap).generate();
            Palette.Swatch vibrantSwatch = p.getVibrantSwatch();

            if (vibrantSwatch != null)
                color = vibrantSwatch.getRgb();
        }
        return color;
    }

    public static Bitmap getImageBitmap(String name, Context mContext) {
        try {
            FileInputStream fis = mContext.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean compareMovieList(List<Movie> networkMovieList, List<Movie> dbMovieList) {
        return networkMovieList.equals(dbMovieList);
    }

    public String saveBitMap(String imageUrl) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
            if (bitmap != null) {
                String fileName = Math.abs(new Random().nextInt()) + ".jpg";
                FileOutputStream fos = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
                return fileName;
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public boolean checkIfNew() {
        return false;
    }

    public ArrayList<Movie> getMovieListByCategory(String category) {
        MovieSelection movieSelection = new MovieSelection();
        movieSelection.category(category);
        Cursor cursor = movieSelection.query(mContext);
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        Movie movie;
        while (cursor.moveToNext()) {
            movie = new Movie();
            movie.setId(cursor.getInt(cursor.getColumnIndex(MovieColumns.MOVIE_NO)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MovieColumns.TITLE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndex(MovieColumns.OVERVIEW)));
            movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(MovieColumns.RELEASE_DATE)));
            movie.setRating(cursor.getFloat(cursor.getColumnIndex(MovieColumns.VOTE_AVERAGE)));
            movie.setVoteCount(cursor.getInt(cursor.getColumnIndex(MovieColumns.VOTE_COUNT)));
            movie.setPopularity(cursor.getFloat(cursor.getColumnIndex(MovieColumns.POPULARITY)));
            movie.setPosterLocalPath(cursor.getString(cursor.getColumnIndex(MovieColumns.POSTER_PATH_LOCAL)));
            movie.setBackImageLocalPath(cursor.getString(cursor.getColumnIndex(MovieColumns.BACKDROP_PATH_LOCAL)));
            movieArrayList.add(movie);
        }
        return movieArrayList;
    }

    public void insertMovieListInDb(List<Movie> movieList, String category) {
        MovieContentValues movieContentValues = new MovieContentValues();
        for (Movie movie : movieList) {
            try {
                movieContentValues.putMovieNo(movie.getId()).putTitle(movie.getTitle()).putCategory(category).
                        putPosterPathUrl(movie.getImagePath()).putPosterPathLocalNull().putOverview(movie.getDescription()).
                        putReleaseDate(movie.getReleaseDate()).putAdult(movie.isAdult()).putVoteAverage(movie.getRating()).
                        putVoteCount(movie.getVoteCount()).putPopularity(movie.getPopularity()).putBackdropPathUrl(movie.getBackImage()).
                        putBackdropPathLocalNull();
                Uri uri = movieContentValues.insert(mContext);
                saveMovieImageInDB(movie, Integer.parseInt(uri.getLastPathSegment()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveMovieImageInDB(final Movie movie, final int _id) {
        final Map<String, String> imageMap = new HashMap<>();
        imageMap.put(IMAGECONSTANT.IMAGE_TYPE_POSTER, IMAGECONSTANT.IMAGE_BASE_URL_POSTER_IMAGE + movie.getImagePath());
        imageMap.put(IMAGECONSTANT.IMAGE_TYPE_BACK_COVER, IMAGECONSTANT.IMAGE_BASE_URL_BACK_IMAGE + movie.getBackImage());
        new Thread() {
            @Override
            public void run() {
                for (String imageKey : imageMap.keySet()) {
                    String fileName = saveBitMap(imageMap.get(imageKey));
                    MovieContentValues movieContentValues = new MovieContentValues();
                    if (imageKey.equals(IMAGECONSTANT.IMAGE_TYPE_POSTER))
                        movieContentValues.putPosterPathLocal(fileName);
                    else if (imageKey.equals(IMAGECONSTANT.IMAGE_TYPE_BACK_COVER))
                        movieContentValues.putBackdropPathLocal(fileName);
                    movieContentValues.update(mContext, new MovieSelection().id(_id));
                }
            }
        }.start();
    }
}

