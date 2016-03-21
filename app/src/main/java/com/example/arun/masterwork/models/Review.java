package com.example.arun.masterwork.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arun on 31/1/16.
 */
public class Review implements Parcelable {

    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String comments;

    public Review(Parcel in) {
        author = in.readString();
        comments = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(comments);
    }

    public static Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel source) {
            return new Review(source);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public String getComments() {
        return comments;
    }
}
