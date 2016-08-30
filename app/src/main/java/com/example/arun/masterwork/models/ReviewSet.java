package com.example.arun.masterwork.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by arun on 29/6/16.
 */

public class ReviewSet implements Parcelable {

    static Creator<ReviewSet> CREATOR = new Creator<ReviewSet>() {
        @Override
        public ReviewSet createFromParcel(Parcel source) {
            return new ReviewSet(source);
        }

        @Override
        public ReviewSet[] newArray(int size) {
            return new ReviewSet[size];
        }
    };

    @SerializedName("id")
    private int movieId;
    @SerializedName("results")
    private ArrayList<Review> reviewList;

    private ReviewSet(Parcel in) {
        movieId = in.readInt();
        reviewList = in.createTypedArrayList(Review.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieId);
        dest.writeTypedList(reviewList);
    }

    public int getMovieId() {
        return movieId;
    }

    public ArrayList<Review> getReviewList() {
        return reviewList;
    }
}
