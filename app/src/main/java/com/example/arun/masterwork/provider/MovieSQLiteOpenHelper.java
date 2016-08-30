package com.example.arun.masterwork.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.arun.masterwork.BuildConfig;
import com.example.arun.masterwork.provider.movie.MovieColumns;
import com.example.arun.masterwork.provider.review.ReviewColumns;
import com.example.arun.masterwork.provider.trailer.TrailerColumns;

public class MovieSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_FILE_NAME = "Mydb.db";
    // @formatter:off
    public static final String SQL_CREATE_TABLE_MOVIE = "CREATE TABLE IF NOT EXISTS "
            + MovieColumns.TABLE_NAME + " ( "
            + MovieColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MovieColumns.MOVIE_NO + " INTEGER NOT NULL, "
            + MovieColumns.TITLE + " TEXT NOT NULL, "
            + MovieColumns.CATEGORY + " TEXT NOT NULL, "
            + MovieColumns.POSTER_PATH_URL + " TEXT NOT NULL, "
            + MovieColumns.POSTER_PATH_LOCAL + " TEXT, "
            + MovieColumns.OVERVIEW + " TEXT NOT NULL, "
            + MovieColumns.RELEASE_DATE + " TEXT NOT NULL, "
            + MovieColumns.ADULT + " INTEGER NOT NULL DEFAULT 0, "
            + MovieColumns.VOTE_AVERAGE + " REAL NOT NULL, "
            + MovieColumns.VOTE_COUNT + " INTEGER NOT NULL, "
            + MovieColumns.POPULARITY + " REAL NOT NULL, "
            + MovieColumns.BACKDROP_PATH_URL + " TEXT NOT NULL, "
            + MovieColumns.BACKDROP_PATH_LOCAL + " TEXT "
            + ", CONSTRAINT unique_constraint UNIQUE (movie_no) ON CONFLICT REPLACE"
            + " );";
    public static final String SQL_CREATE_TABLE_REVIEW = "CREATE TABLE IF NOT EXISTS "
            + ReviewColumns.TABLE_NAME + " ( "
            + ReviewColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ReviewColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + ReviewColumns.AUTHOR + " TEXT NOT NULL, "
            + ReviewColumns.COMMENTS + " TEXT NOT NULL "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + ReviewColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE RESTRICT"
            + " );";
    public static final String SQL_CREATE_TABLE_TRAILER = "CREATE TABLE IF NOT EXISTS "
            + TrailerColumns.TABLE_NAME + " ( "
            + TrailerColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TrailerColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + TrailerColumns.KEY + " TEXT NOT NULL, "
            + TrailerColumns.NAME + " TEXT NOT NULL, "
            + TrailerColumns.TYPE + " TEXT NOT NULL "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + TrailerColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE RESTRICT"
            + ", CONSTRAINT unique_constraint UNIQUE (key) ON CONFLICT REPLACE"
            + " );";
    private static final String TAG = MovieSQLiteOpenHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static MovieSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final MovieSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:on

    private MovieSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new MovieSQLiteOpenHelperCallbacks();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private MovieSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new MovieSQLiteOpenHelperCallbacks();
    }

    public static MovieSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static MovieSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }

    /*
     * Pre Honeycomb.
     */
    private static MovieSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new MovieSQLiteOpenHelper(context);
    }

    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static MovieSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new MovieSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_REVIEW);
        db.execSQL(SQL_CREATE_TABLE_TRAILER);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
